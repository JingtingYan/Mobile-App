package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "households",
        indices = {@Index("parent_loc_id"),
                    @Index("enum_id")})     // remove foreign keys
public class HouseholdTable {

    @PrimaryKey
//    REQUIRED
    @NonNull
    private String household_id;

    // this is a foreign key
    //    REQUIRED
    private Integer parent_loc_id;

    // this is a foreign key
    //    REQUIRED
    private String enum_id;

    // date is in format "YYYY-MM-DD"
    //    REQUIRED
    private String date;

    private String village_street_name;

    private String gps_latitude;

    private String gps_longitude;

    //    REQUIRED
    private String availability;

    private String reason_refusal;

    private Integer visit_num;

    //    REQUIRED
    private String key_informer;

    private String tel1_num;

    private String tel1_owner;

    private String tel2_num;

    private String tel2_owner;

    private String consent;

    //    REQUIRED
    private String a2_q1;

    //    REQUIRED
    private String a2_q2;

    //    REQUIRED
    private String a2_q3;

    //    REQUIRED
    private String a2_q4;

    //    REQUIRED
    private String a2_q5;

    //    REQUIRED
    private String a2_q6;

    //    REQUIRED
    private String a2_q7;

    //    REQUIRED
    private String a2_q8;

    //    REQUIRED
    private String a2_q9;

    //    REQUIRED
    private String a2_q10;

    //    REQUIRED
    private String a2_q11;

    //    REQUIRED
    private String a2_q12;

    //    REQUIRED
    private String a2_q13;

    //    REQUIRED
    // This field is created to support data sync
    // 0 - means this entry is downloaded from server database and will not be synced later
    // 1 - means this entry is newly created on the mobile app and will be synced later
    private Integer isNew;


    // select several main attributes as the constructor for household
    public HouseholdTable(@NonNull String household_id, Integer parent_loc_id, String enum_id,
                          String date, String gps_latitude, String gps_longitude, Integer isNew) {
        this.household_id = household_id;
        this.parent_loc_id = parent_loc_id;
        this.enum_id = enum_id;
        this.date = date;
        this.gps_latitude = gps_latitude;
        this.gps_longitude = gps_longitude;
        this.isNew = isNew;
    }

    /* getter and setter */

    @NonNull
    public String getHousehold_id() {
        return household_id;
    }

    public Integer getParent_loc_id() {
        return parent_loc_id;
    }

    public String getEnum_id() {
        return enum_id;
    }

    public String getDate() {
        return date;
    }

    public String getVillage_street_name() {
        return village_street_name;
    }

    public void setVillage_street_name(String village_street_name) {
        this.village_street_name = village_street_name;
    }

    public String getGps_latitude() {
        return gps_latitude;
    }

    public String getGps_longitude() {
        return gps_longitude;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getReason_refusal() {
        return reason_refusal;
    }

    public void setReason_refusal(String reason_refusal) {
        this.reason_refusal = reason_refusal;
    }

    public Integer getVisit_num() {
        return visit_num;
    }

    public void setVisit_num(Integer visit_num) {
        this.visit_num = visit_num;
    }

    public String getKey_informer() {
        return key_informer;
    }

    public void setKey_informer(String key_informer) {
        this.key_informer = key_informer;
    }

    public String getTel1_num() {
        return tel1_num;
    }

    public void setTel1_num(String tel1_num) {
        this.tel1_num = tel1_num;
    }

    public String getTel1_owner() {
        return tel1_owner;
    }

    public void setTel1_owner(String tel1_owner) {
        this.tel1_owner = tel1_owner;
    }

    public String getTel2_num() {
        return tel2_num;
    }

    public void setTel2_num(String tel2_num) {
        this.tel2_num = tel2_num;
    }

    public String getTel2_owner() {
        return tel2_owner;
    }

    public void setTel2_owner(String tel2_owner) {
        this.tel2_owner = tel2_owner;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String consent) {
        this.consent = consent;
    }

    public String getA2_q1() {
        return a2_q1;
    }

    public void setA2_q1(String a2_q1) {
        this.a2_q1 = a2_q1;
    }

    public String getA2_q2() {
        return a2_q2;
    }

    public void setA2_q2(String a2_q2) {
        this.a2_q2 = a2_q2;
    }

    public String getA2_q3() {
        return a2_q3;
    }

    public void setA2_q3(String a2_q3) {
        this.a2_q3 = a2_q3;
    }

    public String getA2_q4() {
        return a2_q4;
    }

    public void setA2_q4(String a2_q4) {
        this.a2_q4 = a2_q4;
    }

    public String getA2_q5() {
        return a2_q5;
    }

    public void setA2_q5(String a2_q5) {
        this.a2_q5 = a2_q5;
    }

    public String getA2_q6() {
        return a2_q6;
    }

    public void setA2_q6(String a2_q6) {
        this.a2_q6 = a2_q6;
    }

    public String getA2_q7() {
        return a2_q7;
    }

    public void setA2_q7(String a2_q7) {
        this.a2_q7 = a2_q7;
    }

    public String getA2_q8() {
        return a2_q8;
    }

    public void setA2_q8(String a2_q8) {
        this.a2_q8 = a2_q8;
    }

    public String getA2_q9() {
        return a2_q9;
    }

    public void setA2_q9(String a2_q9) {
        this.a2_q9 = a2_q9;
    }

    public String getA2_q10() {
        return a2_q10;
    }

    public void setA2_q10(String a2_q10) {
        this.a2_q10 = a2_q10;
    }

    public String getA2_q11() {
        return a2_q11;
    }

    public void setA2_q11(String a2_q11) {
        this.a2_q11 = a2_q11;
    }

    public String getA2_q12() {
        return a2_q12;
    }

    public void setA2_q12(String a2_q12) {
        this.a2_q12 = a2_q12;
    }

    public String getA2_q13() {
        return a2_q13;
    }

    public void setA2_q13(String a2_q13) {
        this.a2_q13 = a2_q13;
    }

    public Integer getIsNew() {
        return isNew;
    }
}
