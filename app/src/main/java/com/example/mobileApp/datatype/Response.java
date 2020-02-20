package com.example.mobileApp.datatype;

import java.time.LocalDate;

public class Response {

    private Integer index;
    private String patientId;
    private Integer q_id;
    private Integer a_id;
    private Integer qnnaire_id;
    private String text;
    private LocalDate date;

    public Response(Integer index, String patientId, Integer q_id, Integer a_id, Integer qnnaire_id, String text, LocalDate date) {
        this.index = index;
        this.patientId = patientId;
        this.q_id = q_id;
        this.a_id = a_id;
        this.qnnaire_id = qnnaire_id;
        this.text = text;
        this.date = date;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
