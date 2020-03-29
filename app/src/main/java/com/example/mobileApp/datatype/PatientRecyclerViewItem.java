package com.example.mobileApp.datatype;

/**
 * The PatientRecyclerViewItem class is a user-defined data type which contains more general data fields
 * than the PatientTable class.
 * It is used to hold data related to a single patient.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class PatientRecyclerViewItem {

    private String patientID;
    private String patientName;
    private String patientDOB;
    private String studyID;
    private String householdID;

    public PatientRecyclerViewItem(String patientID, String patientName, String patientDOB,
                                   String studyID, String householdID) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientDOB = patientDOB;
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
