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
    @NonNull
    private String patient_id = "";

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer q_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer ans_id = 0;

    private String text;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer qnnaire_id = 0;

    // date is in format "YYYY-MM-DD"
    //    REQUIRED
    private String date;


    /* getter and setter */

    @NonNull
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(@NonNull String patient_id) {
        this.patient_id = patient_id;
    }

    @NonNull
    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(@NonNull Integer q_id) {
        this.q_id = q_id;
    }

    @NonNull
    public Integer getAns_id() {
        return ans_id;
    }

    public void setAns_id(@NonNull Integer ans_id) {
        this.ans_id = ans_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
