package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "answers",
        indices = {@Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Answer {

    @PrimaryKey(autoGenerate = true)
//  REQUIRED
    private int answer_id;

//  REQUIRED
    private String answer_string;

    // this is a foreign key
//  REQUIRED
    private int qnnaire_id;


    /* getter and setter */

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_string() {
        return answer_string;
    }

    public void setAnswer_string(String answer_string)
    {
        this.answer_string = answer_string;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
