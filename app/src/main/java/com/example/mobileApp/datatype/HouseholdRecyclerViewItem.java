package com.example.mobileApp.datatype;

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
