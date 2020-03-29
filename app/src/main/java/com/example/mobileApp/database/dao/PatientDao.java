package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.PatientTable;

import java.util.List;

/**
 * The PatientDao interface is a mapping of some SQL queries (for PatientTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PatientTable patient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PatientTable> patients);

    @Query("SELECT * FROM patients")
    List<PatientTable> getAllPatients();

    @Query("SELECT * FROM patients WHERE hh_id = :hh_id")
    List<PatientTable> getPatientsForHousehold(String hh_id);

    @Query("SELECT * FROM patients WHERE patient_id = :patientID")
    PatientTable getSinglePatient(String patientID);

    @Query("SELECT * FROM patients WHERE isNew = 1")
    List<PatientTable> getAllPatientsToUpload();

    @Query("SELECT COUNT(*) FROM patients")
    Integer countAllPatients();

    @Query("DELETE FROM patients WHERE patient_id = :patientID")
    void deleteSinglePatient(String patientID);

    @Query("DELETE FROM patients")
    void deleteAll();
}