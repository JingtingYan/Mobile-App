package com.example.mobileApp.database;

import android.annotation.TargetApi;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// SQLite DB stores Dates as Strings in YYYY-MM-DD format
// This type converter methods can only be used at Target API level 26

public class LocalDateTypeConverter {
    @TypeConverter @TargetApi(26)
    public static LocalDate toLocalDate(String dateString) {
        if (dateString == null) {
            // default value is "0000-01-01"
            return LocalDate.of(0, 1,1);
        } else {
            String[] dateComponents = dateString.split("-");

            int year = Integer.parseInt(dateComponents[0]);
            int month = Integer.parseInt(dateComponents[1]);
            int day = Integer.parseInt(dateComponents[2]);

            return LocalDate.of(year, month, day);
        }
    }

    @TypeConverter @TargetApi(26)
    public static String toStringDate(LocalDate date) {
        if (date == null) {
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(formatter);
        }
    }
}
