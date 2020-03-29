package com.example.mobileApp.datatype;

import java.util.List;

/**
 * The Question class is a user-defined data type which contains more general data fields than the QuestionTable class.
 * It is used to hold data related to a single question.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class Question {
    private Integer questionID;
    private String questionString;
    private String questionInstruction;
    private Integer questionType;
    private List<Answer> answers;
    private String questionMedia;

    public Question(Integer questionID, String questionString, String questionInstruction, Integer questionType, List<Answer> answers, String questionMedia) {
        this.questionID = questionID;
        this.questionString = questionString;
        this.questionInstruction = questionInstruction;
        this.questionType = questionType;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getQuestionMedia() {
        return questionMedia;
    }
}
