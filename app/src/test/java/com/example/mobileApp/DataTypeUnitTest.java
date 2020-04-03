package com.example.mobileApp;

import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.datatype.AssessmentRecyclerViewItem;
import com.example.mobileApp.datatype.HouseholdRecyclerViewItem;
import com.example.mobileApp.datatype.Location;
import com.example.mobileApp.datatype.PatientRecyclerViewItem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Test, which will execute on the development machine (host).
 * The DataTypeUnitTest class contains a list of tests to test methods defined in user-defined data types.
 *
 * @author Jingting Yan
 * @version 1.0
 * @since March 2020
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataTypeUnitTest {
    @Test
    public void testGetAssessmentInfoToFilter_isCorrect() {
        AssessmentRecyclerViewItem item = new AssessmentRecyclerViewItem("Mobility Questionnaire",
                "INCOMPLETE", 2, "2020-03-03", "", 58);
        String filteredInfo = item.getAssessmentInfoToFilter();
        String correctInfo = "Mobility Questionnaire" + " " + "2020-03-03" + " " + "";
        assertEquals(correctInfo, filteredInfo);
    }

    @Test
    public void testGetHouseholdInfoToFilter_isCorrect() {
        HouseholdRecyclerViewItem item = new HouseholdRecyclerViewItem("1",
                "Java Street", "James Black");
        String filteredInfo = item.getHouseholdInfoToFilter();
        String correctInfo = "Java Street" + " " + "James Black";
        assertEquals(correctInfo, filteredInfo);
    }

    @Test
    public void testGetPatientInfoToFilter_isCorrect() {
        PatientRecyclerViewItem item = new PatientRecyclerViewItem("12345", "James Black",
                "1987-09-12", "123", "1");
        String filteredInfo = item.getPatientInfoToFilter();
        String correctInfo = "12345" + " " + "James Black";
        assertEquals(correctInfo, filteredInfo);
    }

    @Test
    public void testAnswerToString_isCorrect() {
        Answer answer = new Answer(1, "answer");
        String answerToString = answer.toString();
        String correct = "answer";
        assertEquals(correct, answerToString);
    }

    @Test
    public void testLocationToString_isCorrect() {
        Location location = new Location(1, "London");
        String locationToString = location.toString();
        String correct = "London";
        assertEquals(correct, locationToString);
    }

}