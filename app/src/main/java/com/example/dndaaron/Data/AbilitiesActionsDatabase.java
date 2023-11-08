package com.example.dndaaron.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.dndaaron.API.AbilitiesActions;


@Database(entities = {AbilitiesActions.class}, version = 1)
public abstract class AbilitiesActionsDatabase extends RoomDatabase {
    private static AbilitiesActionsDatabase INSTANCE;

    public static AbilitiesActionsDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AbilitiesActionsDatabase.class,"db"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract AbilitiesActionsDao getActionDao();
}
