package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.LogicTable;

import java.util.List;

@Dao
public interface LogicDao {

    // used in nextQuestion stored procedure
    @Query("SELECT * FROM logic WHERE q_id = :currQ AND qnnaire_id = :currQnn")
    List<LogicTable> getSkipLogic(int currQ, int currQnn);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LogicTable> logics);

    @Query("DELETE FROM logic")
    void deleteAll();
}
