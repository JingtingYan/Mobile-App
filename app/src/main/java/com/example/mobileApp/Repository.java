package com.example.mobileApp;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository
{
    private LocationDao locationDao;
    private MutableLiveData<List<Location>> countries;
    private MutableLiveData<List<Location>> regions;
    private MutableLiveData<List<Location>> clusters;

    public Repository(Context ctx) {
        MobileAppDatabase db = MobileAppDatabase.getDatabase(ctx);
        this.locationDao = db.locationDao();
    }

    MutableLiveData<List<Location>> getSpinnerCountries() {
        List<Location> locations = new ArrayList<>();
        List<LocationTable> locationTables = locationDao.getAllCountries();

        for (LocationTable location : locationTables) {
            locations.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        countries = new MutableLiveData<>(locations);
        return countries;

        /*
        List<Location> countries = new ArrayList<Location>();
        LiveData<List<Location>> result = new MutableLiveData<>(countries);
        List<LocationTable> locationTables = locationDao.getAllCountries();

        for(LocationTable location : locationTables) {
            countries.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        countries.addAll(result.getValue());

        // Return all components of the Location, ViewModel extracts the location name
        return result;
         */
    }

    MutableLiveData<List<Location>> getSpinnerRegions(int countryID) {
        List<Location> locations = new ArrayList<>();
        List<LocationTable> locationTables = locationDao.getRegions(countryID);

        for(LocationTable location : locationTables) {
            locations.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        regions = new MutableLiveData<>(locations);
        return regions;
    }

    MutableLiveData<List<Location>> getSpinnerClusters(int regionID) {
        List<Location> locations = new ArrayList<>();
        List<LocationTable> locationTables = locationDao.getClusters(regionID);

        for(LocationTable location : locationTables) {
            locations.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        clusters = new MutableLiveData<>(locations);
        return clusters;
    }

    /* db pre-populate?
    // call the insertion operation on a background thread
    void insert(LocationTable location) {
        MobileAppDatabase.databaseWriteExecutor.execute(() -> {
            locationDao.insert(location);
        });
    }
     */
}
