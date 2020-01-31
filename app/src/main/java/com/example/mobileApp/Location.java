package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location")
public class Location {

    @PrimaryKey(autoGenerate = true)
    private int location_id;

    @NonNull private String location_name;

    @NonNull private int parent_loc_id;

    public int getLocId()
    {
        return this.location_id;
    }

    public void setLocId(int locId)
    {
        this.location_id = locId;
    }

    public String getLocationName()
    {
        return this.location_name;
    }

    public void setLocationName(String locationName)
    {
        this.location_name = locationName;
    }

    public int getParentLocId()
    {
        return this.parent_loc_id;
    }

    public void setParentLocId(int parentLocId)
    {
        this.parent_loc_id = parentLocId;
    }

}
