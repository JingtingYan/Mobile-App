package com.example.mobileApp;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mobileApp.database.MobileAppDatabase;
import com.example.mobileApp.database.dao.LocationDao;
import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "JUnit";
    private MobileAppDatabase database;
    private LocationDao locationDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, MobileAppDatabase.class).build();
        locationDao = database.locationDao();
        Log.i(TAG, "database created");
    }

    @After
    public void closeDb() {
        database.close();
        Log.i(TAG, "database closed");
    }

    @Test
    public void getAllCountries() {
        locationDao.insertAll(SampleData.getLocations());
        List<LocationTable> countries = locationDao.getAllCountries();
        int count = locationDao.countCountries();

        LocationTable original1 = SampleData.getLocations().get(0);
        LocationTable result1 = countries.get(0);
        assertEquals(original1.getLocation_name(), result1.getLocation_name());

        LocationTable original2 = SampleData.getLocations().get(1);
        LocationTable result2 = countries.get(1);
        assertEquals(original2.getLocation_name(), result2.getLocation_name());

        LocationTable original3 = SampleData.getLocations().get(2);
        LocationTable result3 = countries.get(2);
        assertEquals(original3.getLocation_name(), result3.getLocation_name());

        LocationTable original4 = SampleData.getLocations().get(3);
        LocationTable result4 = countries.get(3);
        assertEquals(original4.getLocation_name(), result4.getLocation_name());

        assertEquals(4, count);
    }
}
