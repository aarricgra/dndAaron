package com.example.dndaaron.Data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

        public MonsterViewModel(Application application) {
            super(application);

            this.app = application;
            this.monsterDatabase = MonsterDatabase.getDatabase(
                    this.getApplication());
            this.monsterDao = monsterDatabase.getMonsterDao();
        }

        public LiveData<List<Monster>> getMonsters() {
            return monsterDao.getMonsters();
        }

        public void refresh() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> {
                MonsterAPI api = new MonsterAPI();
                ArrayList<Monster> pokemonsApi = api.getMonsters();

                this.monsterDao.deleteMonsters();
                this.monsterDao.addMonsters(pokemonsApi);
            });
        }
    }

