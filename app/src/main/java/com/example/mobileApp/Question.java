package com.example.mobileApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questions",
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
    @NonNull private int question_id;

    @NonNull private String question_string;

    // this is a foreign key
    @NonNull private String q_type_id;

    private int answer_min;

    private int answer_max;

    private String question_instruction;

    // this is a foreign key
    @NonNull private String qnnaire_id;

    private String question_media;


    /* getter and setter */

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
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
    public String getQ_type_id() {
        return q_type_id;
    }

    public void setQ_type_id(@NonNull String q_type_id) {
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

    @NonNull
    public String getQnnaire_id() {
        return qnnaire_id;
    }

    public void setQnnaire_id(@NonNull String qnnaire_id) {
        this.qnnaire_id = qnnaire_id;
    }

    public String getQuestion_media() {
        return question_media;
    }

    public void setQuestion_media(String question_media) {
        this.question_media = question_media;
    }
}
