package com.example.prototype1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class Country {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int countryId;

    @NonNull
    @ColumnInfo(name = "name")
    private String countryName;

//    put a 0 for the id when creating the object -> means "id not yet set"
    public Country(int countryId, String countryName)
    {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public String getCountryName() {
        return this.countryName;
    }
}
