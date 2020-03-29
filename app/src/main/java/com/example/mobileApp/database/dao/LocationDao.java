package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.LocationTable;

import java.util.List;

/**
 * The LocationDao interface is a mapping of some SQL queries (for LocationTable) to
 * Java functions that can be called in MobileAppRepository.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations WHERE parent_location_id = -1")
    List<LocationTable> getAllCountries();

    @Query("SELECT * FROM locations WHERE parent_location_id = :countryID")
    List<LocationTable> getRegions(int countryID);

    @Query("SELECT * FROM locations WHERE parent_location_id = :regionID")
    List<LocationTable> getClusters(int regionID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LocationTable> locations);

    @Query("DELETE FROM locations")
    void deleteAll();

    // debugging - used in DatabaseTest
    @Query("SELECT COUNT(*) FROM locations WHERE parent_location_id = -1")
    int countCountries();
}
