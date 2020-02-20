package com.example.mobileApp.datatype;

import com.example.mobileApp.datatype.Answer;

import java.util.List;

public class Question {
    private Integer questionID;
    private String questionString;
    private String questionInstruction;
    private Integer questionType;
    private Integer ansMin;
    private Integer ansMax;
    private List<Answer> answers;
    private String questionMedia;

    public Question(Integer questionID, String questionString, String questionInstruction, Integer questionType, Integer ansMin, Integer ansMax, List<Answer> answers, String questionMedia) {
        this.questionID = questionID;
        this.questionString = questionString;
        this.questionInstruction = questionInstruction;
        this.questionType = questionType;
        this.ansMin = ansMin;
        this.ansMax = ansMax;
        this.answers = answers;
        this.questionMedia = questionMedia;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public String getQuestionString() {
        return questionString;
    }

    public String getQuestionInstruction() {
        return questionInstruction;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public Integer getAnsMin() {
        return ansMin;
    }

    public Integer getAnsMax() {
        return ansMax;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getQuestionMedia() {
        return questionMedia;
    }
}
