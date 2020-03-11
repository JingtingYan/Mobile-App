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
    HouseholdTable getHouseholdForPatient(String hh_id);

    @Query("SELECT * from households WHERE parent_loc_id = :clusterId")
    List<HouseholdTable> getHouseholdsForCluster(int clusterId);

    @Query("SELECT COUNT(*) FROM households")
    int countAllHouseholds();

    @Query("SELECT * FROM households")
    List<HouseholdTable> getAllHouseholds();

    @Query("DELETE FROM households")
    void deleteAll();

    @Query("DELETE FROM households WHERE household_id = :hh_id")
    void deleteSingleHousehold(String hh_id);
}
