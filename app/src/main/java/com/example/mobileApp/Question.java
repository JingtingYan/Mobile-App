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
        foreignKeys = {@ForeignKey(entity = QuestionType.class,
                                   parentColumns = "question_type_id",
                                   childColumns = "q_type_id",
                                   onDelete = CASCADE,
                                   onUpdate = CASCADE),
                      @ForeignKey(entity = Questionnaire.class,
                                  parentColumns = "questionnaire_id",
                                  childColumns = "qnnaire_id",
                                  onDelete = CASCADE,
                                  onUpdate = CASCADE)})
public class Question {

    @PrimaryKey(autoGenerate = true)
    //    REQUIRED
    private int question_id;

    //    REQUIRED
    private String question_string;

    // this is a foreign key
    //    REQUIRED
    private int q_type_id;

    private int answer_min;

    private int answer_max;

    private String question_instruction;

    // this is a foreign key
    //    REQUIRED
    private int qnnaire_id;

    private String question_media;


    /* getter and setter */

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_string() {
        return question_string;
    }

    public void setQuestion_string(String question_string) {
        this.question_string = question_string;
    }

    public int getQ_type_id() {
        return q_type_id;
    }

    public void setQ_type_id(int q_type_id) {
        this.q_type_id = q_type_id;
    }

    public int getAnswer_min() {
        return answer_min;
    }

    public void setAnswer_min(int answer_min) {
        this.answer_min = answer_min;
    }

    public int getAnswer_max() {
        return answer_max;
    }

    public void setAnswer_max(int answer_max) {
        this.answer_max = answer_max;
    }

    public String getQuestion_instruction() {
        return question_instruction;
    }

    public void setQuestion_instruction(String question_instruction) {
        this.question_instruction = question_instruction;
    }

    public int getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(int qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getQuestion_media() {
        return question_media;
    }

    public void setQuestion_media(String question_media) {
        this.question_media = question_media;
    }
}
