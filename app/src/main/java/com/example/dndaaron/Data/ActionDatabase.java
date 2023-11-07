package com.example.dndaaron.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dndaaron.API.Action;
import com.example.dndaaron.API.Monster;

@Database(entities = {Action.class}, version = 1)
public abstract class ActionDatabase extends RoomDatabase {
    private static ActionDatabase INSTANCE;

    public static ActionDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            ActionDatabase.class,"db"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract ActionDao getActionDao();
}
