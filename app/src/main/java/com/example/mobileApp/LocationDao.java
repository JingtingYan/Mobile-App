package com.example.mobileApp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations WHERE parent_location_id = -1")
    List<LocationTable> getAllCountries();

    @Query("SELECT * FROM locations WHERE parent_location_id = :countryID")
    List<LocationTable> getRegions(int countryID);

    @Query("SELECT * FROM locations WHERE parent_location_id = :regionID")
    List<LocationTable> getClusters(int regionID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocationTable location);

    @Query("DELETE FROM locations")
    void deleteAll();
}
