package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HouseholdDao
{
    @Insert
    void insert(Household household);

    @Query("SELECT * from households WHERE households.household_id = :hh_id")
    Household getHouseholdforPatient(int hh_id);

    @Query("SELECT * from households WHERE households.parent_loc_id = :clusterId")
    List<Household> getHouseholdsforCluster(int clusterId);

}
