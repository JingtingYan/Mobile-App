package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questionnaires")   // remove indices
public class QuestionnaireTable {

    @PrimaryKey
    //    REQUIRED
    @NonNull
    private Integer questionnaire_id;

    //    REQUIRED
    private String questionnaire_name;

    //    REQUIRED
    private String questionnaire_version;

    private String questionnaire_type;


    public QuestionnaireTable(@NonNull Integer questionnaire_id, String questionnaire_name,
                              String questionnaire_version, String questionnaire_type) {
        this.questionnaire_id = questionnaire_id;
        this.questionnaire_name = questionnaire_name;
        this.questionnaire_version = questionnaire_version;
        this.questionnaire_type = questionnaire_type;
    }


    /* getter and setter */

    @NonNull
    public Integer getQuestionnaire_id() {
        return questionnaire_id;
    }

    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public String getQuestionnaire_version() {
        return questionnaire_version;
    }

    public String getQuestionnaire_type() {
        return questionnaire_type;
    }

}
