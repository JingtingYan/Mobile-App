package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

// The EnumeratorTable has NOT been used so far.

@Entity(tableName = "enumerators")
public class EnumeratorTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer enumerator_id = 0;

    private String enum_prefix;

//    REQUIRED
    private String enum_first;

    private String enum_middle;

//    REQUIRED
    private String enum_last;

    private String enum_suffix;

    private String organization;

//    REQUIRED
    private LocalDate dob;

    // set default value of active_flag to be false
//    REQUIRED
    private Integer active_flag = 0;

    private String qualifications;


    /* getter & setter */

    @NonNull
    public Integer getEnumerator_id() {
        return enumerator_id;
    }

    public void setEnumerator_id(@NonNull Integer enumerator_id) {
        this.enumerator_id = enumerator_id;
    }

    public String getEnum_prefix() {
        return enum_prefix;
    }

    public void setEnum_prefix(String enum_prefix) {
        this.enum_prefix = enum_prefix;
    }

    public String getEnum_first() {
        return enum_first;
    }

    public void setEnum_first(String enum_first) {
        this.enum_first = enum_first;
    }

    public String getEnum_middle() {
        return enum_middle;
    }

    public void setEnum_middle(String enum_middle) {
        this.enum_middle = enum_middle;
    }

    public String getEnum_last() {
        return enum_last;
    }

    public void setEnum_last(String enum_last) {
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(Integer active_flag) {
        this.active_flag = active_flag;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
