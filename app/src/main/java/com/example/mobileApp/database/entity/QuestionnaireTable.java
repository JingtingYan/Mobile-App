package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "questionnaires", indices={@Index("questionnaire_id")})
public class QuestionnaireTable {

    @PrimaryKey
    //    REQUIRED
    @NonNull
    private Integer questionnaire_id;

    //    REQUIRED
    private String questionnaire_name;

    //    REQUIRED
    private String questionnaire_version;

    private Integer active_flag = 0;


    public QuestionnaireTable(@NonNull Integer questionnaire_id, String questionnaire_name, String questionnaire_version) {
        this.questionnaire_id = questionnaire_id;
        this.questionnaire_name = questionnaire_name;
        this.questionnaire_version = questionnaire_version;
    }


    /* getter and setter */

    @NonNull
    public Integer getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(@NonNull Integer questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public void setQuestionnaire_name(String questionnaire_name) {
        this.questionnaire_name = questionnaire_name;
    }

    public String getQuestionnaire_version() {
        return questionnaire_version;
    }

    public void setQuestionnaire_version(String questionnaire_version) {
        this.questionnaire_version = questionnaire_version;
    }

    public Integer getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(Integer active_flag) {
        this.active_flag = active_flag;
    }
}
