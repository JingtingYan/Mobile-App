package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionAnswerTable;

import java.util.List;

@Dao
public interface QuestionAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionAnswerTable> qas);

    @Query("DELETE FROM questions_and_answers")
    void deleteAll();
}
