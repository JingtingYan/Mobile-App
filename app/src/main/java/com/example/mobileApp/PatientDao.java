package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao
{
    @Insert
    void insert(Patient patient);


    @Query("SELECT * FROM patients WHERE patients.hh_id = :hh_id")
    List<Patient> getPatientsforHousehold(int hh_id);

}