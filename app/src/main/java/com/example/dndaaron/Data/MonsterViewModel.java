package com.example.dndaaron.Data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dndaaron.API.AbilitiesActions;
import com.example.dndaaron.API.ActionAPI;

import com.example.dndaaron.API.MonsterAPI;
import com.example.dndaaron.API.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MonsterViewModel extends AndroidViewModel {
        private final Application app;
        private final MonsterDatabase monsterDatabase ;
        private final MonsterDao monsterDao;
        private final AbilitiesActionsDatabase actionDatabase;
        private final AbilitiesActionsDao actionDao;

        public MonsterViewModel(Application application) {
            super(application);

            this.app = application;
            this.monsterDatabase = MonsterDatabase.getDatabase(this.getApplication());
            this.monsterDao = monsterDatabase.getMonsterDao();

            this.actionDatabase = AbilitiesActionsDatabase.getDatabase(this.getApplication());
            this.actionDao = actionDatabase.getActionDao();
        }

        public LiveData<List<Monster>> getMonsters() {
            return monsterDao.getMonsters();
        }
        public LiveData<List<AbilitiesActions>> getActionsFrom(String key,String type) {
            return actionDao.getActionsFrom(key,type);
        }

        public void refresh() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> {
                MonsterAPI api = new MonsterAPI();
                ArrayList<Monster> pokemonsApi = api.getMonsters();

                this.monsterDao.deleteMonsters();
                this.monsterDao.resetMonsterTable();
                this.monsterDao.addMonsters(pokemonsApi);

                ActionAPI api2 = new ActionAPI();
                ArrayList<AbilitiesActions> actionsApi = api2.getActions();

                this.actionDao.deleteActions();
                this.actionDao.addActions(actionsApi);
            });
        }
    }

