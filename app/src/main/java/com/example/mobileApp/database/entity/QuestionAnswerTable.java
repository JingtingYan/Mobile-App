package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions_and_answers",
        indices={@Index(value = {"ans_id", "q_id", "qnnaire_id"}, unique = true),
                 @Index("q_id"),
                 @Index("qnnaire_id")})
public class QuestionAnswerTable {

    @PrimaryKey
    @NonNull
    //    REQUIRED
    private Integer index;  // remove auto-generate

    //    REQUIRED
    private Integer q_id;   // remove default = 0

    //    REQUIRED
    private Integer ans_id; // remove default = 0

    //    REQUIRED
    private Integer qnnaire_id; // remove default = 0


    public QuestionAnswerTable(@NonNull Integer index, Integer q_id, Integer ans_id, Integer qnnaire_id) {
        this.index = index;
        this.q_id = q_id;
        this.ans_id = ans_id;
        this.qnnaire_id = qnnaire_id;
    }


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public Integer getAns_id() {
        return ans_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }
}
