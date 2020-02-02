package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "causes_of_diseases",
        primaryKeys = {"question_cod_id", "qnnaire_id"},
        indices = {@Index("qnnaire_id")},
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

    // this is a foreign key
//  REQUIRED
    @NonNull
    private Integer question_cod_id = 0;

    // this is a foreign key
//    REQUIRED
    @NonNull
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getQuestion_cod_id() {
        return question_cod_id;
    }

    public void setQuestion_cod_id(@NonNull Integer question_cod_id) {
        this.question_cod_id = question_cod_id;
    }

    @NonNull
    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
