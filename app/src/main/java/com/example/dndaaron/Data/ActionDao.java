package com.example.dndaaron.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dndaaron.API.Action;
import com.example.dndaaron.API.ActionAPI;
import com.example.dndaaron.API.Monster;

import java.util.List;
@Dao
public interface ActionDao {


        @Query("select * from `action`")
        LiveData<List<Action>> getActions();

        @Query("select * from `action` where idMonster = :key")
        LiveData<List<Action>> getActionsFrom(int key);

        @Insert
        void addAction(Action action);

        @Insert
        void addActions(List<Action> actions);

        @Delete
        void deleteAction(Action action);

        @Query("DELETE FROM `action`")
        void deleteActions();
}
