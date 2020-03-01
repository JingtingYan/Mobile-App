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

    // get next Question(s) ID  -- may need to be changed later
    @Query("SELECT DISTINCT next_q_id FROM logic " +
           "WHERE logic.q_id = :currQnID " +
           "AND logic.qnnaire_id = :currQnnID")
    List<Integer> getNextQnsID(int currQnID, int currQnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LogicTable> allLogic);

    @Query("DELETE FROM logic")
    void deleteAll();
}
