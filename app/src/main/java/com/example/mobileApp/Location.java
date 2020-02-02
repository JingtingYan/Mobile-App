package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "locations",
        indices = {@Index("parent_location_id")},
        foreignKeys = {@ForeignKey(entity = Location.class,
                                    parentColumns = "location_id",
                                    childColumns = "parent_location_id",
                                    onDelete = CASCADE,
                                    onUpdate = CASCADE)})
public class Location {

    @PrimaryKey(autoGenerate = true)
    //    REQUIRED
    @NonNull
    private Integer location_id = 0;

    private String location_name;

    //    REQUIRED
    private Integer parent_location_id;


    /* getter and setter */

    @NonNull
    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(@NonNull Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Integer getParent_location_id() {
        return parent_location_id;
    }

    public void setParent_location_id(Integer parent_location_id) {
        this.parent_location_id = parent_location_id;
    }
}
