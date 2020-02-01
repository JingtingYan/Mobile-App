package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "logic",
        indices = { @Index(value = "sequence_num", unique = true),
                    @Index(value = "rel_id", unique = true),
                    @Index(value = {"q_id", "qnnaire_id"}),
                    @Index("rel_ans_id"),
                    @Index("next_q_id"),
                    @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Answer.class,
                                   parentColumns = "answer_id",
                                   childColumns = "rel_ans_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Question.class,
                                   parentColumns = "question_id",
                                   childColumns = "next_q_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                       @ForeignKey(entity = Questionnaire.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE)})
public class Logic {

    @PrimaryKey(autoGenerate = true)
    //    REQUIRED
    private int sequence_num;

    // this is a foreign key
    //    REQUIRED
    private int q_id;

    // this is a foreign key
    private int rel_ans_id;

    // this is a foreign key
    private int next_q_id;

    private String rel_type;

    private int rel_id;  // unique

    // this is a foreign key
    //    REQUIRED
    private int qnnaire_id;


    /* getter and setter */

    public int getSequence_num() {
        return sequence_num;
    }

    public void setSequence_num(int sequence_num) {
        this.sequence_num = sequence_num;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public int getRel_ans_id() {
        return rel_ans_id;
    }

    public void setRel_ans_id(int rel_ans_id) {
        this.rel_ans_id = rel_ans_id;
    }

    public int getNext_q_id() {
        return next_q_id;
    }

    public void setNext_q_id(int next_q_id) {
        this.next_q_id = next_q_id;
    }

    public String getRel_type() {
        return rel_type;
    }

    public void setRel_type(String rel_type) {
        this.rel_type = rel_type;
    }

    public int getRel_id() {
        return rel_id;
    }

    public void setRel_id(int rel_id) {
        this.rel_id = rel_id;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
