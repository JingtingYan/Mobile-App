package com.example.mobileApp.datatype;

import javax.annotation.Nonnull;

public class Location {

    private Integer locationID;
    private String locationName;

    public Location(Integer locationID, String locationName) {
        this.locationID = locationID;
        this.locationName = locationName;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    // Override the toString() method because the ArrayAdapter will display
    // the toString of the given Location object.
    @Nonnull
    @Override
    public String toString() {
        return locationName;
    }
}
