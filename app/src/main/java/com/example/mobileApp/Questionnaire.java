package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questionnaires")
public class Questionnaire {

    @PrimaryKey(autoGenerate = true)
    @NonNull private int questionnaire_id;

    @NonNull private String questionnaire_name;

    @NonNull private String questionnaire_version;

    private boolean active_flag = false;


    /* getter and setter */

    public int getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(int questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    @NonNull
    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public void setQuestionnaire_name(@NonNull String questionnaire_name) {
        this.questionnaire_name = questionnaire_name;
    }

    @NonNull
    public String getQuestionnaire_version() {
        return questionnaire_version;
    }

    public void setQuestionnaire_version(@NonNull String questionnaire_version) {
        this.questionnaire_version = questionnaire_version;
    }

    public boolean isActive_flag() {
        return active_flag;
    }

    public void setActive_flag(boolean active_flag) {
        this.active_flag = active_flag;
    }
}
