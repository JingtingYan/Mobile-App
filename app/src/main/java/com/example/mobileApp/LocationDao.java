package com.example.mobileApp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM location WHERE parent_loc_id = -1")
    public Location[] getAllCountries();

    @Query("SELECT * FROM location WHERE parent_loc_id = :countryID")
    public Location[] getRegions(int countryID);

    @Query("SELECT * FROM location WHERE parent_loc_id = :regionID")
    public Location[] getClusters(int regionID);
}
