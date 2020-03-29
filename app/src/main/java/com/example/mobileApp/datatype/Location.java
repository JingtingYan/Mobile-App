package com.example.mobileApp.datatype;

import javax.annotation.Nonnull;

/**
 * The Location class is a user-defined data type which contains more general data fields than the LocationTable class.
 * It is used to hold data related to a single country/region/cluster.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
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

    // Override the toString() method to return the location name of a given Location object.
    @Nonnull
    @Override
    public String toString() {
        return locationName;
    }
}
