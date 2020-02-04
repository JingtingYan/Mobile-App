package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("INSERT INTO locations (location_id, location_name, parent_location_id) VALUES (:location_id, :location_name, :parent_loc_id)")
    void insertLocation(Integer location_id, String location_name, Integer parent_loc_id);

    @Query("SELECT * FROM locations WHERE parent_location_id = -1")
    List<LocationTable> getAllCountries();

    @Query("SELECT * FROM locations WHERE parent_location_id = :countryID")
    List<LocationTable> getRegions(int countryID);

    @Query("SELECT * FROM locations WHERE parent_location_id = :regionID")
    List<LocationTable> getClusters(int regionID);
}
