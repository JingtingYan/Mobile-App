package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionTable;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions WHERE questions.question_id = :q_id AND questions.qnnaire_id = :qnnaire_id")
    QuestionTable getQuestion(int q_id, int qnnaire_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionTable> questions);

    @Query("DELETE FROM questions")
    void deleteAll();
}
