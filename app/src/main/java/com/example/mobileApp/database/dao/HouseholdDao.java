package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.HouseholdTable;

import java.util.List;

/**
 * The HouseholdDao interface is a mapping of some SQL queries (for HouseholdTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface HouseholdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HouseholdTable household);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll (List<HouseholdTable> households);

    @Query("SELECT * from households WHERE household_id = :hh_id")
    HouseholdTable getHouseholdForPatient(String hh_id);

    @Query("SELECT * from households WHERE parent_loc_id = :clusterId")
    List<HouseholdTable> getHouseholdsForCluster(int clusterId);

    @Query("SELECT COUNT(*) FROM households")
    int countAllHouseholds();

    @Query("SELECT * FROM households WHERE isNew = 1")
    List<HouseholdTable> getAllHouseholdsToUpload();

    @Query("DELETE FROM households")
    void deleteAll();

    @Query("DELETE FROM households WHERE household_id = :hh_id")
    void deleteSingleHousehold(String hh_id);
}
