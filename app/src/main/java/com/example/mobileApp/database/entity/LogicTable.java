package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "logic")    // remove indices
public class LogicTable {

    @PrimaryKey
    //    REQUIRED
    @NonNull
    private Integer index;

    private Integer sequence_num;

    //    REQUIRED
    private Integer q_id;

    private Integer rel_ans_id;

    private Integer next_q_id;

    private String rel_type;

    private Integer rel_id;  // unique

    //    REQUIRED
    private Integer qnnaire_id;


    public LogicTable(@NonNull Integer index, Integer sequence_num, Integer q_id, Integer rel_ans_id,
                      Integer next_q_id, String rel_type, Integer rel_id, Integer qnnaire_id) {
        this.index = index;
        this.sequence_num = sequence_num;
        this.q_id = q_id;
        this.rel_ans_id = rel_ans_id;
        this.next_q_id = next_q_id;
        this.rel_type = rel_type;
        this.rel_id = rel_id;
        this.qnnaire_id = qnnaire_id;
    }


    /* getter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public Integer getSequence_num() {
        return sequence_num;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public Integer getRel_ans_id() {
        return rel_ans_id;
    }

    public Integer getNext_q_id() {
        return next_q_id;
    }

    public String getRel_type() {
        return rel_type;
    }

    public Integer getRel_id() {
        return rel_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }
}
