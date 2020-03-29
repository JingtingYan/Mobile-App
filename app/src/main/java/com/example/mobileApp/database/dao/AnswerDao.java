package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.AnswerTable;

import java.util.List;

/**
 * The AnswerDao interface is a mapping of some SQL queries (for AnswerTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface AnswerDao {

    @Query("SELECT * FROM answers WHERE answer_id = :ansID AND qnnaire_id = :qnnID")
    AnswerTable getAnswer(int ansID, int qnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AnswerTable> answers);

    @Query("DELETE FROM answers")
    void deleteAll();
}
