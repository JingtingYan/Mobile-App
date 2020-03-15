package com.example.mobileApp.datatype;

public class AssessmentRecyclerViewItem {

    private String questionnaireName;
    private String status;
    private Integer id;
    private String startDate;
    private String endDate;

    public AssessmentRecyclerViewItem(String questionnaireName, String status, Integer id, String startDate, String endDate) {
        this.questionnaireName = questionnaireName;
        this.status = status;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public String getStatus() {
        return status;
    }

    public Integer getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // information to search for
    public String getAssessmentInfoToFilter() {
        return questionnaireName + " " + startDate + " " + endDate;
    }
}
