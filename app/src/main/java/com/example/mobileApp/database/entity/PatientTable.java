package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients",
        indices = { @Index(value={"patient_id", "hh_id"}),
                    @Index("hh_id"),
                    @Index("enum_id")})     // remove foreign keys
public class PatientTable {

    @PrimaryKey
    //    REQUIRED
    @NonNull
    private String patient_id;

    //    REQUIRED
    private String study_id;

    //    REQUIRED
    private String date_of_birth = "0000-00-00";

    private String prefix;

    //    REQUIRED
    private String first_name;

    private String middle_name;

    //    REQUIRED
    private String last_name;

    private String suffix;

    private String com_name;

    //    REQUIRED
    private String gender;

    // this is a foreign key
    //    REQUIRED
    private String hh_id;

    //    REQUIRED
    private String dur_hh;

    private String lvl_edu;

    private String work_status;

    private String marital_status;

    private String mother_first;

    private String mother_last;

    private String tel1_num;

    private String tel1_owner;

    private String tel1_owner_rel;

    private String tel2_num;

    private String tel2_owner;

    private String tel2_owner_rel;

    // this is a foreign key
    //    REQUIRED
    private String enum_id;

    private String national_id;

    //    REQUIRED
    private String responder;

    private String proxy_name;

    private String proxy_rel;

    private String notes;

    // This two fields are created in case future usage
    private Integer deceased = 0;

    private String deceased_date = "0001-01-01";

    //    REQUIRED
    // This field is created to support data sync
    // 0 - means this entry is downloaded from server database and will not be synced later
    // 1 - means this entry is newly created on the mobile app and will be synced later
    private Integer isNew;


    /* Select several unique IDs to be the constructor attributes of PatientTable object. */
    public PatientTable(@NonNull String patient_id, String study_id, String hh_id, Integer isNew) {
        this.patient_id = patient_id;
        this.study_id = study_id;
        this.hh_id = hh_id;
        this.isNew = isNew;
    }

    /* getter and setter */

    @NonNull
    public String getPatient_id() {
        return patient_id;
    }

    public String getStudy_id() {
        return study_id;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHh_id() {
        return hh_id;
    }

    public String getDur_hh() {
        return dur_hh;
    }

    public void setDur_hh(String dur_hh) {
        this.dur_hh = dur_hh;
    }

    public String getLvl_edu() {
        return lvl_edu;
    }

    public void setLvl_edu(String lvl_edu) {
        this.lvl_edu = lvl_edu;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getMother_first() {
        return mother_first;
    }

    public void setMother_first(String mother_first) {
        this.mother_first = mother_first;
    }

    public String getMother_last() {
        return mother_last;
    }

    public void setMother_last(String mother_last) {
        this.mother_last = mother_last;
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

    public String getTel1_owner_rel() {
        return tel1_owner_rel;
    }

    public void setTel1_owner_rel(String tel1_owner_rel) {
        this.tel1_owner_rel = tel1_owner_rel;
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

    public String getTel2_owner_rel() {
        return tel2_owner_rel;
    }

    public void setTel2_owner_rel(String tel2_owner_rel) {
        this.tel2_owner_rel = tel2_owner_rel;
    }

    public String getEnum_id() {
        return enum_id;
    }

    public void setEnum_id(String enum_id) {
        this.enum_id = enum_id;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }

    public String getProxy_name() {
        return proxy_name;
    }

    public void setProxy_name(String proxy_name) {
        this.proxy_name = proxy_name;
    }

    public String getProxy_rel() {
        return proxy_rel;
    }

    public void setProxy_rel(String proxy_rel) {
        this.proxy_rel = proxy_rel;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getDeceased() {
        return deceased;
    }

    public void setDeceased(Integer deceased) {
        this.deceased = deceased;
    }

    public String getDeceased_date() {
        return deceased_date;
    }

    public void setDeceased_date(String deceased_date) {
        this.deceased_date = deceased_date;
    }

    public Integer getIsNew() {
        return isNew;
    }
}
