package com.example.mobileApp.utilities;

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
    // location-related data
    private static String country;
    private static String region;
    private static String cluster;
    // questionnaire-related data
    private static Integer currentQuestionnaireID;
    private static String HouseholdRosterQuestionnaireStartDate;
    // patient-related data
    private static String currentPatientID;


    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Constants.token = token;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        Constants.country = country;
    }

    public static String getRegion() {
        return region;
    }

    public static void setRegion(String region) {
        Constants.region = region;
    }

    public static String getCluster() {
        return cluster;
    }

    public static void setCluster(String cluster) {
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

    public static String getHouseholdRosterQuestionnaireStartDate() {
        return HouseholdRosterQuestionnaireStartDate;
    }

    public static void setHouseholdRosterQuestionnaireStartDate(String householdRosterQuestionnaireStartDate) {
        HouseholdRosterQuestionnaireStartDate = householdRosterQuestionnaireStartDate;
    }


    /* Other static final constants */

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

    public static final String POST_RESPONSE_URL = "http://10.0.2.2:8000/tables/Response/";

    // Questionnaires
    public static final Integer HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID = 2;
}
