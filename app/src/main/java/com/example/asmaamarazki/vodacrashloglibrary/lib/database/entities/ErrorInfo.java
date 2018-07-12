package com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = {"id"},unique = true) })
public class ErrorInfo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull

    private final String id;

    @ColumnInfo(name = "error")
    private final String error;

    public ErrorInfo(@NonNull String id, String error) {
        this.id = id;
        this.error = error;
    }

    public String getError(){return this.error;}

    @NonNull
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.error   ;
    }

}
