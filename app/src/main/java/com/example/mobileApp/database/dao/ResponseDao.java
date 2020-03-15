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

    @Query("DELETE FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :questionID AND responses.qnnaire_id = :qnnaireID")
    void deleteResponse(int patientID, int questionID, int qnnaireID);

//    Used in "next" stored procedure
    @Query("SELECT * FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :currentQuestion AND responses.qnnaire_id = :currentQnn")
    List<ResponseTable> getResponse(int patientID, int currentQuestion, int currentQnn);

    @Query("SELECT COUNT(*) FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :currentQuestion AND responses.qnnaire_id = :currentQnn")
    int getResponseCount(int patientID, int currentQuestion, int currentQnn);

//    @Query("SELECT COUNT(*) FROM responses")
//    int countAllResponses();

    @Query("SELECT MAX(`index`) FROM responses")
    int getLastIndex();

    @Query("SELECT * FROM responses")
    List<ResponseTable> getAllResponses();

    @Query("DELETE FROM responses")
    void deleteAll();

    /**
     *
     * @param patientID
     * @param currQnID
     * @param currQnnID
     * @return
     */
    @Query("SELECT ans_id FROM responses AS resp WHERE resp.patient_id = :patientID AND resp.q_id = :currQnID AND resp.qnnaire_id = :currQnnID")
    List<Integer> getResponsesForCurrQuestion(String patientID, int currQnID, int currQnnID);
}
