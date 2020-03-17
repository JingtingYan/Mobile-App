package com.example.mobileApp;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mobileApp.database.MobileAppDatabase;
import com.example.mobileApp.database.dao.AnswerDao;
import com.example.mobileApp.database.dao.LocationDao;
import com.example.mobileApp.database.dao.PatientAssessmentStatusDao;
import com.example.mobileApp.database.dao.QuestionAnswerDao;
import com.example.mobileApp.database.dao.QuestionDao;
import com.example.mobileApp.database.dao.ResponseDao;
import com.example.mobileApp.database.entity.AnswerTable;
import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
import com.example.mobileApp.database.entity.QuestionAnswerTable;
import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.utilities.SampleData;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private static final String TAG = "JUnit";
    private MobileAppDatabase database;

    private AnswerDao answerDao;
    private LocationDao locationDao;
    private QuestionDao questionDao;
    private QuestionAnswerDao questionAnswerDao;
    private ResponseDao responseDao;
    private PatientAssessmentStatusDao patientAssessmentStatusDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, MobileAppDatabase.class).build();

        answerDao = database.answerDao();
        locationDao = database.locationDao();
        questionDao = database.questionDao();
        questionAnswerDao = database.questionAnswerDao();
        responseDao = database.responseDao();
        patientAssessmentStatusDao = database.patientAssessmentStatusDao();

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

    @Test
    public void getAllQuestionsInQuestionnaire() {
        int questionnaireID = 2;
        questionDao.insertAll(SampleData.getQuestions());
        List<Integer> allQuestionsID = questionDao.getAllQnsID(questionnaireID);

        assertEquals(20, allQuestionsID.size());
    }

    @Test
    public void getSingleQuestionInQuestionnaire() {
        List<QuestionTable> questionTables = SampleData.getQuestions();
        questionDao.insertAll(questionTables);

        QuestionTable expectedFirstQn = questionTables.get(0);
        QuestionTable queriedFirstQn  = questionDao.getQuestion(questionTables.get(0).getQuestion_id(), questionTables.get(0).getQnnaire_id());

        assertEquals(expectedFirstQn.getQuestion_instruction(), queriedFirstQn.getQuestion_instruction());
        assertEquals(expectedFirstQn.getQuestion_string(), queriedFirstQn.getQuestion_string());
        assertEquals(expectedFirstQn.getQ_type_id(), queriedFirstQn.getQ_type_id());
    }

    @Test
    public void getSingleAnswerChoiceByID() {
        List<AnswerTable> answerTables = SampleData.getAnswers();
        answerDao.insertAll(answerTables);

        AnswerTable expectedFirstAns = answerTables.get(0);
        AnswerTable queriedFirstAns = answerDao.getAnswer(answerTables.get(0).getAnswer_id(), answerTables.get(0).getQnnaire_id());

        assertEquals(expectedFirstAns.getAnswer_string(), queriedFirstAns.getAnswer_string());
    }

    @Test
    public void getLastIndex() {
        // manually entered index starts from 0
        List<QuestionAnswerTable> questionAnswerTables = SampleData.getQAs();
        questionAnswerDao.insertAll(questionAnswerTables);

        int lastQAIndex = questionAnswerDao.getLastIndex();
        assertEquals(60, lastQAIndex);
    }

    @Test
    public void getLastIndexForEmptyTable() {
        // make QA table empty
        List<QuestionAnswerTable> questionAnswerTables = new ArrayList<>();
        questionAnswerDao.insertAll(questionAnswerTables);

        int lastQAIndex = questionAnswerDao.getLastIndex();
        assertEquals(0, lastQAIndex);
    }

    @Test
    public void jsonOptINTParseNullToTheFallbackValue() {
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
    public void findExistingAssessmentInPatientAssessmentStatusTable() {
        List<PatientAssessmentStatusTable> tables = new ArrayList<>();
        tables.add(new PatientAssessmentStatusTable(1, "001", 1, "INCOMPLETE", "2020-03-12", "", 21));
        patientAssessmentStatusDao.insertAll(tables);

        PatientAssessmentStatusTable result1 = patientAssessmentStatusDao.findExistingAssessment("001", 1, "2020-03-12");
        boolean boolean1 = (result1 == null);
        PatientAssessmentStatusTable result2 = patientAssessmentStatusDao.findExistingAssessment("002", 1, "2020-03-12");
        boolean boolean2 = (result2 == null);

        assertFalse(boolean1);
        assertTrue(boolean2);
    }
}
