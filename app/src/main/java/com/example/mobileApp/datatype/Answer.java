package com.example.mobileApp.datatype;

import javax.annotation.Nonnull;

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

    // Override the toString() method to return the answer string of a given Answer object.
    @Nonnull
    @Override
    public String toString() {
        return answerString;
    }
}