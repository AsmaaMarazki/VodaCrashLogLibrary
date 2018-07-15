package com.example.asmaamarazki.vodacrashloglibrary.lib.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;


@Database(entities = {ErrorInfo.class}, version = 2)
public abstract class DataSource extends RoomDatabase{

    public abstract DataSourceDao getErrorInfo();
     private static DataSource INSTANCE;



    public static DataSource getAppDataSource(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DataSource.class, "error-database")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

