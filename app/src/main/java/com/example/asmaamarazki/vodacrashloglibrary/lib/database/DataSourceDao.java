package com.example.asmaamarazki.vodacrashloglibrary.lib.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;

import java.util.List;

@Dao
public interface DataSourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertError(ErrorInfo error);

    @Query("select * from ErrorInfo")
    List<ErrorInfo> getAllErrors();

    @Query("select * from ErrorInfo where uuid LIKE :uuid")
    ErrorInfo getErrorUsingId(String uuid);

    @Delete
    void deleteError(ErrorInfo errorInfo);

}