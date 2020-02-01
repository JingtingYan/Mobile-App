package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;


@Entity(tableName = "enumerators")
public class Enumerator {

    @PrimaryKey(autoGenerate = true)
    private int enumerator_id;

    private String enum_prefix;

    @NonNull private String enum_first;

    private String enum_middle;

    @NonNull private String enum_last;

    private String enum_suffix;

    private String organization;

    @NonNull private LocalDate dob;

    // set default value of active_flag to be false
    @NonNull private boolean active_flag = false;

    private String qualifications;


    /* getter & setter */

    public int getEnum_id() {
        return enumerator_id;
    }

    public void setEnum_id(int enum_id) {
        this.enumerator_id = enum_id;
    }

    public String getEnum_prefix() {
        return enum_prefix;
    }

    public void setEnum_prefix(String enum_prefix) {
        this.enum_prefix = enum_prefix;
    }

    @NonNull
    public String getEnum_first() {
        return enum_first;
    }

    public void setEnum_first(@NonNull String enum_first) {
        this.enum_first = enum_first;
    }

    public String getEnum_middle() {
        return enum_middle;
    }

    public void setEnum_middle(String enum_middle) {
        this.enum_middle = enum_middle;
    }

    @NonNull
    public String getEnum_last() {
        return enum_last;
    }

    public void setEnum_last(@NonNull String enum_last) {
        this.enum_last = enum_last;
    }

    public String getEnum_suffix() {
        return enum_suffix;
    }

    public void setEnum_suffix(String enum_suffix) {
        this.enum_suffix = enum_suffix;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @NonNull
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(@NonNull LocalDate dob) {
        this.dob = dob;
    }

    public boolean isActive_flag() {
        return active_flag;
    }

    public void setActive_flag(boolean active_flag) {
        this.active_flag = active_flag;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
