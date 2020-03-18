package com.example.mobileApp.datatype;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Response {

    private String patientID;
    private Integer qnID;
    private Integer ansID;
    private Integer qnnID;
    private String ansText;
    private LocalDate date;

    public Response(String patientID, Integer qnID, Integer ansID, Integer qnnID, String ansText, LocalDate date) {
        this.patientID = patientID;
        this.qnID = qnID;
        this.ansID = ansID;
        this.qnnID = qnnID;
        this.ansText = ansText;
        this.date = date;
    }

    public String getPatientID() {
        return patientID;
    }

    public Integer getQnID() {
        return qnID;
    }

    public Integer getAnsID() {
        return ansID;
    }

    public Integer getQnnID() {
        return qnnID;
    }

    public String getAnsText() {
        return ansText;
    }

    public LocalDate getDate() {
        return date;
    }
}
