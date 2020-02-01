package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questions_and_answers",
        foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Answer.class,
                                   parentColumns = "answer_id",
                                   childColumns = "ans_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE)})
public class QuestionAnswer {

    @PrimaryKey
    // this is a foreign key
    @NonNull private int q_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int ans_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int qnnaire_id;


    /* getter and setter */

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public int getAns_id() {
        return ans_id;
    }

    public void setAns_id(int ans_id) {
        this.ans_id = ans_id;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
