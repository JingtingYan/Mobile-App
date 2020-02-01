package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "causes_of_diseases",
        foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_at_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class CauseOfDisease {

    @PrimaryKey
    // this is a foreign key
    @NonNull
    private int question_at_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int qnnaire_id;


    /* getter and setter */

    public int getQuestion_at_id() {
        return question_at_id;
    }

    public void setQuestion_at_id(int question_at_id) {
        this.question_at_id = question_at_id;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
