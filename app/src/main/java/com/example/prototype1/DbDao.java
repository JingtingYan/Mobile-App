package com.example.prototype1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbDao
{
    @Insert
    void insertCountry(Country country);

    @Insert
    void insertRegion(Region region);

    @Insert
    void insertCluster(Cluster cluster);

    @Query("SELECT * FROM countries")
    LiveData<List<Country>> getAllCountries();

    @Query("SELECT * FROM regions WHERE parentLocId = :parentLocId")
    LiveData<List<Region>> getRegionsByCountry(int parentLocId);

    @Query("SELECT * FROM clusters WHERE parentLocId = :parentLocId")
    LiveData<List<Cluster>> getClustersByRegions(int parentLocId);
}
