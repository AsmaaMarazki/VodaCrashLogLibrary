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

    private Integer id;

    @ColumnInfo(name = "error")
    private final String error;

    public ErrorInfo(String error) {
        this.error = error;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getError(){return this.error;}

    @NonNull
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.error   ;
    }

}
