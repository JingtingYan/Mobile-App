package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_types")
public class QuestionType {

    @PrimaryKey(autoGenerate = true)
    @NonNull private int question_type_id;

    @NonNull private String question_type;


    /* getter and setter */

    public int getQuestion_type_id() {
        return question_type_id;
    }

    public void setQuestion_type_id(int question_type_id) {
        this.question_type_id = question_type_id;
    }

    @NonNull
    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(@NonNull String question_type) {
        this.question_type = question_type;
    }
}
