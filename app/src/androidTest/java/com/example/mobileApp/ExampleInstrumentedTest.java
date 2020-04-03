package com.example.mobileApp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 * The ExampleInstrumentedTest class contains a list of tests to test functions defined to parse or create
 * Json objects/arrays in Repository and ViewModel.
 *
 * @author Jingting Yan
 * @version 1.0
 * @since March 2020
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.mobileApp", appContext.getPackageName());
    }

    @Test
    public void jsonOptIntParseNullToTheFallbackValue() {
        JSONObject jsonObject = new JSONObject();
        Integer value1 = null;
        Integer value2 = 10;

        try {
            jsonObject.put("value1", value1);
            jsonObject.put("value2", value2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int parsedValue1 = jsonObject.optInt("value1", -1);
        int parsedValue2 = jsonObject.optInt("value2", 10);
        assertEquals(-1, parsedValue1);
        assertEquals(10, parsedValue2);
    }

    @Test
    public void parseLocalDateFromJsonObject() {
        LocalDate date = LocalDate.of(2020, 3, 16);
        JSONObject object = new JSONObject();
        try {
            object.put("date" , date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LocalDate parsedDate = (LocalDate) object.opt("date");

        assertEquals(LocalDate.of(2020, 3, 16), parsedDate);
    }

    @Test
    public void parseNullLocalDateFromJsonObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("date" , null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LocalDate parsedDate = (LocalDate) object.opt("date");

        assertNull(parsedDate);
    }
}
