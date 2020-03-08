package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.mobileApp.database.entity.AnswerTable;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.database.entity.QuestionnaireTable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "responses",
        indices = {@Index(value = {"patient_id", "q_id", "ans_id", "qnnaire_id"}, unique = true),
                   @Index("q_id"),
                   @Index("ans_id"),
                   @Index("qnnaire_id")})   // remove foreign keys
public class ResponseTable {

    @PrimaryKey
    @NonNull
    private Integer index;  // remove auto-increment; remove default = 0

    // this is a foreign key
    //    REQUIRED
    private String patient_id;

    // this is a foreign key
    //    REQUIRED
    private Integer q_id;

    // this is a foreign key
    //    REQUIRED
    private Integer ans_id;

    private String text;

    // this is a foreign key
    //    REQUIRED
    private Integer qnnaire_id;

    // date is in format "YYYY-MM-DD"
    //    REQUIRED
    private String date;


    public ResponseTable(@NonNull Integer index, String patient_id, Integer q_id,
                         Integer ans_id, String text, Integer qnnaire_id, String date) {
        this.index = index;
        this.patient_id = patient_id;
        this.q_id = q_id;
        this.ans_id = ans_id;
        this.text = text;
        this.qnnaire_id = qnnaire_id;
        this.date = date;
    }

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

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public Integer getAns_id() {
        return ans_id;
    }

    public void setAns_id(Integer ans_id) {
        this.ans_id = ans_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
