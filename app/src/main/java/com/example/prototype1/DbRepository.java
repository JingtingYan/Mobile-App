package com.example.prototype1;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import java.util.List;

public class DbRepository
{
    private DbDao dao;
    private LiveData<List<Country>> allCountries;

    DbRepository(Application application) {

        LocationRoomDatabase db = LocationRoomDatabase.getDatabase(application);
        dao = db.dbDao();
        allCountries = dao.getAllCountries();
    }

    LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }

    LiveData<List<Region>> findRegions(Country country)
    {
        return dao.getRegionsByCountry(country.getCountryId());
    }

    LiveData<List<Cluster>> findClusters(Region region)
    {
        return dao.getClustersByRegions(region.getRegionId());
    }
}