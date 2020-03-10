package com.example.mobileApp.utilities;

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
    private static Integer currentQuestionnaireID;
    private static String HouseholdRosterQuestionnaireDate;
    // patient-related data
    private static String currentPatientID;
    private static String currentHouseholdID;


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

    public static String getHouseholdRosterQuestionnaireDate() {
        return HouseholdRosterQuestionnaireDate;
    }

    public static void setHouseholdRosterQuestionnaireDate(String householdRosterQuestionnaireStartDate) {
        HouseholdRosterQuestionnaireDate = householdRosterQuestionnaireStartDate;
    }

    public static String getCurrentHouseholdID() {
        return currentHouseholdID;
    }

    public static void setCurrentHouseholdID(String currentHouseholdID) {
        Constants.currentHouseholdID = currentHouseholdID;
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
    // add other APIs route here in the future

    // Questionnaires
    public static final int HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID = 1;
    public static final int GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID = 2;
    public static final int MOBILITY_QUESTIONNAIRE_ID = 3;
    // add other questionnaires' ID here in the future
}
