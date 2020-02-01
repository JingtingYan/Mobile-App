package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "response",
        indices = {@Index(value = {"patient_id", "q_id","qnnaire_id"}),
                    @Index("ans_id"),
                    @Index("q_id"),
                    @Index("qnnaire_id")},
        primaryKeys = {"patient_id", "q_id", "ans_id", "qnnaire_id"},
        foreignKeys = {@ForeignKey(entity = Patient.class,
                                   parentColumns = "patient_id",
                                   childColumns = "patient_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Answer.class,
                                   parentColumns = "answer_id",
                                   childColumns = "ans_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Response {

    // this is a foreign key
    //    REQUIRED
    private String patient_id;

    // this is a foreign key
    //    REQUIRED
    private int q_id;

    // this is a foreign key
    //    REQUIRED
    private int ans_id;

    private String text;

    // this is a foreign key
    //    REQUIRED
    private int qnnaire_id;

    // date is in format "YYYY-MM-DD"
    //    REQUIRED
    private String date;


    /* getter and setter */

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public int getAns_id() {
        return ans_id;
    }

    public void setAns_id(int ans_id) {
        this.ans_id = ans_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
