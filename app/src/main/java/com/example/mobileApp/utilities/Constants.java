package com.example.mobileApp.utilities;

public class Constants {

    /* Shared data that will be used throughout the App */
    private static String token;
    private static String country;
    private static String region;
    private static String cluster;

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


    /* Other constants */
    public static final String LOGIN_URL = "http://10.0.2.2:8000/tables/login/";
    public static final String GET_LOCATION_URL = "http://10.0.2.2:8000/tables/Location/";
    public static final String GET_QUESTIONNAIRE_URL = "http://10.0.2.2:8000/tables/Questionnaire/";
    public static final String GET_QUESTION_URL = "http://10.0.2.2:8000/tables/Question/";
    public static final String GET_ANSWER_URL = "http://10.0.2.2:8000/tables/Answer/";
    public static final String GET_QA_URL = "http://10.0.2.2:8000/tables/QA/";
    public static final String GET_LOGIC_URL = "http://10.0.2.2:8000/tables/Logic/";
    public static final String GET_QUESTION_RELATION_URL = "http://10.0.2.2:8000/tables/QRel/";
}
