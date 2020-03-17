package com.example.mobileApp.datatype;

public class AssessmentRecyclerViewItem {

    private String questionnaireName;
    private String status;
    private Integer questionnaireID;
    private String startDate;
    private String endDate;
    private Integer lastAnsweredQnID;

    public AssessmentRecyclerViewItem(String questionnaireName, String status, Integer questionnaireID,
                                      String startDate, String endDate, Integer lastAnsweredQnID) {
        this.questionnaireName = questionnaireName;
        this.status = status;
        this.questionnaireID = questionnaireID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastAnsweredQnID = lastAnsweredQnID;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public String getStatus() {
        return status;
    }

    public Integer getQuestionnaireID() {
        return questionnaireID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getLastAnsweredQnID() {
        return lastAnsweredQnID;
    }

    // information to search for
    public String getAssessmentInfoToFilter() {
        return questionnaireName + " " + startDate + " " + endDate;
    }
}
