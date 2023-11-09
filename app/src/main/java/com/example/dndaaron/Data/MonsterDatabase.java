package com.example.dndaaron.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dndaaron.API.Monster;

@Database(entities = {Monster.class}, version = 1)
public abstract class
MonsterDatabase extends RoomDatabase {
    private static MonsterDatabase INSTANCE;

    public static MonsterDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            MonsterDatabase.class,"db2"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract MonsterDao getMonsterDao();
}
