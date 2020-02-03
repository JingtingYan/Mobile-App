package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResponseDao
{
    @Insert
    void insert(ResponseTable response);

    @Query("DELETE FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :questionID AND responses.qnnaire_id = :qnnaireID")
    void deleteResponse(int patientID, int questionID, int qnnaireID);

//    Used in "next" stored procedure
    @Query("SELECT * FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :currentQuestion AND responses.qnnaire_id = :currentQnn")
    List<ResponseTable> getResponse(int patientID, int currentQuestion, int currentQnn);


    @Query("SELECT COUNT(*) FROM responses WHERE responses.patient_id = :patientID AND responses.q_id = :currentQuestion AND responses.qnnaire_id = :currentQnn")
    int getResponseCount(int patientID, int currentQuestion, int currentQnn);
}
