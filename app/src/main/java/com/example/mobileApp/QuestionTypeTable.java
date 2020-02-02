package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_types")
public class QuestionTypeTable {

    @PrimaryKey(autoGenerate = true)
    //    REQUIRED
    @NonNull
    private Integer question_type_id = 0;

    //    REQUIRED
    private String question_type = "";


    /* getter and setter */

    @NonNull
    public Integer getQuestion_type_id() {
        return question_type_id;
    }

    public void setQuestion_type_id(@NonNull Integer question_type_id) {
        this.question_type_id = question_type_id;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }
}
