package com.example.dndaaron.Data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dndaaron.API.AbilitiesActions;
import com.example.dndaaron.API.ActionAPI;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ActionViewModel extends AndroidViewModel {
        private final Application app;
        private final AbilitiesActionsDatabase actionDatabase ;
        private final AbilitiesActionsDao actionDao;

        public ActionViewModel(Application application) {
            super(application);

            this.app = application;
            this.actionDatabase = AbilitiesActionsDatabase.getDatabase(
                    this.getApplication());
            this.actionDao = actionDatabase.getActionDao();
        }

        public LiveData<List<AbilitiesActions>> getActionsFrom(String key,String type) {
            return actionDao.getActionsFrom(key,type);
        }

        public void refresh() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> {
                ActionAPI api = new ActionAPI();
                ArrayList<AbilitiesActions> actionsApi = api.getActions();

                this.actionDao.deleteActions();
                this.actionDao.addActions(actionsApi);
            });
        }
    }

