package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_assessment_status")    // remove indices

public class PatientAssessmentStatusTable {

    //    REQUIRED
    @NonNull
    @PrimaryKey     // remove auto-increment, remove default = 0
    private Integer index;

    private String patient_id;

    private Integer qnnaire_id;     // remove default = 0

    private String qnnaire_status;

    // start is in format "YYYY-MM-DD"
    private String start;

    // end is in format "YYYY-MM-DD"
    private String end;

    private Integer last_answered_qn_id;

    public PatientAssessmentStatusTable(@NonNull Integer index, String patient_id, Integer qnnaire_id,
                                        String qnnaire_status, String start, String end, Integer last_answered_qn_id) {
        this.index = index;
        this.patient_id = patient_id;
        this.qnnaire_id = qnnaire_id;
        this.qnnaire_status = qnnaire_status;
        this.start = start;
        this.end = end;
        this.last_answered_qn_id = last_answered_qn_id;
    }


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public String getQnnaire_status() {
        return qnnaire_status;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Integer getLast_answered_qn_id() {
        return last_answered_qn_id;
    }

}
