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
    private static String currentStudyID;
    private static String currentPatientID;
    private static String currentHouseholdID;

    // two shared variables used to update AssessmentStatus table
    private static boolean qnnExists = false;
    private static AssessmentRecyclerViewItem selectedAssessment;


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

    public static String getCurrentStudyID() {
        return currentStudyID;
    }

    public static void setCurrentStudyID(String currentStudyID) {
        Constants.currentStudyID = currentStudyID;
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
    public static final String LOGIN_URL = "http://10.0.2.2:8000/tables/login/";

    // Data Sync APIs
    public static final String GET_LOCATION_URL = "http://10.0.2.2:8000/tables/Location/";
    public static final String GET_QUESTIONNAIRE_URL = "http://10.0.2.2:8000/tables/Questionnaire/";
    public static final String GET_QUESTION_URL = "http://10.0.2.2:8000/tables/Question/";
    public static final String GET_ANSWER_URL = "http://10.0.2.2:8000/tables/Answer/";
    public static final String GET_QA_URL = "http://10.0.2.2:8000/tables/QA/";
    public static final String GET_LOGIC_URL = "http://10.0.2.2:8000/tables/Logic/";
    public static final String GET_QUESTION_RELATION_URL = "http://10.0.2.2:8000/tables/QRel/";
    public static final String GET_HOUSEHOLD_FOR_CLUSTER_URL = "http://10.0.2.2:8000/tables/Household/";
    public static final String GET_PATIENT_FOR_CLUSTER_URL = "http://10.0.2.2:8000/tables/Patients/";
    public static final String GET_PATIENT_ASSESSMENT_FOR_CLUSTER_URL = "http://10.0.2.2:8000/tables/PatientAssessment/";
    public static final String GET_RESPONSE_FOR_CLUSTER_URL = "http://10.0.2.2:8000/tables/Response/";

    public static final String POST_RESPONSE_URL = "http://10.0.2.2:8000/tables/Response/";
    public static final String POST_HOUSEHOLD_URL = "http://10.0.2.2:8000/tables/Household/";
    public static final String POST_PATIENT_URL = "http://localhost:8000/tables/Patients/";
    public static final String POST_ASSESSMENT_STATUS_URL = "http://10.0.2.2:8000/tables/PatientAssessment/";
    // add other APIs route here in the future

    // some default prompts
    public static final String DEFAULT_QN_INSTRUCTION_TEXT_ENTRY = "This is a text entry question. Please enter the response in the text field then click NEXT.";
    public static final String DEFAULT_QN_INSTRUCTION_SCQ = "This is a single choice question. Please select one answer choice from the dropdown list then click NEXT.";
    public static final String DEFAULT_QN_INSTRUCTION_MCQ = "This is a multiple choice question. Please select appropriate answers by ticking the checkbox then click NEXT.";

    // some tags
    public static final String HOUSEHOLD_HOME_FRAGMENT_TAG = "household_home_fragment";
}
