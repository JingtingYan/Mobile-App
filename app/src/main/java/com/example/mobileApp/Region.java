package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "regions", foreignKeys = @ForeignKey(entity = Country.class,
                                                        parentColumns = "id",
                                                        childColumns = "parentLocId",
                                                        onDelete = CASCADE,
                                                        onUpdate = CASCADE),
                                                        indices = {@Index(value = "parentLocId")})
public class Region {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int regionId;

    @NonNull
    @ColumnInfo(name = "name")
    private String regionName;

    @NonNull
    @ColumnInfo(name = "parentLocId")
    private int parentLocId;

    public Region(int regionId, String regionName, int parentLocId)
    {
        this.regionId = regionId;
        this.regionName = regionName;
        this.parentLocId = parentLocId;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public int getParentLocId() {
        return this.parentLocId;
    }
}
