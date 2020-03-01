package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.AnswerTable;

import java.util.List;

@Dao
public interface AnswerDao {

    @Query("SELECT * FROM answers WHERE answer_id = :ansID AND qnnaire_id = :qnnID")
    AnswerTable getAnswer(int ansID, int qnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AnswerTable> answers);

    @Query("DELETE FROM answers")
    void deleteAll();
}
