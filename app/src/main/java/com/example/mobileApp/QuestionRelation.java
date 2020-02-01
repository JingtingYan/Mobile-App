package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "question_relations",
        foreignKeys = {@ForeignKey(entity = Logic.class,
                                   parentColumns = "relation_id",
                                   childColumns = "rel_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "rel_q_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Answer.class,
                                   parentColumns = "answer_id",
                                   childColumns = "rel_ans_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE)})
public class QuestionRelation {

    @PrimaryKey
    // this is a foreign key
    @NonNull private int rel_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int q_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int rel_q_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int rel_ans_id;

    @PrimaryKey
    // this is a foreign key
    @NonNull private int qnnaire_id;


    /* getter and setter */

    public int getRel_id() {
        return rel_id;
    }

    public void setRel_id(int rel_id) {
        this.rel_id = rel_id;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public int getRel_q_id() {
        return rel_q_id;
    }

    public void setRel_q_id(int rel_q_id) {
        this.rel_q_id = rel_q_id;
    }

    public int getRel_ans_id() {
        return rel_ans_id;
    }

    public void setRel_ans_id(int rel_ans_id) {
        this.rel_ans_id = rel_ans_id;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
