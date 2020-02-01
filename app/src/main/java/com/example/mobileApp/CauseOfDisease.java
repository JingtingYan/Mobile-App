package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "causes_of_diseases",
        primaryKeys = {"question_cod_id", "qnnaire_id"},
        indices = {@Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_cod_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class CauseOfDisease {

    // this is a foreign key
//  REQUIRED
    private int question_cod_id;

    // this is a foreign key
//    REQUIRED
    private int qnnaire_id;


    /* getter and setter */

    public int getQuestion_cod_id() {
        return question_cod_id;
    }

    public void setQuestion_cod_id(int question_cod_id) {
        this.question_cod_id = question_cod_id;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}