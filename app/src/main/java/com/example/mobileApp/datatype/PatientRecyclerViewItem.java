package com.example.mobileApp.datatype;

public class PatientRecyclerViewItem {

    private String patientID;
    private String patientName;
    private String patientDOB;
    private String patientAssessmentStatus;
    private String studyID;
    private String householdID;

    public PatientRecyclerViewItem(String patientID, String patientName, String patientDOB,
                                   String patientAssessmentStatus, String studyID, String householdID) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientDOB = patientDOB;
        this.patientAssessmentStatus = patientAssessmentStatus;
        this.studyID = studyID;
        this.householdID = householdID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public String getPatientAssessmentStatus() {
        return patientAssessmentStatus;
    }

    public String getStudyID() {
        return studyID;
    }

    public String getHouseholdID() {
        return householdID;
    }

    public String getPatientInfoToFilter() {
        return patientID + " " + patientName;
    }
}
