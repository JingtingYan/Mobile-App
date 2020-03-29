package com.example.mobileApp.datatype;

/**
 * The HouseholdRecyclerViewItem class is a user-defined data type which contains more general data fields
 * than the HouseholdTable class.
 * It is used to hold data related to a single household.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class HouseholdRecyclerViewItem {

    private String householdID;
    private String householdLocation;
    private String householdKeyInformant;

    public HouseholdRecyclerViewItem(String householdID, String householdLocation, String householdKeyInformant) {
        this.householdID = householdID;
        this.householdLocation = householdLocation;
        this.householdKeyInformant = householdKeyInformant;
    }

    public String getHouseholdID() {
        return householdID;
    }

    public String getHouseholdLocation() {
        return householdLocation;
    }

    public String getHouseholdKeyInformant() {
        return householdKeyInformant;
    }

    public String getHouseholdInfoToFilter() {
        return householdLocation + " " + householdKeyInformant;
    }
}
