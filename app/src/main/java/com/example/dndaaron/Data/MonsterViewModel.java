package com.example.dndaaron.Data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dndaaron.API.Monster;

import java.util.List;


    public class MonsterViewModel extends AndroidViewModel {
        private final Application app;
        private final MonsterDatabase monsterDatabase ;
        private final MonsterDao monsterDao;
        private LiveData<List<Monster>> monsters;

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

        void refresh() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> {
                PokeAPI api = new PokeAPI();
                ArrayList<Pokemon> pokemonsApi = api.getPokemons();

                this.pokemonDao.deletePokemons();
                this.pokemonDao.addPokemons(pokemonsApi);
            });
        }
    }

