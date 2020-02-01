package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResponseDao
{
    @Insert
    void insert(Response response);

    @Query("DELETE FROM response WHERE response.patient_id = :patientID AND response.q_id = :questionID AND response.qnnaire_id = :qnnaireID")
    void deleteResponse(int patientID, int questionID, int qnnaireID);

//    Used in "next" stored procedure
    @Query("SELECT * FROM response WHERE response.patient_id = :patientID AND response.q_id = :currentQuestion AND response.qnnaire_id = :currentQnn")
    List<Response> getResponse(int patientID, int currentQuestion, int currentQnn);


    @Query("SELECT COUNT(*) FROM response WHERE response.patient_id = :patientID AND response.q_id = :currentQuestion AND response.qnnaire_id = :currentQnn")
    int getResponseCount(int patientID, int currentQuestion, int currentQnn);
}
