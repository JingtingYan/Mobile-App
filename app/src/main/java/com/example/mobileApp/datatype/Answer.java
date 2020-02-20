package com.example.mobileApp.datatype;

public class Answer {

    private Integer answerID;
    private String answerString;

    public Answer(Integer answerID, String answerString) {
        this.answerID = answerID;
        this.answerString = answerString;
    }

    public Integer getAnswerID() {
        return answerID;
    }

    public String getAnswerString() {
        return answerString;
    }
}
