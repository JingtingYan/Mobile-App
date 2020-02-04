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

    public Repository(Context ctx)
    {
        System.out.println("\n\nREPO: MAKING DATABASE\n\n");
        this.db = MobileAppDatabase.getDatabase(ctx);
        System.out.println("REPO: DATABASE MADE!!\n\n");
        this.locationDao = db.locationDao();
    }

    LiveData<List<Location>> getCountries()
    {
        List<Location> countries = new ArrayList<Location>();
        LiveData<List<Location>> result = new MutableLiveData<>(countries);
        List<LocationTable> locationTable = locationDao.getAllCountries();

        for(LocationTable location : locationTable)
        {
            countries.add(new Location(location.getLocation_id(), location.getLocation_name()));
        }

        countries.addAll(result.getValue());

        // Return all components of the Location, ViewModel extracts the location name
        return result;
    }

}
