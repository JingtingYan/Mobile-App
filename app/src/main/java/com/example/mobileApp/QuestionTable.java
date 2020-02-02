package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questions",
        indices = {@Index(value= {"question_id", "qnnaire_id"}),
                    @Index("q_type_id"),
                    @Index("qnnaire_id")},
        foreignKeys = {@ForeignKey(entity = QuestionTypeTable.class,
                                   parentColumns = "question_type_id",
                                   childColumns = "q_type_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                      @ForeignKey(entity = QuestionnaireTable.class,
                                  parentColumns = "questionnaire_id",
                                  childColumns = "qnnaire_id",
                                  onDelete = CASCADE,
                                  onUpdate = CASCADE)})
public class QuestionTable {

    @PrimaryKey(autoGenerate = true)
    //    REQUIRED
    @NonNull
    private Integer question_id = 0;

    //    REQUIRED
    @NonNull
    private String question_string = "";

    // this is a foreign key
    //    REQUIRED
    @NonNull
    private Integer q_type_id = 0;

    private Integer answer_min;

    private Integer answer_max;

    private String question_instruction;

    // this is a foreign key
    //    REQUIRED
    private Integer qnnaire_id;

    private String question_media;


    /* getter and setter */

    @NonNull
    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(@NonNull Integer question_id) {
        this.question_id = question_id;
    }

    @NonNull
    public String getQuestion_string() {
        return question_string;
    }

    public void setQuestion_string(@NonNull String question_string) {
        this.question_string = question_string;
    }

    @NonNull
    public Integer getQ_type_id() {
        return q_type_id;
    }

    public void setQ_type_id(@NonNull Integer q_type_id) {
        this.q_type_id = q_type_id;
    }

    public Integer getAnswer_min() {
        return answer_min;
    }

    public void setAnswer_min(Integer answer_min) {
        this.answer_min = answer_min;
    }

    public Integer getAnswer_max() {
        return answer_max;
    }

    public void setAnswer_max(Integer answer_max) {
        this.answer_max = answer_max;
    }

    public String getQuestion_instruction() {
        return question_instruction;
    }

    public void setQuestion_instruction(String question_instruction) {
        this.question_instruction = question_instruction;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(Integer qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getQuestion_media() {
        return question_media;
    }

    public void setQuestion_media(String question_media) {
        this.question_media = question_media;
    }
}
