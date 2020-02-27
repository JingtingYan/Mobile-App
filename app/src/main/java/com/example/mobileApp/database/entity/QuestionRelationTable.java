package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "question_relations",
        indices = {@Index(value={"rel_id","q_id","rel_q_id","rel_ans_id","qnnaire_id"}, unique = true),
                   @Index("q_id"),
                   @Index("rel_q_id"),
                   @Index("rel_ans_id"),
                   @Index("qnnaire_id")})
public class QuestionRelationTable {

    @PrimaryKey
    @NonNull
    private Integer index;  // remove auto-generate

    //    REQUIRED
    private Integer rel_id; // remove default = 0

    //    REQUIRED
    private Integer q_id;   // remove default = 0

    //    REQUIRED
    private Integer rel_q_id;   // remove default = 0

    //    REQUIRED
    private Integer rel_ans_id; // remove default = 0

    //    REQUIRED
    private Integer qnnaire_id; // remove default = 0


    public QuestionRelationTable(@NonNull Integer index, Integer rel_id, Integer q_id, Integer rel_q_id, Integer rel_ans_id, Integer qnnaire_id) {
        this.index = index;
        this.rel_id = rel_id;
        this.q_id = q_id;
        this.rel_q_id = rel_q_id;
        this.rel_ans_id = rel_ans_id;
        this.qnnaire_id = qnnaire_id;
    }


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public void setIndex(@NonNull Integer index) {
        this.index = index;
    }

    public Integer getRel_id() {
        return rel_id;
    }

    public void setRel_id(Integer rel_id) {
        this.rel_id = rel_id;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public Integer getRel_q_id() {
        return rel_q_id;
    }

    public void setRel_q_id(Integer rel_q_id) {
        this.rel_q_id = rel_q_id;
    }

    public Integer getRel_ans_id() {
        return rel_ans_id;
    }

    public void setRel_ans_id(Integer rel_ans_id) {
        this.rel_ans_id = rel_ans_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
