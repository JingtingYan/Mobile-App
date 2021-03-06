package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;

import java.util.List;

/**
 * The PatientAssessmentStatusDao interface is a mapping of some SQL queries (for PatientAssessmentStatusTable)
 * to Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface PatientAssessmentStatusDao {

    @Query("SELECT * FROM patient_assessment_status WHERE patient_id = :patientID")
    List<PatientAssessmentStatusTable> getAssessmentStatusForPatient(String patientID);

    @Query("SELECT MAX(`index`) FROM patient_assessment_status")
    int getLastIndex();

    @Query("SELECT * FROM patient_assessment_status WHERE patient_id = :patientID AND qnnaire_id = :qnnID AND start = :startDate")
    PatientAssessmentStatusTable findExistingAssessment(String patientID, int qnnID, String startDate);

    @Query("SELECT * FROM patient_assessment_status")
    List<PatientAssessmentStatusTable> getAllAssessmentStatus();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PatientAssessmentStatusTable assessmentStatus);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PatientAssessmentStatusTable> assessmentStatusList);

    @Query("DELETE FROM patient_assessment_status WHERE patient_id = :patientID AND qnnaire_id = :qnnID AND start = :startDate")
    void deleteSingleAssessmentStatus(String patientID, int qnnID, String startDate);

    @Query("DELETE FROM patient_assessment_status")
    void deleteAll();
}
