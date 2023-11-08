package com.example.dndaaron.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dndaaron.API.AbilitiesActions;
import com.example.dndaaron.API.ActionAPI;

import com.example.dndaaron.API.Monster;

import java.util.List;
@Dao
public interface AbilitiesActionsDao {


        @Query("select * from `AbilitiesActions`")
        LiveData<List<AbilitiesActions>> getActions();

        @Query("SELECT * FROM AbilitiesActions WHERE nameMonster LIKE '%' || :key || '%' and type like '%' || :type || '%'")
        LiveData<List<AbilitiesActions>> getActionsFrom(String key,String type);

        @Insert
        void addAction(AbilitiesActions action);

        @Insert
        void addActions(List<AbilitiesActions> actions);

        @Delete
        void deleteAction(AbilitiesActions action);

        @Query("DELETE FROM `AbilitiesActions`")
        void deleteActions();


}
