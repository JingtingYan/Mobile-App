package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionTable;

import java.time.LocalDate;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions WHERE questions.question_id = :q_id AND questions.qnnaire_id = :qnnaire_id")
    QuestionTable getQuestion(int q_id, int qnnaire_id);

    //LocalDate date = LocalDate.now();
}
