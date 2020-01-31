package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "answers",
        foreignKeys = {@ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Answer {

    @PrimaryKey(autoGenerate = true)
    @NonNull private int answer_id;

    @NonNull private String answer_string;

    // this is a foreign key
    @NonNull private String qnnaire_id;


    /* getter and setter */

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    @NonNull
    public String getAnswer_string() {
        return answer_string;
    }

    public void setAnswer_string(@NonNull String answer_string) {
        this.answer_string = answer_string;
    }

    @NonNull
    public String getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull String qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
