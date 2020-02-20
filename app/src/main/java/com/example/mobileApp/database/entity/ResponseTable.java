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
                   @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = PatientTable.class,
                                   parentColumns = "patient_id",
                                   childColumns = "patient_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = AnswerTable.class,
                                   parentColumns = "answer_id",
                                   childColumns = "ans_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = QuestionnaireTable.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class ResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer index = 0;

    // this is a foreign key
    //    REQUIRED
    private String patient_id = "";

    // this is a foreign key
    //    REQUIRED
    private Integer q_id = 0;

    // this is a foreign key
    //    REQUIRED
    private Integer ans_id = 0;

    private String text;

    // this is a foreign key
    //    REQUIRED
    private Integer qnnaire_id = 0;

    // date is in format "YYYY-MM-DD"
    //    REQUIRED
    private String date;


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
