package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.QuestionRelationTable;

import java.util.List;

/**
 * The QuestionRelationDao interface is a mapping of some SQL queries (for QuestionRelationTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Nadhirah Rafidz
 *  @version 1.0
 *  @since February 2020
 */

@Dao
public interface QuestionRelationDao {

    // Used in AND logic and OR logic -> questions cursor -> each element is checkQuestion element used in responseCount()
    @Query("SELECT DISTINCT rel_q_id FROM question_relations AS qrel " +
            "WHERE qrel.rel_id = :relID " +
            "AND qrel.q_id = :currQnID " +
            "AND qrel.qnnaire_id = :currQnnID")
    List<Integer> getDistinctRelQID(int relID, int currQnID, int currQnnID);

    @Query("SELECT COUNT(*) FROM question_relations AS qrel " +
            "WHERE qrel.rel_id = :relID " +
            "AND qrel.q_id = :currQnID " +
            "AND qrel.qnnaire_id = :currQnnID")
    int getCorrectCount(int relID, int currQnID, int currQnnID);

    // Used in AND logic and OR logic
    @Query("SELECT COUNT(resp.q_id) FROM responses AS resp " +
            "INNER JOIN question_relations AS qrel " +
            "ON resp.q_id = qrel.rel_q_id " +
            "AND resp.ans_id = qrel.rel_ans_id " +
            "WHERE resp.patient_id = :patientID " +
            "AND resp.q_id = :checkQuestion " +
            "AND resp.qnnaire_id = :currQnnID")
    int getResponseCount(String patientID, int checkQuestion, int currQnnID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionRelationTable> questionRelations);

    @Query("DELETE FROM question_relations")
    void deleteAll();
}
