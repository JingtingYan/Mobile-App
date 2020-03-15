package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;

import java.util.List;

@Dao
public interface PatientAssessmentStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PatientAssessmentStatusTable assessmentStatus);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PatientAssessmentStatusTable> assessmentStatusList);

    @Query("SELECT * FROM patient_assessment_status WHERE patient_id = :patientID")
    List<PatientAssessmentStatusTable> getAssessmentStatusForPatient(String patientID);

    @Query("SELECT MAX(`index`) FROM patient_assessment_status")
    int getLastIndex();
}
