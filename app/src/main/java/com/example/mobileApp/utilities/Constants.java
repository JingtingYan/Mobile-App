package com.example.mobileApp.utilities;

import com.example.mobileApp.datatype.AssessmentRecyclerViewItem;
import com.example.mobileApp.datatype.Location;

/**
 * The Constants class stores almost all of the static/final values that are required when
 * using the app. This class is created to follow a better development practice.
 *
 * @author Jingting Yan
 * @version 1.0
 * @since March 2020
 */
public class Constants {

    /* Some static values that will be shared throughout the App (multiple Activities & Fragments) */

    // API authentication
    private static String token;
    private static String enumeratorID;

    // location-related data
    private static Location country;
    private static Location region;
    private static Location cluster;

    // questionnaire-related data
    public static int HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID;
    public static int GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID;
    public static int MOBILITY_QUESTIONNAIRE_ID;
    public static int PATIENT_BASIC_INFORMATION_QUESTIONNAIRE;
    public static int VISION_QUESTIONNAIRE_ID;
    public static int HEARING_QUESTIONNAIRE_ID;

    private static Integer currentQuestionnaireID;
    private static String currentQuestionnaireStartDate;

    // assessment-related data
    private static String currentPatientID;
    private static String currentHouseholdID;

    // two shared variables used to update AssessmentStatus table
    // false indicates the current assessment is a new one; true indicates the current assessment is an unfinished one
    private static boolean qnnExists = false;
    private static AssessmentRecyclerViewItem selectedAssessment;   // the selected unfinished assessment


    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Constants.token = token;
    }

    public static String getEnumeratorID() {
        return enumeratorID;
    }

    public static void setEnumeratorID(String enumeratorID) {
        Constants.enumeratorID = enumeratorID;
    }

    public static Location getCountry() {
        return country;
    }

    public static void setCountry(Location country) {
        Constants.country = country;
    }

    public static Location getRegion() {
        return region;
    }

    public static void setRegion(Location region) {
        Constants.region = region;
    }

    public static Location getCluster() {
        return cluster;
    }

    public static void setCluster(Location cluster) {
        Constants.cluster = cluster;
    }

    public static Integer getCurrentQuestionnaireID() {
        return currentQuestionnaireID;
    }

    public static void setCurrentQuestionnaireID(Integer currentQuestionnaireID) {
        Constants.currentQuestionnaireID = currentQuestionnaireID;
    }

    public static String getCurrentPatientID() {
        return currentPatientID;
    }

    public static void setCurrentPatientID(String currentPatientID) {
        Constants.currentPatientID = currentPatientID;
    }

    public static String getCurrentQuestionnaireStartDate() {
        return currentQuestionnaireStartDate;
    }

    public static void setCurrentQuestionnaireStartDate(String currentQuestionnaireStartDate) {
        Constants.currentQuestionnaireStartDate = currentQuestionnaireStartDate;
    }

    public static String getCurrentHouseholdID() {
        return currentHouseholdID;
    }

    public static void setCurrentHouseholdID(String currentHouseholdID) {
        Constants.currentHouseholdID = currentHouseholdID;
    }

    public static boolean isQnnExists() {
        return qnnExists;
    }

    public static void setQnnExists(boolean qnnExists) {
        Constants.qnnExists = qnnExists;
    }

    public static AssessmentRecyclerViewItem getSelectedAssessment() {
        return selectedAssessment;
    }

    public static void setSelectedAssessment(AssessmentRecyclerViewItem selectedAssessment) {
        Constants.selectedAssessment = selectedAssessment;
    }


    /* Other static final constants */

    // some keys
    public static final String INITIALISATION_KEY = "initialisation_key";

    // Authentication API
    public static final String LOGIN_URL = "https://system-engineering.herokuapp.com/tables/login/";

    // Data Sync APIs
    public static final String GET_LOCATION_URL = "https://system-engineering.herokuapp.com/tables/Location/";
    public static final String GET_QUESTIONNAIRE_URL = "https://system-engineering.herokuapp.com/tables/Questionnaire/";
    public static final String GET_QUESTION_URL = "https://system-engineering.herokuapp.com/tables/Question/";
    public static final String GET_ANSWER_URL = "https://system-engineering.herokuapp.com/tables/Answer/";
    public static final String GET_QA_URL = "https://system-engineering.herokuapp.com/tables/QA/";
    public static final String GET_LOGIC_URL = "https://system-engineering.herokuapp.com/tables/Logic/";
    public static final String GET_QUESTION_RELATION_URL = "https://system-engineering.herokuapp.com/tables/QRel/";
    public static final String GET_HOUSEHOLD_FOR_CLUSTER_URL = "https://system-engineering.herokuapp.com/tables/Household/";
    public static final String GET_PATIENT_FOR_CLUSTER_URL = "https://system-engineering.herokuapp.com/tables/Patients/";
    public static final String GET_PATIENT_ASSESSMENT_FOR_CLUSTER_URL = "https://system-engineering.herokuapp.com/tables/PatientAssessment/";
    public static final String GET_RESPONSE_FOR_CLUSTER_URL = "https://system-engineering.herokuapp.com/tables/Response/";

    public static final String POST_RESPONSE_URL = "https://system-engineering.herokuapp.com/tables/Response/";
    public static final String POST_HOUSEHOLD_URL = "https://system-engineering.herokuapp.com/tables/Household/";
    public static final String POST_PATIENT_URL = "https://system-engineering.herokuapp.com/tables/Patients/";
    public static final String POST_ASSESSMENT_STATUS_URL = "https://system-engineering.herokuapp.com/tables/PatientAssessment/";
    // add other APIs route here in the future

    // some default prompts
    public static final String DEFAULT_QN_INSTRUCTION_TEXT_ENTRY = "This is a text entry question. Please enter the response in the text field then click NEXT.";
    public static final String DEFAULT_QN_INSTRUCTION_SCQ = "This is a single choice question. Please select one answer choice from the dropdown list then click NEXT.";
    public static final String DEFAULT_QN_INSTRUCTION_MCQ = "This is a multiple choice question. Please select appropriate answers by ticking the checkbox then click NEXT.";

}
