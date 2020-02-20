package com.example.mobileApp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileApp.database.entity.LocationTable;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations WHERE parent_location_id = -1")
    List<LocationTable> getAllCountries();

    // debug
    @Query("SELECT COUNT(*) FROM locations WHERE parent_location_id = -1")
    int countCountries();

    @Query("SELECT * FROM locations WHERE parent_location_id = :countryID")
    List<LocationTable> getRegions(int countryID);

    @Query("SELECT * FROM locations WHERE parent_location_id = :regionID")
    List<LocationTable> getClusters(int regionID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LocationTable> locations);

    @Query("DELETE FROM locations")
    void deleteAll();
}
