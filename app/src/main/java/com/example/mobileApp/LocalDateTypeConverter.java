package com.example.mobileApp;

import android.annotation.TargetApi;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// SQLite DB stores Dates as Strings in YYYY-MM-DD format
// This type converter methods can only be used at Target API level 26

public class LocalDateTypeConverter
{
    @TypeConverter @TargetApi(26)
    public static LocalDate toLocalDate(String dateString)
    {
        LocalDate date;

        String[] dateComponents = dateString.split("-");

        int year = Integer.valueOf((String) dateComponents[0]);
        int month = Integer.valueOf((String) dateComponents[1]);
        int day = Integer.valueOf((String) dateComponents[2]);

        return date = LocalDate.of(year, month, day);

    }

    @TypeConverter @TargetApi(26)
    public static String toStringDate(LocalDate date)
    {
        String dateString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return dateString = date.format(formatter);
    }
}
