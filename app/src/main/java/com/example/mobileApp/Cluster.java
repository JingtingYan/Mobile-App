package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "clusters", foreignKeys = @ForeignKey(entity = Region.class,
                                                        parentColumns = "id",
                                                        childColumns = "parentLocId",
                                                        onDelete = CASCADE,
                                                        onUpdate = CASCADE),
                                                        indices = {@Index(value = "parentLocId")})
public class Cluster {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int clusterId;

    @NonNull
    @ColumnInfo(name = "name")
    private String clusterName;

    @NonNull
    @ColumnInfo(name = "parentLocId")
    private int parentLocId;

    public Cluster(int clusterId, String clusterName, int parentLocId)
    {
        this.clusterId = clusterId;
        this.clusterName = clusterName;
        this.parentLocId = parentLocId;
    }

    public int getClusterId() {
        return this.clusterId;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public int getParentLocId() {
        return this.parentLocId;
    }
}
