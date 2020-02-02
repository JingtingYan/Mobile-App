package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Query;

import java.time.LocalDate;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions WHERE questions.question_id = :q_id AND questions.qnnaire_id = :qnnaire_id")
    Question getQuestion(int q_id, int qnnaire_id);

    LocalDate date = LocalDate.now();
}
