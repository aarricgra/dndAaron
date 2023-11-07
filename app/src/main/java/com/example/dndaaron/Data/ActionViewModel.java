package com.example.dndaaron.Data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dndaaron.API.Action;
import com.example.dndaaron.API.ActionAPI;
import com.example.dndaaron.API.Monster;
import com.example.dndaaron.API.MonsterAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ActionViewModel extends AndroidViewModel {
        private final Application app;
        private final ActionDatabase actionDatabase ;
        private final ActionDao actionDao;

        public ActionViewModel(Application application) {
            super(application);

            this.app = application;
            this.actionDatabase = ActionDatabase.getDatabase(
                    this.getApplication());
            this.actionDao = actionDatabase.getActionDao();
        }

        public LiveData<List<Action>> getActionsFrom(int key) {
            return actionDao.getActionsFrom(key);
        }

        public void refresh() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> {
                ActionAPI api = new ActionAPI();
                ArrayList<Action> actionsApi = api.getActions();

                this.actionDao.deleteActions();
                this.actionDao.addActions(actionsApi);
            });
        }
    }

