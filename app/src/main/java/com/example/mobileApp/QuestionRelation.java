package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "question_relations",
        primaryKeys = {"rel_id","q_id","rel_q_id","rel_ans_id","qnnaire_id"},
        indices = {@Index(value={"q_id","qnnaire_id"}),
                    @Index(value={"rel_id","q_id", "qnnaire_id"}),
                    @Index("rel_q_id"),
                    @Index("rel_ans_id"),
                    @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = Logic.class,
                                   parentColumns = "rel_id",
                                   childColumns = "rel_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "rel_q_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = AnswerTables.class,
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

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer rel_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer q_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer rel_q_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer rel_ans_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getRel_id() {
        return rel_id;
    }

    public void setRel_id(@NonNull Integer rel_id) {
        this.rel_id = rel_id;
    }

    @NonNull
    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(@NonNull Integer q_id) {
        this.q_id = q_id;
    }

    @NonNull
    public Integer getRel_q_id() {
        return rel_q_id;
    }

    public void setRel_q_id(@NonNull Integer rel_q_id) {
        this.rel_q_id = rel_q_id;
    }

    @NonNull
    public Integer getRel_ans_id() {
        return rel_ans_id;
    }

    public void setRel_ans_id(@NonNull Integer rel_ans_id) {
        this.rel_ans_id = rel_ans_id;
    }

    @NonNull
    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
