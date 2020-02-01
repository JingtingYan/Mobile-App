package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations WHERE parent_location_id = -1")
    List<Location> getAllCountries();

    @Query("SELECT * FROM locations WHERE parent_location_id = :countryID")
    List<Location> getRegions(int countryID);

    @Query("SELECT * FROM locations WHERE parent_location_id = :regionID")
    List<Location> getClusters(int regionID);
}
