package com.example.mobileApp.datatype;

import javax.annotation.Nonnull;

/**
 * The Answer class is a user-defined data type which contains more general data fields than the AnswerTable class.
 * It is used to hold data related to a single answer choice.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
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
