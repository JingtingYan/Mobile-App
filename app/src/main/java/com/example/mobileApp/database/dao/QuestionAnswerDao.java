package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionAnswerTable;

import java.util.List;

/**
 * The QuestionAnswerDao interface is a mapping of some SQL queries (for QuestionAnswerTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface QuestionAnswerDao {

    // get answerID for each answer choice for a question
    @Query("SELECT DISTINCT ans_id FROM questions_and_answers AS qa " +
           "WHERE qa.q_id = :qnID " +
           "AND qa.qnnaire_id = :qnnID")
    List<Integer> getAllAnsID(int qnID, int qnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionAnswerTable> qas);

    @Query("DELETE FROM questions_and_answers")
    void deleteAll();

    @Query("SELECT MAX(`index`) FROM questions_and_answers")
    int getLastIndex();
}
