package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "responses",
        foreignKeys = {@ForeignKey(entity = Patient.class,
                                   parentColumns = "patient_id",
                                   childColumns = "p_id",
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

    @PrimaryKey
    // this is a foreign key
    @NonNull private String p_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int q_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int ans_id;

    private String text;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int qnnaire_id;

    // date is in format "YYYY-MM-DD"
    @NonNull private String date;


    /* getter and setter */

    @NonNull
    public String getP_id() {
        return p_id;
    }

    public void setP_id(@NonNull String p_id) {
        this.p_id = p_id;
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

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }
}
