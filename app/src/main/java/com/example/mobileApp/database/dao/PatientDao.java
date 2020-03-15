package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.PatientTable;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PatientTable patient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PatientTable> patients);

    @Query("SELECT * FROM patients WHERE hh_id = :hh_id")
    List<PatientTable> getPatientsForHousehold(String hh_id);

    @Query("SELECT * FROM patients WHERE patient_id = :patientID")
    PatientTable getSinglePatient(String patientID);

    @Query("SELECT * FROM patients")
    List<PatientTable> getAllPatients();
}