package com.example.mobileApp;

public class Location {

    private Integer location_id;
    private String location_name;

    public Location(Integer location_id, String location_name) {
        this.location_id = location_id;
        this.location_name = location_name;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public String getLocation_name() {
        return location_name;
    }
}
