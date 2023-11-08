package com.example.dndaaron.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dndaaron.API.Monster;

import java.util.List;


    @Dao
    public interface MonsterDao {
        @Query("select * from monster")
        LiveData<List<Monster>> getMonsters();

        @Insert
        void addMonster(Monster pokemon);

        @Insert
        void addMonsters(List<Monster> pokemons);

        @Delete
        void deleteMonster(Monster pokemon);

        @Query("DELETE FROM monster")
        void deleteMonsters();
        @Query("DELETE FROM sqlite_sequence WHERE name='monster'")
        void resetMonsterTable();
    }

