package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "patients",
        foreignKeys = {@ForeignKey(entity = Household.class,
                                   parentColumns = "household_id",
                                   childColumns = "hh_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Enumerator.class,
                                   parentColumns = "enumerator_id",
                                   childColumns = "enum_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Patient {

    @PrimaryKey
    @NonNull private String patient_id;

    @NonNull private String study_id;

    @NonNull private String date_of_birth = "0000-00-00";

    private String prefix;

    @NonNull private String first_name;

    private String middle_name;

    @NonNull private String last_name;

    private String suffix;

    private String com_name;

    @NonNull private String gender;

    // this is a foreign key
    @NonNull private String hh_id;

    @NonNull private String dur_hh;

    private String exam_status;

    private String notes;

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
    @NonNull private String enum_id;

    private String national_id;

    private boolean deceased = false;

    private LocalDate deceased_date = null;

    @NonNull private String responder;

    private String proxy_name;

    private String proxy_rel;


    /* getter and setter */

    @NonNull
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(@NonNull String patient_id) {
        this.patient_id = patient_id;
    }

    @NonNull
    public String getStudy_id() {
        return study_id;
    }

    public void setStudy_id(@NonNull String study_id) {
        this.study_id = study_id;
    }

    @NonNull
    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(@NonNull String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @NonNull
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@NonNull String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    @NonNull
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NonNull String last_name) {
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

    @NonNull
    public String getGender() {
        return gender;
    }

    public void setGender(@NonNull String gender) {
        this.gender = gender;
    }

    @NonNull
    public String getHh_id() {
        return hh_id;
    }

    public void setHh_id(@NonNull String hh_id) {
        this.hh_id = hh_id;
    }

    @NonNull
    public String getDur_hh() {
        return dur_hh;
    }

    public void setDur_hh(@NonNull String dur_hh) {
        this.dur_hh = dur_hh;
    }

    public String getExam_status() {
        return exam_status;
    }

    public void setExam_status(String exam_status) {
        this.exam_status = exam_status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    @NonNull
    public String getEnum_id() {
        return enum_id;
    }

    public void setEnum_id(@NonNull String enum_id) {
        this.enum_id = enum_id;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }

    public LocalDate getDeceased_date() {
        return deceased_date;
    }

    public void setDeceased_date(LocalDate deceased_date) {
        this.deceased_date = deceased_date;
    }

    @NonNull
    public String getResponder() {
        return responder;
    }

    public void setResponder(@NonNull String responder) {
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
}
