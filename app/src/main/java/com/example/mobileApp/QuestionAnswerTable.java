package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questions_and_answers",
        primaryKeys = {"q_id", "ans_id", "qnnaire_id"},
        indices={@Index(value = {"ans_id", "q_id"}),
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

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer q_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer ans_id = 0;

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer qnnaire_id = 0;


    /* getter and setter */

    @NonNull
    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(@NonNull Integer q_id) {
        this.q_id = q_id;
    }

    @NonNull
    public Integer getAns_id() {
        return ans_id;
    }

    public void setAns_id(@NonNull Integer ans_id) {
        this.ans_id = ans_id;
    }

    @NonNull
    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }
}
