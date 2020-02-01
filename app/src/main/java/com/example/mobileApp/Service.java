package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "services",
        foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_service_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Service {

    @PrimaryKey
    // this is a foreign key
    @NonNull
    private int question_service_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int qnnaire_id;


    /* getter and setter */

    public int getQuestion_service_id() {
        return question_service_id;
    }

    public void setQuestion_service_id(int question_service_id) {
        this.question_service_id = question_service_id;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
