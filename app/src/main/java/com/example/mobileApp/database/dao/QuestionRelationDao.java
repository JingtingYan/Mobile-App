package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionRelationDao
{

//    Used in andLogic and orLogic -> questions cursor -> each element is checkQuestion element used in responseCount()
    @Query("SELECT DISTINCT rel_q_id FROM question_relations AS qrel " +
            "WHERE qrel.rel_id = :relID " +
            "AND qrel.q_id = :currQ " +
            "AND qrel.qnnaire_id = :currQnn")
    List<Integer> getDistinctRelQID(int relID, int currQ, int currQnn);

    @Query("SELECT COUNT(*) FROM question_relations AS qrel " +
            "WHERE qrel.rel_id = :relID " +
            "AND qrel.q_id = :currQ " +
            "AND qrel.qnnaire_id = :currQnn")
    int correctCount(int relID, int currQ, int currQnn);

//    Used in andLogic and orLogic
    @Query("SELECT COUNT(resp.q_id) FROM responses AS resp " +
            "INNER JOIN question_relations AS qrel " +
            "ON resp.q_id = qrel.rel_q_id " +
            "AND resp.ans_id = qrel.rel_ans_id " +
            "WHERE resp.patient_id = :patientID " +
            "AND resp.q_id = :checkQuestion " +
            "AND resp.qnnaire_id = :currQnn")
    int responseCount(int patientID, int checkQuestion, int currQnn);


}
