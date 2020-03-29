package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionnaireTable;

import java.util.List;

/**
 * The QuestionnaireDao interface is a mapping of some SQL queries (for QuestionnaireTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface QuestionnaireDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionnaireTable> questionnaires);

    @Query("DELETE FROM questionnaires")
    void deleteAll();

    @Query("SELECT * FROM questionnaires WHERE questionnaire_id = :qnnID")
    QuestionnaireTable getSingleQuestionnaireInfo(Integer qnnID);

    @Query("SELECT questionnaire_id FROM questionnaires WHERE questionnaire_type = :questionnaireType")
    Integer getQuestionnaireID(String questionnaireType);
}
