package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions",
        indices = {@Index(value= {"question_id", "qnnaire_id"}),
                    @Index("q_type_id"),
                    @Index("qnnaire_id")})
public class QuestionTable {

    @PrimaryKey
    //    REQUIRED
    @NonNull
    private Integer question_id;

    //    REQUIRED
    private String question_string; // remove default = ""

    //    REQUIRED
    private Integer q_type_id;  // remove default = 0

    private Integer answer_min;

    private Integer answer_max;

    private String question_instruction;

    //    REQUIRED
    private Integer qnnaire_id;

    private String question_media;


    public QuestionTable(@NonNull Integer question_id, String question_string, Integer q_type_id, Integer answer_min,
                         Integer answer_max, String question_instruction, Integer qnnaire_id, String question_media) {
        this.question_id = question_id;
        this.question_string = question_string;
        this.q_type_id = q_type_id;
        this.answer_min = answer_min;
        this.answer_max = answer_max;
        this.question_instruction = question_instruction;
        this.qnnaire_id = qnnaire_id;
        this.question_media = question_media;
    }


    /* getter */

    @NonNull
    public Integer getQuestion_id() {
        return question_id;
    }

    public String getQuestion_string() {
        return question_string;
    }

    public Integer getQ_type_id() {
        return q_type_id;
    }

    public Integer getAnswer_min() {
        return answer_min;
    }

    public Integer getAnswer_max() {
        return answer_max;
    }

    public String getQuestion_instruction() {
        return question_instruction;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public String getQuestion_media() {
        return question_media;
    }
}
