package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
