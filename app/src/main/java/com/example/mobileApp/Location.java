package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")
public class Location {

    @PrimaryKey(autoGenerate = true)
    @NonNull private int location_id;

    @NonNull private String location_name;

    @NonNull private int parent_location_id;


    /* getter and setter */

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    @NonNull
    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(@NonNull String location_name) {
        this.location_name = location_name;
    }

    public int getParent_location_id() {
        return parent_location_id;
    }

    public void setParent_location_id(int parent_location_id) {
        this.parent_location_id = parent_location_id;
    }

}
