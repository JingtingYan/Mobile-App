package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogicDao {

    // used in nextQuestion stored procedure
    @Query("SELECT * FROM logic " +
            "WHERE logic.q_id = :currQ " +
            "AND logic.qnnaire_id = :currQnn")
    List<Logic> getSkipLogic(int currQ, int currQnn);
}
