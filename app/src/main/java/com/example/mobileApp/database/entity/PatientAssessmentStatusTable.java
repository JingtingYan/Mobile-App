package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_assessment_status",
        indices = {@Index(value = {"patient_id", "qnnaire_id"}, unique = true)})

public class PatientAssessmentStatusTable {

    //    REQUIRED
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer index = 0;

    private String patient_id = "";

    private String qnnaire_id = "";

    private String qnnaire_status;

    // start is in format "YYYY-MM-DD"
    private String start;

    // end is in format "YYYY-MM-DD"
    private String end;


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public void setIndex(@NonNull Integer index) {
        this.index = index;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(String qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getQnnaire_status() {
        return qnnaire_status;
    }

    public void setQnnaire_status(String qnnaire_status) {
        this.qnnaire_status = qnnaire_status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
