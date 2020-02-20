package com.example.mobileApp.database;

import android.content.Context;

import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.datatype.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* MobileAppRepository class is singleton */

public class MobileAppRepository {

    private static MobileAppRepository ourInstance;

    private MobileAppDatabase db;
    private ExecutorService executor = Executors.newFixedThreadPool(1);


    public static MobileAppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MobileAppRepository(context);
        }
        return ourInstance;
    }

    private MobileAppRepository(Context context) {
        db = MobileAppDatabase.getDatabase(context);
    }


    public List<Location> getSpinnerCountries() throws ExecutionException, InterruptedException {
        Future<List<Location>> task = executor.submit(() -> {
            List<LocationTable> locationTables = db.locationDao().getAllCountries();
            List<Location> results = new ArrayList<>();

            for (LocationTable location : locationTables) {
                results.add(new Location(location.getLocation_id(), location.getLocation_name()));
            }
            return results;
        });

        return task.get();
    }

    public List<Location> getSpinnerRegions(int countryID) throws ExecutionException, InterruptedException {
        Future<List<Location>> task = executor.submit(() -> {
            List<LocationTable> locationTables = db.locationDao().getRegions(countryID);
            List<Location> results = new ArrayList<>();

            for (LocationTable location : locationTables) {
                results.add(new Location(location.getLocation_id(), location.getLocation_name()));
            }

            return results;
        });

        return task.get();
    }

    public List<Location> getSpinnerClusters(int regionID) throws ExecutionException, InterruptedException {
        Future<List<Location>> task = executor.submit(() -> {
            List<LocationTable> locationTables = db.locationDao().getClusters(regionID);
            List<Location> results = new ArrayList<>();

            for (LocationTable location : locationTables) {
                results.add(new Location(location.getLocation_id(), location.getLocation_name()));
            }

            return results;
        });

        return task.get();
    }


    public void deleteLocationData() {
        executor.execute(() -> db.locationDao().deleteAll());
    }

    public void addLocationData(String jsonArray) throws JSONException {
        List<LocationTable> locationTables = parseLocationJSONArray(jsonArray);
        executor.execute(() -> db.locationDao().insertAll(locationTables));
    }

    private List<LocationTable> parseLocationJSONArray(String jsonArray) throws JSONException {
        List<LocationTable> locationTables = new ArrayList<>();
        JSONArray locations = new JSONArray(jsonArray);

        for (int i = 0; i < locations.length(); i++) {
            JSONObject location = locations.getJSONObject(i);
            Integer location_id = location.getInt("locationID");
            String location_name = location.getString("locationName");
            Integer parent_location_id = location.getInt("parentLocID");

            locationTables.add(new LocationTable(location_id, location_name, parent_location_id));
        }

        return locationTables;
    }
}
