package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionnaireTable;

import java.util.List;

@Dao
public interface QuestionnaireDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionnaireTable> questionnaires);

    @Query("DELETE FROM questionnaires")
    void deleteAll();

    @Query("SELECT * FROM questionnaires WHERE questionnaire_id = :qnnID")
    QuestionnaireTable getSingleQuestionnaireInfo(Integer qnnID);
}
