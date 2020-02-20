package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.database.entity.QuestionnaireTable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "services",
        indices = {@Index(value = {"question_service_id", "qnnaire_id"}, unique = true),
                   @Index("qnnaire_id")},
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

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer index = 0;

    // this is a foreign key
    //    REQUIRED
    private Integer question_service_id = 0;

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

    public Integer getQuestion_service_id() {
        return question_service_id;
    }

    public void setQuestion_service_id(Integer question_service_id) {
        this.question_service_id = question_service_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
