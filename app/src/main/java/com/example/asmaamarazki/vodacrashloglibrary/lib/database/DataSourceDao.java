package com.example.asmaamarazki.vodacrashloglibrary.lib.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;

import java.util.List;

@Dao
public interface DataSourceDao {
    @Insert
    void insertError(ErrorInfo error);

    @Query("select * from ErrorInfo")
    List<ErrorInfo> getAllErrors();

    @Query("select * from ErrorInfo where id LIKE :id")
    List<ErrorInfo> getErrorUsingId(String id);

    @Delete
    void deleteError(ErrorInfo errorInfo);

}