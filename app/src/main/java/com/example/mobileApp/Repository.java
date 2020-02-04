package com.example.mobileApp;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository
{
    private MobileAppDatabase db;
    private LocationDao locationDao;

    public Repository(Context ctx) {
        this.db = MobileAppDatabase.getDatabase(ctx);
        this.locationDao = db.locationDao();
    }

    LiveData<List<Location>> getCountries() {
        List<Location> countries = new ArrayList<Location>();
        LiveData<List<Location>> result = new MutableLiveData<>(countries);
        List<LocationTable> locationTables = locationDao.getAllCountries();

        for(LocationTable location : locationTables) {
            countries.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        countries.addAll(result.getValue());

        // Return all components of the Location, ViewModel extracts the location name
        return result;
    }

    MutableLiveData<List<Location>> getRegions(int countryID) {
        List<Location> regions = new ArrayList<>();
        MutableLiveData<List<Location>> result = new MutableLiveData<>(regions);
        List<LocationTable> locationTables = locationDao.getRegions(countryID);

        for(LocationTable location : locationTables) {
            regions.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        regions.addAll(result.getValue());
        return result;
    }

    MutableLiveData<List<Location>> getClusters(int regionID) {
        List<Location> clusters = new ArrayList<>();
        MutableLiveData<List<Location>> result = new MutableLiveData<>(clusters);
        List<LocationTable> locationTables = locationDao.getClusters(regionID);

        for(LocationTable location : locationTables) {
            clusters.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        clusters.addAll(result.getValue());
        return result;
    }
}
