package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_assessment_status", primaryKeys = {"patient_id", "qnnaire_id"})

public class PatientAssessmentStatusTable {

    //    REQUIRED
    @NonNull
    private String patient_id = "";

    //    REQUIRED
    @NonNull
    private String qnnaire_id = "";

    private String qnnaire_status;

    // start is in format "YYYY-MM-DD"
    private String start;

    // end is in format "YYYY-MM-DD"
    private String end;


    /* getter and setter */

    @NonNull
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(@NonNull String patient_id) {
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
