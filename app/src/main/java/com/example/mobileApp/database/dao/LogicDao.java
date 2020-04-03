package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.LogicTable;

import java.util.List;

/**
 * The LogicDao interface is a mapping of some SQL queries (for LogicTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface LogicDao {

    // used in nextQuestion stored procedure
    @Query("SELECT * FROM logic WHERE q_id = :currQnID AND qnnaire_id = :currQnnID")
    List<LogicTable> getLogicObjects(int currQnID, int currQnnID);

    @Query("SELECT * FROM logic WHERE qnnaire_id = :currQnnID AND sequence_num = 1")
    LogicTable getFirstQn(int currQnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LogicTable> allLogic);

    @Query("DELETE FROM logic")
    void deleteAll();
}
