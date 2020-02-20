package com.example.mobileApp.utilities;

public class Constants {

    private static String TOKEN;

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        Constants.TOKEN = TOKEN;
    }

    public static final String LOGIN_URL = "http://10.0.2.2:8000/tables/login/";

    public static final String GET_LOCATION_URL = "http://10.0.2.2:8000/tables/Location/";

}
