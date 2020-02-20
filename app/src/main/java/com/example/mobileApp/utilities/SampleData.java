package com.example.mobileApp.utilities;

import com.example.mobileApp.database.entity.LocationTable;

import java.util.ArrayList;
import java.util.List;

/* Pre-populate the database at the development stage -- will be removed after data sync */

public class SampleData {

    public static List<LocationTable> getLocations() {
        List<LocationTable> locations = new ArrayList<>();

        // pre-populate countries
        locations.add(new LocationTable(1, "Federal Repubic of Nigeria", -1));
        locations.add(new LocationTable(2,"India", -1));
        locations.add(new LocationTable(3,"Phillipenes", -1));
        locations.add(new LocationTable(4,"Indonesia", -1));

        // pre-populate regions
        locations.add(new LocationTable(5,"Ado", 1));
        locations.add(new LocationTable(6,"Bauchi", 1));
        locations.add(new LocationTable(7,"Sokoto", 1));
        locations.add(new LocationTable(8,"Srinigar", 2));
        locations.add(new LocationTable(9,"Chandigarh", 2));
        locations.add(new LocationTable(10,"Simla", 2));
        locations.add(new LocationTable(11,"Manila", 3));
        locations.add(new LocationTable(12,"Java", 4));
        locations.add(new LocationTable(13,"Sumatera", 4));
        locations.add(new LocationTable(14,"Palembang", 4));

        // pre-populate clusters
        locations.add(new LocationTable(15,"Cluster A1", 5));
        locations.add(new LocationTable(16,"Cluster A2", 5));
        locations.add(new LocationTable(17,"Cluster B1", 5));
        locations.add(new LocationTable(18,"Cluster A1", 6));
        locations.add(new LocationTable(19,"Cluster A2", 6));
        locations.add(new LocationTable(20,"Cluster A3", 6));

        return locations;
    }
}
