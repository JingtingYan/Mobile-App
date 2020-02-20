package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileApp.database.entity.PatientTable;

import java.util.List;

@Dao
public interface PatientDao
{
    @Insert
    void insert(PatientTable patient);


    @Query("SELECT * FROM patients WHERE hh_id = :hh_id")
    List<PatientTable> getPatientsforHousehold(int hh_id);

}