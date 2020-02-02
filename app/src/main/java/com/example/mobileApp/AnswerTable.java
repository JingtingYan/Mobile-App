package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "answers",
        indices = {@Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = QuestionnaireTable.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class AnswerTable {

    @PrimaryKey(autoGenerate = true)
//  REQUIRED
    @NonNull private Integer answer_id = 0;

//  REQUIRED
    private String answer_string;

    // this is a foreign key
//  REQUIRED
    private Integer qnnaire_id;


    /* getter and setter */

    @NonNull
    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(@NonNull Integer answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_string() {
        return answer_string;
    }

    public void setAnswer_string(String answer_string) {
        this.answer_string = answer_string;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
