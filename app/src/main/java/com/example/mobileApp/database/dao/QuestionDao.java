package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionTable;

import java.util.List;

/**
 * The QuestionDao interface is a mapping of some SQL queries (for QuestionTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface QuestionDao {

    // get the QuestionTable object for a certain question in a certain questionnaire
    @Query("SELECT * FROM questions WHERE questions.question_id = :q_id AND questions.qnnaire_id = :qnnaire_id")
    QuestionTable getQuestion(int q_id, int qnnaire_id);

    // get the list of questions' IDs for a certain questionnaire
    @Query("SELECT DISTINCT question_id FROM questions WHERE qnnaire_id = :qnnID")
    List<Integer> getAllQnsID(int qnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionTable> questions);

    @Query("DELETE FROM questions")
    void deleteAll();
}
