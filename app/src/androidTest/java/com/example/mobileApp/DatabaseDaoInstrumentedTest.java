package com.example.mobileApp;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mobileApp.database.MobileAppDatabase;
import com.example.mobileApp.database.dao.AnswerDao;
import com.example.mobileApp.database.dao.HouseholdDao;
import com.example.mobileApp.database.dao.LocationDao;
import com.example.mobileApp.database.dao.LogicDao;
import com.example.mobileApp.database.dao.PatientAssessmentStatusDao;
import com.example.mobileApp.database.dao.PatientDao;
import com.example.mobileApp.database.dao.QuestionAnswerDao;
import com.example.mobileApp.database.dao.QuestionDao;
import com.example.mobileApp.database.dao.QuestionnaireDao;
import com.example.mobileApp.database.dao.ResponseDao;
import com.example.mobileApp.database.entity.AnswerTable;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.database.entity.LogicTable;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.database.entity.QuestionAnswerTable;
import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.database.entity.QuestionnaireTable;
import com.example.mobileApp.database.entity.ResponseTable;
import com.example.mobileApp.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Instrumented test, which will execute on an Android device or emulator.
 * The DatabaseDaoInstrumentedTest class contains a list of tests to test queries defined in the Dao classes.
 *
 * @author Jingting Yan
 * @version 1.0
 * @since March 2020
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseDaoInstrumentedTest {
    private static final String TAG = "JUnit";
    private MobileAppDatabase database;

    private AnswerDao answerDao;
    private HouseholdDao householdDao;
    private LocationDao locationDao;
    private LogicDao logicDao;
    private PatientAssessmentStatusDao patientAssessmentStatusDao;
    private PatientDao patientDao;
    private QuestionAnswerDao questionAnswerDao;
    private QuestionDao questionDao;
    private QuestionnaireDao questionnaireDao;
    private ResponseDao responseDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, MobileAppDatabase.class).build();

        answerDao = database.answerDao();
        householdDao = database.householdDao();
        locationDao = database.locationDao();
        logicDao = database.logicDao();
        patientAssessmentStatusDao = database.patientAssessmentStatusDao();
        patientDao = database.patientDao();
        questionAnswerDao = database.questionAnswerDao();
        questionDao = database.questionDao();
        questionnaireDao = database.questionnaireDao();
        responseDao = database.responseDao();

        Log.i(TAG, "database created");
    }

    @After
    public void closeDb() {
        database.close();
        Log.i(TAG, "database closed");
    }

    /* AnswerDao Tests */
    @Test
    public void getSingleAnswerChoiceByID_isCorrect() {
        answerDao.insertAll(SampleData.getAnswers());
        AnswerTable answer = answerDao.getAnswer(1, 2);

        assertEquals("Well", answer.getAnswer_string());
    }


    /* HouseholdDao Tests */
    @Test
    public void getHouseholdsForCluster_isCorrect() {
        householdDao.insertAll(SampleData.getHouseholds());
        List<HouseholdTable> households = householdDao.getHouseholdsForCluster(34);

        assertEquals(2, households.size());
        assertEquals("1", households.get(0).getHousehold_id());
        assertEquals("2", households.get(1).getHousehold_id());
    }

    @Test
    public void countAllHouseholds_isCorrect() {
        householdDao.insertAll(SampleData.getHouseholds());
        int count = householdDao.countAllHouseholds();

        assertEquals(3, count);
    }

    @Test
    public void getAllHouseholdsToUpload_isCorrect() {
        householdDao.insertAll(SampleData.getHouseholds());
        List<HouseholdTable> households = householdDao.getAllHouseholdsToUpload();

        assertEquals(2, households.size());
        assertEquals("2", households.get(0).getHousehold_id());
        assertEquals("3", households.get(1).getHousehold_id());
    }


    /* LocationDao Tests */
    @Test
    public void getAllCountries_isCorrect() {
        locationDao.insertAll(SampleData.getLocations());
        List<LocationTable> countries = locationDao.getAllCountries();

        assertEquals(4, countries.size());
        assertEquals("Federal Republic of Nigeria", countries.get(0).getLocation_name());
        assertEquals("India", countries.get(1).getLocation_name());
        assertEquals("Philippines", countries.get(2).getLocation_name());
        assertEquals("Indonesia", countries.get(3).getLocation_name());
    }

    @Test
    public void getRegionsForCountry_isCorrect() {
        locationDao.insertAll(SampleData.getLocations());
        List<LocationTable> regions = locationDao.getRegions(1);

        assertEquals(3, regions.size());
        assertEquals("Ado", regions.get(0).getLocation_name());
        assertEquals("Bauchi", regions.get(1).getLocation_name());
        assertEquals("Sokoto", regions.get(2).getLocation_name());
    }

    @Test
    public void getClustersForRegion_isCorrect() {
        locationDao.insertAll(SampleData.getLocations());
        List<LocationTable> clusters = locationDao.getClusters(5);

        assertEquals(3, clusters.size());
        assertEquals("Cluster A1", clusters.get(0).getLocation_name());
        assertEquals("Cluster A2", clusters.get(1).getLocation_name());
        assertEquals("Cluster B1", clusters.get(2).getLocation_name());
    }


    /* LogicDao Tests*/
    @Test
    public void getFirstQuestionInQuestionnaire_isCorrect() {
        logicDao.insertAll(SampleData.getLogic());
        LogicTable logic1 = logicDao.getFirstQn(2);
        LogicTable logic2 = logicDao.getFirstQn(3);

        assertEquals(1, (int) logic1.getQ_id());
        assertEquals(21, (int) logic2.getQ_id());
    }


    /* PatientAssessmentStatusDao Tests */
    @Test
    public void getAllAssessmentStatusForPatient_isCorrect() {
        patientAssessmentStatusDao.insertAll(SampleData.getAssessmentStatus());
        List<PatientAssessmentStatusTable> tables = patientAssessmentStatusDao.getAssessmentStatusForPatient("001");

        String correctStatus1 = "1" + " " + "INCOMPLETE" + " " + "2020-03-12" + " " + "" + " " + "21";
        String correctStatus2 = "2" + " " + "COMPLETE" + " " + "2020-03-15" + " " + "2020-03-15" + " " + "-1";
        String queriedStatus1 = tables.get(0).getQnnaire_id() + " " + tables.get(0).getQnnaire_status() + " " +
                tables.get(0).getStart() + " " + tables.get(0).getEnd() + " " + tables.get(0).getLast_answered_qn_id();
        String queriedStatus2 = tables.get(1).getQnnaire_id() + " " + tables.get(1).getQnnaire_status() + " " +
                tables.get(1).getStart() + " " + tables.get(1).getEnd() + " " + tables.get(1).getLast_answered_qn_id();

        assertEquals(2, tables.size());
        assertEquals(correctStatus1, queriedStatus1);
        assertEquals(correctStatus2, queriedStatus2);
    }

    @Test
    public void findExistingAssessmentStatusForPatient_isCorrect() {
        patientAssessmentStatusDao.insertAll(SampleData.getAssessmentStatus());
        PatientAssessmentStatusTable result = patientAssessmentStatusDao.findExistingAssessment("001", 1, "2020-03-12");

        assertNotNull(result);
    }

    @Test
    public void findNonExistingAssessmentStatusForPatient_isNull() {
        patientAssessmentStatusDao.insertAll(SampleData.getAssessmentStatus());
        PatientAssessmentStatusTable result = patientAssessmentStatusDao.findExistingAssessment("002", 2, "2020-03-12");

        assertNull(result);
    }

    @Test
    public void getLastIndexInTable_isCorrect() {
        patientAssessmentStatusDao.insertAll(SampleData.getAssessmentStatus());
        int lastIndex = patientAssessmentStatusDao.getLastIndex();

        assertEquals(3, lastIndex);
    }

    @Test
    public void getAllAssessmentStatusInTable_isCorrect() {
        patientAssessmentStatusDao.insertAll(SampleData.getAssessmentStatus());
        List<PatientAssessmentStatusTable> statusTables = patientAssessmentStatusDao.getAllAssessmentStatus();

        assertEquals(3, statusTables.size());
    }


    /* PatientDao Tests*/
    @Test
    public void getAllPatientsInTable_isCorrect() {
        patientDao.insertAll(SampleData.getPatients());
        List<PatientTable> patients = patientDao.getAllPatients();

        assertEquals(7, patients.size());
    }

    @Test
    public void getPatientsForHouseholdByHouseholdID_isCorrect() {
        patientDao.insertAll(SampleData.getPatients());
        List<PatientTable> patients1 = patientDao.getPatientsForHousehold("1");
        List<PatientTable> patients2 = patientDao.getPatientsForHousehold("2");

        assertEquals(4, patients1.size());
        assertEquals(3, patients2.size());
    }

    @Test
    public void getAllPatientsToUpload_isCorrect() {
        patientDao.insertAll(SampleData.getPatients());
        List<PatientTable> patients = patientDao.getAllPatientsToUpload();

        assertEquals(4, patients.size());
    }

    @Test
    public void countAllPatientsInTable_isCorrect() {
        patientDao.insertAll(SampleData.getPatients());
        int count = patientDao.countAllPatients();

        assertEquals(7, count);
    }


    /* QuestionAnswerDao Tests */
    @Test
    public void getAllAnsIDForQuestionByQuestionIDAndQuestionnaireID_isCorrect() {
        questionAnswerDao.insertAll(SampleData.getQAs());
        List<Integer> answerIDs = questionAnswerDao.getAllAnsID(1, 2);

        assertEquals(3, answerIDs.size());
        assertEquals(1, (int) answerIDs.get(0));
        assertEquals(2, (int) answerIDs.get(1));
        assertEquals(3, (int) answerIDs.get(2));
    }


    /* QuestionDao Tests */
    @Test
    public void getAllQuestionsInQuestionnaireByQuestionnaireID_isCorrect() {
        questionDao.insertAll(SampleData.getQuestions());
        List<Integer> allQuestionsID = questionDao.getAllQnsID(2);

        assertEquals(20, allQuestionsID.size());
    }

    @Test
    public void getSingleQuestionByQuestionIDAndQuestionnaireID_isCorrect() {
        questionDao.insertAll(SampleData.getQuestions());

        QuestionTable question  = questionDao.getQuestion(1, 2);

        assertEquals("", question.getQuestion_instruction());
        assertEquals("Can [you/your child] read well, a little or not at all?", question.getQuestion_string());
        assertEquals(1, (int) question.getQ_type_id());
    }


    /* QuestionnaireDao Tests */
    @Test
    public void getSingleQuestionnaireByQuestionnaireID_isCorrect() {
        questionnaireDao.insertAll(SampleData.getQuestionnaires());
        QuestionnaireTable questionnaire = questionnaireDao.getSingleQuestionnaireInfo(1);

        assertEquals("Household Roster Questionnaire", questionnaire.getQuestionnaire_name());
        assertEquals("1", questionnaire.getQuestionnaire_version());
        assertEquals("HOUSEHOLD", questionnaire.getQuestionnaire_type());
    }

    @Test
    public void getQuestionnaireIDByQuestionnaireType_isCorrect() {
        questionnaireDao.insertAll(SampleData.getQuestionnaires());
        int id = questionnaireDao.getQuestionnaireID("HEARING");

        assertEquals(4, id);
    }


    /* ResponseDao Tests */
    @Test
    public void getAllResponsesToUpload_isCorrect() {
        responseDao.insertAll(SampleData.getResponses());
        List<ResponseTable> responses = responseDao.getAllResponsesToUpload();

        assertEquals(5, responses.size());
    }

    @Test
    public void getResponsesForCurrNonTextEntryQuestion_isCorrect() {
        responseDao.insertAll(SampleData.getResponses());
        List<Integer> responses1 = responseDao.getResponsesForCurrQuestion("001", 1, 2);
        List<Integer> responses2 = responseDao.getResponsesForCurrQuestion("002", 6, 1);

        assertEquals(1, responses1.size());
        assertEquals(2, responses2.size());
        assertEquals(1, (int) responses1.get(0));
        assertEquals(40, (int) responses2.get(0));
        assertEquals(41, (int) responses2.get(1));
    }


    /* Others */
    @Test
    public void getLastIndexForEmptyTable() {
        // make QA table empty
        List<QuestionAnswerTable> questionAnswerTables = new ArrayList<>();
        questionAnswerDao.insertAll(questionAnswerTables);

        int lastQAIndex = questionAnswerDao.getLastIndex();
        assertEquals(0, lastQAIndex);
    }
}
