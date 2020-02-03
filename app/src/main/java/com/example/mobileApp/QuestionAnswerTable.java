package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questions_and_answers",
        indices={@Index(value = {"ans_id", "q_id", "qnnaire_id"}, unique = true),
                 @Index("q_id"),
                 @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = QuestionTable.class,
                                   parentColumns = "question_id",
                                   childColumns = "q_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = AnswerTable.class,
                                   parentColumns = "answer_id",
                                   childColumns = "ans_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE),
                       @ForeignKey(entity = QuestionnaireTable.class,
                                   parentColumns = "questionnaire_id",
                                   childColumns = "qnnaire_id",
                                   onUpdate = CASCADE,
                                   onDelete = CASCADE)})
public class QuestionAnswerTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    //    REQUIRED
    private Integer index;

    // this is a foreign key
    //    REQUIRED
    private Integer q_id = 0;

    // this is a foreign key
    //    REQUIRED
    private Integer ans_id = 0;

    // this is a foreign key
    //    REQUIRED
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public void setIndex(@NonNull Integer index) {
        this.index = index;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public Integer getAns_id() {
        return ans_id;
    }

    public void setAns_id(Integer ans_id) {
        this.ans_id = ans_id;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
