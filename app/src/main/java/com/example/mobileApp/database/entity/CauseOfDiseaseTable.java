package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "causes_of_diseases",
        indices = {@Index(value = {"question_cod_id", "qnnaire_id"}, unique = true),
                   @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_cod_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = QuestionnaireTable.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class CauseOfDiseaseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer index = 0;

    // this is a foreign key
    //  REQUIRED
    private Integer question_cod_id = 0;

    // this is a foreign key
    //    REQUIRED
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public void setIndex(@NonNull Integer index) {
        this.index = index;
    }

    public Integer getQuestion_cod_id() {
        return question_cod_id;
    }

    public void setQuestion_cod_id(Integer question_cod_id) {
        this.question_cod_id = question_cod_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
