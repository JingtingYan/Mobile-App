package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations",
        indices = {@Index("parent_location_id")})
public class LocationTable {

    @PrimaryKey
    //    REQUIRED
    @NonNull
    private Integer location_id;

    private String location_name;

    //    REQUIRED
    private Integer parent_location_id;


    public LocationTable(@NonNull Integer location_id, String location_name, Integer parent_location_id) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.parent_location_id = parent_location_id;
    }


    /* getter */

    @NonNull
    public Integer getLocation_id() {
        return location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public Integer getParent_location_id() {
        return parent_location_id;
    }
}
