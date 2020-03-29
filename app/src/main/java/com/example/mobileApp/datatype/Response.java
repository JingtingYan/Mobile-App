package com.example.mobileApp.datatype;

import java.time.LocalDate;

/**
 * The Response class is a user-defined data type which contains more general data fields than the ResponseTable class.
 * It is used to hold data related to a single patient response.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
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
