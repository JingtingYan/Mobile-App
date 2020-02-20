package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileApp.database.entity.HouseholdTable;

import java.util.List;

@Dao
public interface HouseholdDao
{
    @Insert
    void insert(HouseholdTable household);

    @Query("SELECT * from households WHERE household_id = :hh_id")
    HouseholdTable getHouseholdforPatient(int hh_id);

    @Query("SELECT * from households WHERE parent_loc_id = :clusterId")
    List<HouseholdTable> getHouseholdsforCluster(int clusterId);

}
