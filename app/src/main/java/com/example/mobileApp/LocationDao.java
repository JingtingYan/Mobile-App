package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations WHERE parent_location_id = -1")
    public Location[] getAllCountries();

    @Query("SELECT * FROM locations WHERE parent_location_id = :countryID")
    public Location[] getRegions(int countryID);

    @Query("SELECT * FROM locations WHERE parent_location_id = :regionID")
    public Location[] getClusters(int regionID);
}
