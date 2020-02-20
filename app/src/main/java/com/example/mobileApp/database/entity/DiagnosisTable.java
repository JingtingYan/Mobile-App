package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "diagnosis",
        indices = {@Index(value = {"question_diagnosis_id", "qnnaire_id"}, unique = true),
                   @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_diagnosis_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = QuestionnaireTable.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class DiagnosisTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer index = 0;

    // this is a foreign key
    //    REQUIRED
    private Integer question_diagnosis_id = 0;

    // this is a foreign key
    //    REQUIREd
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public void setIndex(@NonNull Integer index) {
        this.index = index;
    }

    public Integer getQuestion_diagnosis_id() {
        return question_diagnosis_id;
    }

    public void setQuestion_diagnosis_id(Integer question_diagnosis_id) {
        this.question_diagnosis_id = question_diagnosis_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
