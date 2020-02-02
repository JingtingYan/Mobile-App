package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "diagnosis",
        primaryKeys = {"question_diagnosis_id", "qnnaire_id"},
        indices = {@Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_diagnosis_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Diagnosis {

    // this is a foreign key
//    REQUIRED
    @NonNull
    private Integer question_diagnosis_id = 0;

    // this is a foreign key
//    REQUIREd
    @NonNull
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getQuestion_diagnosis_id() {
        return question_diagnosis_id;
    }

    public void setQuestion_diagnosis_id(@NonNull Integer question_diagnosis_id) {
        this.question_diagnosis_id = question_diagnosis_id;
    }

    @NonNull
    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
