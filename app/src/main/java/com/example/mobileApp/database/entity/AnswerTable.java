package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "answers",
        indices = {@Index("qnnaire_id")})
public class AnswerTable {

    @PrimaryKey
//  REQUIRED
    @NonNull private Integer answer_id;

//  REQUIRED
    private String answer_string;

//  REQUIRED
    private Integer qnnaire_id;


    public AnswerTable(@NonNull Integer answer_id, String answer_string, Integer qnnaire_id) {
        this.answer_id = answer_id;
        this.answer_string = answer_string;
        this.qnnaire_id = qnnaire_id;
    }


    /* getter */

    @NonNull
    public Integer getAnswer_id() {
        return answer_id;
    }

    public String getAnswer_string() {
        return answer_string;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }
}
