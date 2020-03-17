package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.ResponseTable;

import java.util.List;

@Dao
public interface ResponseDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ResponseTable response);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ResponseTable> responses);

    @Query("DELETE FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :questionID AND " +
            "responses.ans_id = :answerID AND responses.text = :answerText AND responses.qnnaire_id = :qnnaireID AND " +
            "responses.date = :date")
    void deleteSingleResponse(String patientID, Integer questionID, Integer answerID, String answerText, Integer qnnaireID, String date);

//    Used in "next" stored procedure
    @Query("SELECT * FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :currentQuestion AND responses.qnnaire_id = :currentQnn")
    List<ResponseTable> getResponse(int patientID, int currentQuestion, int currentQnn);

    @Query("SELECT COUNT(*) FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :currentQuestion AND responses.qnnaire_id = :currentQnn")
    int getResponseCount(int patientID, int currentQuestion, int currentQnn);

    @Query("SELECT MAX(`index`) FROM responses")
    int getLastIndex();

    @Query("SELECT * FROM responses")
    List<ResponseTable> getAllResponses();

    @Query("DELETE FROM responses")
    void deleteAll();

    @Query("SELECT ans_id FROM responses AS resp WHERE resp.patient_id = :patientID AND resp.q_id = :currQnID AND resp.qnnaire_id = :currQnnID")
    List<Integer> getResponsesForCurrQuestion(String patientID, int currQnID, int currQnnID);
}
