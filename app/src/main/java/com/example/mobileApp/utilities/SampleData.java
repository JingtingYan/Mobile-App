package com.example.mobileApp.utilities;

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

import java.util.ArrayList;
import java.util.List;

/* Pre-populate some dummy data into SQLite database -- used for instrumented testing only */

public class SampleData {

    /* pre-populate the Answer Table */
    public static List<AnswerTable> getAnswers() {

        List<AnswerTable> answers = new ArrayList<>();

        answers.add(new AnswerTable(1,"Well",2));
        answers.add(new AnswerTable(2,"A little",2));
        answers.add(new AnswerTable(3,"Not at all",2));
        answers.add(new AnswerTable(4,"Never Attended",2));
        answers.add(new AnswerTable(5,"Primary School",2));
        answers.add(new AnswerTable(6,"Middle School",2));
        answers.add(new AnswerTable(7,"Secondary School/High School",2));
        answers.add(new AnswerTable(8,"Vocation School/Technical Certificate",2));
        answers.add(new AnswerTable(9,"Bachelor's Degree",2));
        answers.add(new AnswerTable(10,"Master's Degree",2));
        answers.add(new AnswerTable(11,"Doctorate",2));
        answers.add(new AnswerTable(12,"Blue",2));
        answers.add(new AnswerTable(13,"Red",2));
        answers.add(new AnswerTable(14,"Yellow",2));
        answers.add(new AnswerTable(15,"Green",2));
        answers.add(new AnswerTable(16,"Baby Blue",2));
        answers.add(new AnswerTable(17,"Deep Blue",2));
        answers.add(new AnswerTable(18,"Tiffany Blue",2));
        answers.add(new AnswerTable(19,"Football",2));
        answers.add(new AnswerTable(20,"Netball",2));
        answers.add(new AnswerTable(21,"Tennis",2));
        answers.add(new AnswerTable(22,"Yes",2));
        answers.add(new AnswerTable(23,"No",2));
        answers.add(new AnswerTable(24,"Thai Cuisine",2));
        answers.add(new AnswerTable(25,"Chinese Cuisine",2));
        answers.add(new AnswerTable(26,"American Cuisine",2));
        answers.add(new AnswerTable(27,"Arabic Cuisine",2));
        answers.add(new AnswerTable(28,"Marvel",2));
        answers.add(new AnswerTable(29,"DC",2));
        answers.add(new AnswerTable(30,"Iron Man",2));
        answers.add(new AnswerTable(31,"The Hulk",2));
        answers.add(new AnswerTable(32,"Thor",2));
        answers.add(new AnswerTable(33,"Superman",2));
        answers.add(new AnswerTable(34,"Batman",2));
        answers.add(new AnswerTable(35,"The Flash",2));
        answers.add(new AnswerTable(36,"Iron Man 1",2));
        answers.add(new AnswerTable(37,"Iron Man 2",2));
        answers.add(new AnswerTable(38,"Iron Man 3",2));
        answers.add(new AnswerTable(39,"Healthy",8));
        answers.add(new AnswerTable(59,"I'm Fine",1));
        answers.add(new AnswerTable(60,"I'm Not Fine",1));
        answers.add(new AnswerTable(65,"I'm okay",8));
        answers.add(new AnswerTable(66,"I'm not okay",8));
        answers.add(new AnswerTable(101,"Wheelchair",8));
        answers.add(new AnswerTable(102,"Cane",8));
        answers.add(new AnswerTable(104,"Prosthetics",8));
        answers.add(new AnswerTable(105,"Orthoses",8));
        answers.add(new AnswerTable(106,"Protective footwear",8));
        answers.add(new AnswerTable(107,"Grab bars",8));
        answers.add(new AnswerTable(108,"Ramp",8));
        answers.add(new AnswerTable(201,"Hearing Aid",4));
        answers.add(new AnswerTable(202,"Closed Captioning TV",4));
        answers.add(new AnswerTable(203,"Video Communication Devices",4));

        return answers;
    }


    /* pre-populate the Location Table */
    public static List<LocationTable> getLocations() {

        List<LocationTable> locations = new ArrayList<>();

        // pre-populate countries
        locations.add(new LocationTable(1, "Federal Republic of Nigeria", -1));
        locations.add(new LocationTable(2,"India", -1));
        locations.add(new LocationTable(3,"Philippines", -1));
        locations.add(new LocationTable(4,"Indonesia", -1));

        // pre-populate regions
        locations.add(new LocationTable(5,"Ado", 1));
        locations.add(new LocationTable(6,"Bauchi", 1));
        locations.add(new LocationTable(7,"Sokoto", 1));
        locations.add(new LocationTable(8,"Srinigar", 2));
        locations.add(new LocationTable(9,"Chandigarh", 2));
        locations.add(new LocationTable(10,"Simla", 2));
        locations.add(new LocationTable(11,"Manila", 3));
        locations.add(new LocationTable(12,"Java", 4));
        locations.add(new LocationTable(13,"Sumatera", 4));
        locations.add(new LocationTable(14,"Palembang", 4));

        // pre-populate clusters
        locations.add(new LocationTable(15,"Cluster A1", 5));
        locations.add(new LocationTable(16,"Cluster A2", 5));
        locations.add(new LocationTable(17,"Cluster B1", 5));
        locations.add(new LocationTable(18,"Cluster A1", 6));
        locations.add(new LocationTable(19,"Cluster A2", 6));
        locations.add(new LocationTable(20,"Cluster A3", 6));

        return locations;
    }


    /* pre-populate the Logic Table */
    public static List<LogicTable> getLogic() {

        List<LogicTable> logic = new ArrayList<>();

        logic.add(new LogicTable(1, 1, 1, -1, 2, "", -1, 2));
        logic.add(new LogicTable(2, 2, 2, -1, 3, "", -1, 2));
        logic.add(new LogicTable(3, 3, 3, -1, 4, "", -1, 2));
        logic.add(new LogicTable(4, 4, 4, 12, 5, "NEXT", -1, 2));
        logic.add(new LogicTable(5, 5, 4, -1, 6, "", -1, 2));
        logic.add(new LogicTable(6, 6, 5, -1, 6, "", -1, 2));
        logic.add(new LogicTable(7, 7, 6,  -1, 7, "AND", 1, 2));
        logic.add(new LogicTable(8, 8, 6, -1, 8, "", -1, 2));
        logic.add(new LogicTable(9, 9, 7, -1, 8, "", -1, 2));
        logic.add(new LogicTable(10,10, 8, -1, 9, "", -1, 2));
        logic.add(new LogicTable(11,11, 9, -1, 10, "OR", 2, 2));
        logic.add(new LogicTable(12,12, 9, -1, 11, "", -1, 2));
        logic.add(new LogicTable(13,13, 10, -1, 11, "", -1, 2));
        logic.add(new LogicTable(14,14, 11, -1, 12, "", -1, 2));
        logic.add(new LogicTable(15,15, 12, 28, 13, "NEXT", -1, 2));
        logic.add(new LogicTable(16,16, 12, 29, 14, "NEXT", -1, 2));
        logic.add(new LogicTable(17,17, 13, 30, 15, "NEXT", -1, 2));
        logic.add(new LogicTable(18,18, 13, -1, 17, "", -1, 2));
        logic.add(new LogicTable(19,19, 14, -1, 16, "AND", 3, 2));
        logic.add(new LogicTable(20,20, 14, -1, 17, "", -1, 2));
        logic.add(new LogicTable(21,21, 15, -1, 17, "", -1, 2));
        logic.add(new LogicTable(22,22, 16, -1, 17, "", -1, 2));
        logic.add(new LogicTable(23,23, 17, -1, 18, "", -1, 2));
        logic.add(new LogicTable(24,24, 18, -1, 19, "OR", 4, 2));
        logic.add(new LogicTable(25,25, 18, -1, 20, "", -1, 2));
        logic.add(new LogicTable(26,26, 19,  -1, 20, "", -1, 2));
        logic.add(new LogicTable(27,27, 20, -1, -1, "", -1, 2));
        logic.add(new LogicTable(28,28, 14, -1, 17, "AND", 5, 2));
        logic.add(new LogicTable(29, 1, 21, -1, 22, "", -1, 3));
        logic.add(new LogicTable(30, 2, 22, -1, -1, "", -1, 3));

        return logic;
    }


    /* pre-populate the PatientAssessmentStatus Table */
    public static List<PatientAssessmentStatusTable> getAssessmentStatus() {
        List<PatientAssessmentStatusTable> statusTables = new ArrayList<>();

        statusTables.add(new PatientAssessmentStatusTable(1, "001", 1, "INCOMPLETE", "2020-03-12", "", 21));
        statusTables.add(new PatientAssessmentStatusTable(2, "001", 2, "COMPLETE", "2020-03-15", "2020-03-15", -1));
        statusTables.add(new PatientAssessmentStatusTable(3, "002", 2, "COMPLETE", "2020-03-18", "2020-03-18", -1));

        return statusTables;
    }


    /* pre-populate the QuestionAnswer Table */
    public static List<QuestionAnswerTable> getQAs() {

        List<QuestionAnswerTable> questionAnswers = new ArrayList<>();

        questionAnswers.add(new QuestionAnswerTable(0,45,59,1));
        questionAnswers.add(new QuestionAnswerTable(1,45,60,1));
        questionAnswers.add(new QuestionAnswerTable(2,1,1,2));
        questionAnswers.add(new QuestionAnswerTable(3,1,2,2));
        questionAnswers.add(new QuestionAnswerTable(4,1,3,2));
        questionAnswers.add(new QuestionAnswerTable(5,2,1,2));
        questionAnswers.add(new QuestionAnswerTable(6,2,2,2));
        questionAnswers.add(new QuestionAnswerTable(7,2,3,2));
        questionAnswers.add(new QuestionAnswerTable(8,3,4,2));
        questionAnswers.add(new QuestionAnswerTable(9,3,5,2));
        questionAnswers.add(new QuestionAnswerTable(10,3,6,2));
        questionAnswers.add(new QuestionAnswerTable(11,3,7,2));
        questionAnswers.add(new QuestionAnswerTable(12,3,8,2));
        questionAnswers.add(new QuestionAnswerTable(13,3,9,2));
        questionAnswers.add(new QuestionAnswerTable(14,3,10,2));
        questionAnswers.add(new QuestionAnswerTable(15,3,11,2));
        questionAnswers.add(new QuestionAnswerTable(16,4,12,2));
        questionAnswers.add(new QuestionAnswerTable(17,4,13,2));
        questionAnswers.add(new QuestionAnswerTable(18,4,14,2));
        questionAnswers.add(new QuestionAnswerTable(19,4,15,2));
        questionAnswers.add(new QuestionAnswerTable(20,5,16,2));
        questionAnswers.add(new QuestionAnswerTable(21,5,17,2));
        questionAnswers.add(new QuestionAnswerTable(22,5,18,2));
        questionAnswers.add(new QuestionAnswerTable(23,6,19,2));
        questionAnswers.add(new QuestionAnswerTable(24,6,20,2));
        questionAnswers.add(new QuestionAnswerTable(25,6,21,2));
        questionAnswers.add(new QuestionAnswerTable(26,7,22,2));
        questionAnswers.add(new QuestionAnswerTable(27,7,23,2));
        questionAnswers.add(new QuestionAnswerTable(28,8,22,2));
        questionAnswers.add(new QuestionAnswerTable(29,8,23,2));
        questionAnswers.add(new QuestionAnswerTable(30,9,24,2));
        questionAnswers.add(new QuestionAnswerTable(31,9,25,2));
        questionAnswers.add(new QuestionAnswerTable(32,9,26,2));
        questionAnswers.add(new QuestionAnswerTable(33,9,27,2));
        questionAnswers.add(new QuestionAnswerTable(34,10,22,2));
        questionAnswers.add(new QuestionAnswerTable(35,10,23,2));
        questionAnswers.add(new QuestionAnswerTable(36,11,22,2));
        questionAnswers.add(new QuestionAnswerTable(37,11,23,2));
        questionAnswers.add(new QuestionAnswerTable(38,12,28,2));
        questionAnswers.add(new QuestionAnswerTable(39,12,29,2));
        questionAnswers.add(new QuestionAnswerTable(40,13,30,2));
        questionAnswers.add(new QuestionAnswerTable(41,13,31,2));
        questionAnswers.add(new QuestionAnswerTable(42,13,32,2));
        questionAnswers.add(new QuestionAnswerTable(43,14,33,2));
        questionAnswers.add(new QuestionAnswerTable(44,14,34,2));
        questionAnswers.add(new QuestionAnswerTable(45,14,35,2));
        questionAnswers.add(new QuestionAnswerTable(46,15,36,2));
        questionAnswers.add(new QuestionAnswerTable(47,15,37,2));
        questionAnswers.add(new QuestionAnswerTable(48,15,38,2));
        questionAnswers.add(new QuestionAnswerTable(49,16,33,2));
        questionAnswers.add(new QuestionAnswerTable(50,16,34,2));
        questionAnswers.add(new QuestionAnswerTable(51,17,22,2));
        questionAnswers.add(new QuestionAnswerTable(52,17,23,2));
        questionAnswers.add(new QuestionAnswerTable(53,18,22,2));
        questionAnswers.add(new QuestionAnswerTable(54,18,23,2));
        questionAnswers.add(new QuestionAnswerTable(55,19,22,2));
        questionAnswers.add(new QuestionAnswerTable(56,19,23,2));
        questionAnswers.add(new QuestionAnswerTable(57,20,22,2));
        questionAnswers.add(new QuestionAnswerTable(58,20,23,2));
        questionAnswers.add(new QuestionAnswerTable(59,48,65,8));
        questionAnswers.add(new QuestionAnswerTable(60,48,66,8));

        return questionAnswers;
    }


    /* pre-populate the Questionnaire Table */
    public static List<QuestionnaireTable> getQuestionnaires() {
        List<QuestionnaireTable> questionnaires = new ArrayList<>();

        questionnaires.add(new QuestionnaireTable(1,"Household Roster Questionnaire","1", "HOUSEHOLD"));
        questionnaires.add(new QuestionnaireTable(2,"General Questionnaire","1", "GENERAL"));
        questionnaires.add(new QuestionnaireTable(3,"Vision Questionnaire","1", "VISION"));
        questionnaires.add(new QuestionnaireTable(4,"Hearing Questionnaire","1", "HEARING"));
        questionnaires.add(new QuestionnaireTable(8,"Mobility Questionnaire","1", "MOBILITY"));

        return questionnaires;
    }


    /* pre-populate the Question Table */
    public static List<QuestionTable> getQuestions() {
        List<QuestionTable> questions = new ArrayList<>();

        questions.add(new QuestionTable(1,"Can [you/your child] read well, a little or not at all?",1,1,1,"",2,""));
        questions.add(new QuestionTable(2,"Can [you/your child] write well, a little or not at all?",1,1,1,"",2,""));
        questions.add(new QuestionTable(3,"What is the highest level of education you completed or are currently studying",1,1,1,"",2,""));
        questions.add(new QuestionTable(4,"What are your favourite colour(s)?",2,1,3,"",2,""));
        questions.add(new QuestionTable(5,"What shade of blue do you like?",1,1,1,"",2,""));
        questions.add(new QuestionTable(6,"What sports do you play?",2,1,3,"",2,""));
        questions.add(new QuestionTable(7,"Are football and netball your favourite sports?",1,1,1,"",2,""));
        questions.add(new QuestionTable(8,"Do you like pasta?",1,1,1,"",2,""));
        questions.add(new QuestionTable(9,"What is your favourite cuisine?",2,1,3,"",2,""));
        questions.add(new QuestionTable(10,"Are you Asian?",1,1,1,"",2,""));
        questions.add(new QuestionTable(11,"Are you studying in UCL?",1,1,1,"",2,""));
        questions.add(new QuestionTable(12,"Do you prefer Marvel or DC?",1,1,1,"",2,""));
        questions.add(new QuestionTable(13,"Who are your favourite Avengers?",2,1,2,"",2,""));
        questions.add(new QuestionTable(14,"Who are your favourite DC Heroes?",2,1,2,"",2,""));
        questions.add(new QuestionTable(15,"Which is your favourite Iron Man movie?",1,1,1,"",2,""));
        questions.add(new QuestionTable(16,"Who do you love more - Superman or Batman?",1,1,1,"",2,""));
        questions.add(new QuestionTable(17,"Do you wear glasses?",1,1,1,"",2,""));
        questions.add(new QuestionTable(18,"Is your vision blurry?",1,1,1,"",2,""));
        questions.add(new QuestionTable(19,"Would you consider using contact lenses?",1,1,1,"Clap for 'yes', Shake for 'no'",2,""));
        questions.add(new QuestionTable(20,"Do you have any back pain?",1,1,1,"",2,""));
        questions.add(new QuestionTable(45,"How are you?",2,1,3,"",1,""));
        questions.add(new QuestionTable(48,"How are you",1,1,1,"",8,""));

        return questions;
    }


    /* pre-populate the Household Table */
    public static List<HouseholdTable> getHouseholds() {
        List<HouseholdTable> households = new ArrayList<>();

        households.add(new HouseholdTable("1",34,"123","2020-03-19","","",0));
        households.add(new HouseholdTable("2",34,"123","2020-03-19","","",1));
        households.add(new HouseholdTable("3",20,"124","2020-03-19","","",1));

        return households;
    }


    /* pre-populate the Household Table */
    public static List<PatientTable> getPatients() {
        List<PatientTable> patients = new ArrayList<>();

        patients.add(new PatientTable("1", "100100", "1", 0));
        patients.add(new PatientTable("2", "100100", "1", 0));
        patients.add(new PatientTable("3", "100100", "1", 0));
        patients.add(new PatientTable("4", "100100", "1", 1));
        patients.add(new PatientTable("5", "100100", "2", 1));
        patients.add(new PatientTable("6", "100100", "2", 1));
        patients.add(new PatientTable("7", "100100", "2", 1));

        return patients;
    }


    /* pre-populate the Response Table */
    public static List<ResponseTable> getResponses() {
        List<ResponseTable> responses = new ArrayList<>();

        responses.add(new ResponseTable(1, "001", 1, 1, "", 2, "2020-03-19", 0));
        responses.add(new ResponseTable(2, "001", 2, 2, "", 2, "2020-03-19", 0));
        responses.add(new ResponseTable(3, "001", 3, -1, "well", 2, "2020-03-19", 0));
        responses.add(new ResponseTable(4, "001", 4, 10, "", 2, "2020-03-19", 1));
        responses.add(new ResponseTable(5, "002", 5, 20, "", 1, "2020-03-19", 1));
        responses.add(new ResponseTable(6, "002", 6, 40, "", 1, "2020-03-19", 1));
        responses.add(new ResponseTable(7, "002", 6, 41, "", 1, "2020-03-19", 1));
        responses.add(new ResponseTable(8, "002", 7, -1, "good", 1, "2020-03-19", 1));

        return responses;
    }
}
