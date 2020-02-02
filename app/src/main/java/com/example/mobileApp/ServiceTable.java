package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "services",
        primaryKeys = {"question_service_id", "qnnaire_id"},
        indices = {@Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "question_service_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = QuestionnaireTable.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class ServiceTable {

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer question_service_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getQuestion_service_id() {
        return question_service_id;
    }

    public void setQuestion_service_id(@NonNull Integer question_service_id) {
        this.question_service_id = question_service_id;
    }

    @NonNull
    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
