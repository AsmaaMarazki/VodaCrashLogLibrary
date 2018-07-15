package com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity//(indices = {@Index(value = {"id"},unique = true) })
public class ErrorInfo {
    //@PrimaryKey(autoGenerate = true)
    //@NonNull
    //@ColumnInfo(name = "id")
    //private Integer id;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid; //new Primary key Ahmed


    @ColumnInfo(name = "error")
    private final String error;

    public ErrorInfo(String error)
    {
        this.error = error;
        this.uuid = UUID.randomUUID().toString();
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }
    @NonNull
    public String getUuid() {
        return uuid;
    }

    //    public void setId(@NonNull Integer id) {
//        this.id = id;
//    }

    public String getError(){return this.error;}

//    @NonNull
//    public Integer getId() {
//        return id;
//    }

    @Override
    public String toString() {
        return this.error   ;
    }

}
