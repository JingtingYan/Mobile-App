package com.example.mobileApp.database;

import android.content.Context;

import com.example.mobileApp.database.entity.AnswerTable;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.database.entity.LogicTable;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.database.entity.QuestionAnswerTable;
import com.example.mobileApp.database.entity.QuestionRelationTable;
import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.database.entity.QuestionnaireTable;
import com.example.mobileApp.database.entity.ResponseTable;
import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.datatype.Location;
import com.example.mobileApp.datatype.Question;
import com.example.mobileApp.datatype.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* MobileAppRepository class is singleton */

public class MobileAppRepository {

    private static MobileAppRepository repoInstance;

    private MobileAppDatabase db;
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    public static MobileAppRepository getInstance(Context context) {
        if (repoInstance == null) {
            repoInstance = new MobileAppRepository(context);
        }
        return repoInstance;
    }

    private MobileAppRepository(Context context) {
        db = MobileAppDatabase.getDatabase(context);
    }


    /* methods used to load data for LocationActivity */
    public List<Location> getSpinnerCountries() throws ExecutionException, InterruptedException {
        Future<List<Location>> task = executor.submit(() -> {
            List<LocationTable> locationTables = db.locationDao().getAllCountries();
            List<Location> results = new ArrayList<>();

            for (LocationTable location : locationTables) {
                results.add(new Location(location.getLocation_id(), location.getLocation_name()));
            }
            return results;
        });

        return task.get();
    }

    public List<Location> getSpinnerRegions(int countryID) throws ExecutionException, InterruptedException {
        Future<List<Location>> task = executor.submit(() -> {
            List<LocationTable> locationTables = db.locationDao().getRegions(countryID);
            List<Location> results = new ArrayList<>();

            for (LocationTable location : locationTables) {
                results.add(new Location(location.getLocation_id(), location.getLocation_name()));
            }

            return results;
        });

        return task.get();
    }

    public List<Location> getSpinnerClusters(int regionID) throws ExecutionException, InterruptedException {
        Future<List<Location>> task = executor.submit(() -> {
            List<LocationTable> locationTables = db.locationDao().getClusters(regionID);
            List<Location> results = new ArrayList<>();

            for (LocationTable location : locationTables) {
                results.add(new Location(location.getLocation_id(), location.getLocation_name()));
            }

            return results;
        });

        return task.get();
    }


    /* methods used to load data for DataSyncActivity */
    public void deleteLocationData() {
        executor.execute(() -> db.locationDao().deleteAll());
    }

    public void deleteQuestionnaireData() {
        executor.execute(() -> db.questionnaireDao().deleteAll());
    }

    public void deleteQuestionData() {
        executor.execute(() -> db.questionDao().deleteAll());
    }

    public void deleteAnswerData() {
        executor.execute(() -> db.answerDao().deleteAll());
    }

    public void deleteQAData() {
        executor.execute(() -> db.questionAnswerDao().deleteAll());
    }

    public void deleteLogicData() {
        executor.execute(() -> db.logicDao().deleteAll());
    }

    public void deleteQuestionRelationData() {
        executor.execute(() -> db.questionRelationDao().deleteAll());
    }

    public void deleteResponseData() {
        executor.execute(() -> db.responseDao().deleteAll());
    }

    public void deleteHouseholdData() {
        executor.execute(() -> db.householdDao().deleteAll());
    }

    public void deletePatientData() {
        executor.execute(() -> db.patientDao().deleteAll());
    }

    public void deletePatientAssessmentData() {
        executor.execute(() -> db.patientAssessmentStatusDao().deleteAll());
    }


    public void addLocationData(String jsonArray) throws JSONException {
        List<LocationTable> locationTables = parseLocationJSONArray(jsonArray);
        executor.execute(() -> db.locationDao().insertAll(locationTables));
    }

    private List<LocationTable> parseLocationJSONArray(String jsonArray) throws JSONException {
        List<LocationTable> locationTables = new ArrayList<>();
        JSONArray locations = new JSONArray(jsonArray);

        for (int i = 0; i < locations.length(); i++) {
            JSONObject location = locations.getJSONObject(i);

            Integer location_id = location.getInt("locationID");
            String location_name = location.getString("locationName");
            Integer parent_location_id = location.getInt("parentLocID");

            locationTables.add(new LocationTable(location_id, location_name, parent_location_id));
        }

        return locationTables;
    }

    public void addQuestionnaireData(String jsonArray) throws JSONException {
        List<QuestionnaireTable> questionnaireTables = parseQuestionnaireJSONArray(jsonArray);
        executor.execute(() -> db.questionnaireDao().insertAll(questionnaireTables));
    }

    private List<QuestionnaireTable> parseQuestionnaireJSONArray(String jsonArray) throws JSONException {
        List<QuestionnaireTable> questionnaireTables = new ArrayList<>();
        JSONArray questionnaires = new JSONArray(jsonArray);

        for (int i = 0; i < questionnaires.length(); i++) {
            JSONObject questionnaire = questionnaires.getJSONObject(i);

            Integer questionnaire_id = questionnaire.getInt("questionnaireID");
            String questionnaire_name = questionnaire.getString("questionnaireName");
            String questionnaire_version = questionnaire.getString("questionnaireVersion");
            String questionnaire_type = questionnaire.getString("questionnaireType");

            questionnaireTables.add(new QuestionnaireTable(questionnaire_id, questionnaire_name, questionnaire_version, questionnaire_type));
        }

        return questionnaireTables;
    }

    public void addQuestionData(String jsonArray) throws JSONException {
        List<QuestionTable> questionTables = parseQuestionJSONArray(jsonArray);
        executor.execute(() -> db.questionDao().insertAll(questionTables));
    }

    private List<QuestionTable> parseQuestionJSONArray(String jsonArray) throws JSONException {
        List<QuestionTable> questionTables = new ArrayList<>();
        JSONArray questions = new JSONArray(jsonArray);

        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);

            Integer question_id = question.getInt("questionID");
            String question_string = question.getString("questionString");
            Integer q_type_id = question.getInt("questionTypeID");
            Integer answer_min = question.getInt("answerMin");
            Integer answer_max = question.getInt("answerMax");
            String question_instruction = question.getString("questionInstruction");
            Integer qnnaire_id = question.getInt("questionnaireID");
            String question_media = question.getString("questionMedia");

            questionTables.add(new QuestionTable(question_id, question_string, q_type_id, answer_min,
                                   answer_max, question_instruction, qnnaire_id, question_media));
        }
        return questionTables;
    }

    public void addAnswerData(String jsonArray) throws JSONException {
        List<AnswerTable> answerTables = parseAnswerJSONArray(jsonArray);
        executor.execute(() -> db.answerDao().insertAll(answerTables));
    }

    private List<AnswerTable> parseAnswerJSONArray(String jsonArray) throws JSONException {
        List<AnswerTable> answerTables = new ArrayList<>();
        JSONArray answers = new JSONArray(jsonArray);

        for (int i = 0; i < answers.length(); i++) {
            JSONObject answer = answers.getJSONObject(i);

            Integer answer_id = answer.getInt("answerID");
            String answer_string = answer.getString("answerString");
            Integer qnnaire_id = answer.getInt("questionnaireID");

            answerTables.add(new AnswerTable(answer_id, answer_string, qnnaire_id));
        }

        return answerTables;
    }

    public void addQAData(String jsonArray) throws JSONException {
        List<QuestionAnswerTable> questionAnswerTables = parseQAJSONArray(jsonArray);
        executor.execute(() -> db.questionAnswerDao().insertAll(questionAnswerTables));
    }

    private List<QuestionAnswerTable> parseQAJSONArray(String jsonArray) throws JSONException {
        List<QuestionAnswerTable> questionAnswerTables = new ArrayList<>();
        JSONArray qas = new JSONArray(jsonArray);

        for (int i = 0; i < qas.length(); i++) {
            JSONObject qa = qas.getJSONObject(i);

            Integer q_id = qa.getInt("questionID");
            Integer ans_id = qa.getInt("answerID");
            Integer qnnaire_id = qa.getInt("questionnaireID");

            questionAnswerTables.add(new QuestionAnswerTable(i, q_id, ans_id, qnnaire_id));
        }

        return questionAnswerTables;
    }

    public void addLogicData(String jsonArray) throws JSONException {
        List<LogicTable> logicTables = parseLogicJSONArray(jsonArray);
        executor.execute(() -> db.logicDao().insertAll(logicTables));
    }

    private List<LogicTable> parseLogicJSONArray(String jsonArray) throws JSONException {
        List<LogicTable> logicTables = new ArrayList<>();
        JSONArray logic = new JSONArray(jsonArray);

        for (int i = 0; i < logic.length(); i++) {
            JSONObject jsonObject = logic.getJSONObject(i);

            Integer sequence_num = jsonObject.getInt("seq_num");
            Integer q_id = jsonObject.getInt("logic_questionID");
            // If the incoming data is null, then set it to be -1 (meaningless)
            Integer rel_ans_id = jsonObject.optInt("rel_ans_ID", -1);
            Integer next_q_id = jsonObject.optInt("next_qID", -1);
            String rel_type = jsonObject.getString("rel_type");
            Integer rel_id = jsonObject.optInt("rel_ID", -1);
            Integer qnnaire_id = jsonObject.getInt("questionnaireID");

            logicTables.add(new LogicTable(i+1, sequence_num, q_id, rel_ans_id, next_q_id, rel_type, rel_id, qnnaire_id));
        }

        return logicTables;
    }

    public void addQuestionRelationData(String jsonArray) throws JSONException {
        List<QuestionRelationTable> questionRelationTables = parseQnRelJSONArray(jsonArray);
        executor.execute(() -> db.questionRelationDao().insertAll(questionRelationTables));
    }

    private List<QuestionRelationTable> parseQnRelJSONArray(String jsonArray) throws JSONException {
        List<QuestionRelationTable> questionRelationTables = new ArrayList<>();
        JSONArray relations = new JSONArray(jsonArray);

        for (int i = 0; i < relations.length(); i++) {
            JSONObject relation = relations.getJSONObject(i);

            Integer rel_id = relation.getInt("rel_ID");
            Integer q_id = relation.getInt("question_rel_questionID");
            Integer rel_q_id = relation.getInt("rel_qID");
            Integer rel_ans_id = relation.getInt("rel_ans_ID");
            Integer qnnaire_id = relation.getInt("questionnaireID");

            questionRelationTables.add(new QuestionRelationTable(i+1, rel_id, q_id, rel_q_id, rel_ans_id, qnnaire_id));
        }

        return questionRelationTables;
    }

    public Integer getQuestionnaireID(String questionnaireType) throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.questionnaireDao().getQuestionnaireID(questionnaireType));
        return task.get();
    }

    public List<ResponseTable> getAllResponsesToUpload() throws ExecutionException, InterruptedException {
        Future<List<ResponseTable>> task = executor.submit(() -> db.responseDao().getAllResponsesToUpload());
        return task.get();
    }

    public List<HouseholdTable> getAllHouseholdsToUpload() throws ExecutionException, InterruptedException {
        Future<List<HouseholdTable>> task = executor.submit(() -> db.householdDao().getAllHouseholdsToUpload());
        return task.get();
    }

    public List<PatientTable> getAllPatientsToUpload() throws ExecutionException, InterruptedException {
        Future<List<PatientTable>> task = executor.submit(() -> db.patientDao().getAllPatientsToUpload());
        return task.get();
    }

    public List<PatientAssessmentStatusTable> getAllAssessmentStatus() throws ExecutionException, InterruptedException {
        Future<List<PatientAssessmentStatusTable>> task = executor.submit(() -> db.patientAssessmentStatusDao().getAllAssessmentStatus());
        return task.get();
    }

    public void deleteSingleHousehold(String householdID) {
        executor.execute(() -> db.householdDao().deleteSingleHousehold(householdID));
    }

    public void deleteSingleResponse(String patientID, Integer questionID, Integer answerID,
                                     String answerText, Integer qnnaireID, String date) {
        executor.execute(() -> db.responseDao().deleteSingleResponse(patientID, questionID, answerID, answerText, qnnaireID, date));
    }

    public void deleteSinglePatient(String patientID) {
        executor.execute(() -> db.patientDao().deleteSinglePatient(patientID));
    }

    public void deleteSingleAssessmentStatus(String patientID, int qnnID, String startDate) {
        executor.execute(() -> db.patientAssessmentStatusDao().deleteSingleAssessmentStatus(patientID, qnnID, startDate));
    }


    /* check skip logic */
    /* check NEXT type of logic */
    private boolean satisfyNEXTLogic(int relAnsID, int currQnID, String patientID, int currQnnID) throws ExecutionException, InterruptedException {
        List<Integer> responses = getResponsesForCurrQuestion(patientID, currQnID, currQnnID);
        for (Integer response : responses) {
            if (response == relAnsID) {
                return true;
            }
        }
        return false;
    }

    /* helper function for checking NEXT type of logic */
    private List<Integer> getResponsesForCurrQuestion(String patientID, int currQnID, int currQnnID) throws ExecutionException, InterruptedException {
        Future<List<Integer>> task = executor.submit(() -> db.responseDao().getResponsesForCurrQuestion(patientID, currQnID, currQnnID));
        return task.get();
    }

    /* check AND type of logic */
    private boolean satisfyANDLogic(int relID, int currQnID, int currQnnID, String patientId) throws ExecutionException, InterruptedException {
        int correctCount = getCorrectCount(relID, currQnID, currQnnID);
        List<Integer> relQnsID = getDistinctRelQID(relID, currQnID, currQnnID);
        int count = 0;

        for (int relQnID : relQnsID) {
            count += getResponseCount(patientId, relQnID, currQnnID);
        }

        return count == correctCount;
    }

    /* check OR type of logic */
    private boolean satisfyORLogic(int relID, int currQnID, int currQnnID, String patientId) throws ExecutionException, InterruptedException {
        int correctCount = getCorrectCount(relID, currQnID, currQnnID);
        List<Integer> relQnsID = getDistinctRelQID(relID, currQnID, currQnnID);
        int count = 0;

        for (int relQnID : relQnsID) {
            count += getResponseCount(patientId, relQnID, currQnnID);
        }

        return (count > 0) && (count < correctCount);
    }

    /* helper functions for checking AND and OR type of logic */
    private List<Integer> getDistinctRelQID(int relID, int currQnID, int currQnnID) throws ExecutionException, InterruptedException {
        Future<List<Integer>> task = executor.submit(() -> db.questionRelationDao().getDistinctRelQID(relID, currQnID, currQnnID));
        return task.get();
    }

    private int getCorrectCount(int relID, int currQnID, int currQnnID) throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.questionRelationDao().getCorrectCount(relID, currQnID, currQnnID));
        return task.get();
    }

    private int getResponseCount(String patientID, int checkQuestion, int currQnnID) throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.questionRelationDao().getResponseCount(patientID, checkQuestion, currQnnID));
        return task.get();
    }


    /* methods used to load data for UserCreateFragment */
    public Question loadFirstQuestion(Integer qnnID) throws ExecutionException, InterruptedException {

        Integer firstQnID = getFirstQnID(qnnID);
        List<Answer> answers = getQnAns(firstQnID, qnnID);

        Future<Question> task = executor.submit(() -> {
            QuestionTable questionTable = db.questionDao().getQuestion(firstQnID, qnnID);
            return new Question(questionTable.getQuestion_id(), questionTable.getQuestion_string(),
                    questionTable.getQuestion_instruction(), questionTable.getQ_type_id(),
                    answers, questionTable.getQuestion_media());
        });
        return task.get();
    }

    // helper function to get the first question in a questionnaire
    // getFirstQnID queries in the QuestionTable and returns the questionID for the first question
    private Integer getFirstQnID(Integer qnnID) throws ExecutionException, InterruptedException {
        Future<Integer> task =executor.submit(() -> {
            LogicTable firstQn = db.logicDao().getFirstQn(qnnID);
            return firstQn.getQ_id();
        });
        return task.get();
    }

    public Question loadThisQuestion(int qnID, int qnnID) throws ExecutionException, InterruptedException {
        List<Answer> answers = getQnAns(qnID, qnnID);

        Future<Question> task = executor.submit(() -> {
            QuestionTable questionTable = db.questionDao().getQuestion(qnID, qnnID);
            return new Question(questionTable.getQuestion_id(), questionTable.getQuestion_string(),
                    questionTable.getQuestion_instruction(), questionTable.getQ_type_id(), answers,
                    questionTable.getQuestion_media());
        });
        return task.get();
    }


    public Question loadNextQuestion(String patientID, int currQnID, int currQnnID) throws ExecutionException, InterruptedException {
        Integer nextQnID = getNextQnID(patientID, currQnID, currQnnID);
        List<Answer> answers = getQnAns(nextQnID, currQnnID);

        Future<Question> task = executor.submit(() -> {
           QuestionTable questionTable = db.questionDao().getQuestion(nextQnID, currQnnID);
            return new Question(questionTable.getQuestion_id(), questionTable.getQuestion_string(),
                                questionTable.getQuestion_instruction(), questionTable.getQ_type_id(),
                                answers, questionTable.getQuestion_media());
        });
        return task.get();
    }

    public Integer getNextQnID(String patientID, int currQnID, int currQnnID) throws ExecutionException, InterruptedException {
        List<LogicTable> logicObjects = getLogicObjects(currQnID, currQnnID);
        int resultSeqNum = 0;
        int nextQnID = 0;

        for (LogicTable logic : logicObjects) {
            switch (logic.getRel_type()) {
                case ("NEXT"):
                    if (satisfyNEXTLogic(logic.getRel_ans_id(), currQnID, patientID, currQnnID)) {
                        if ((logic.getSequence_num() < resultSeqNum) || (resultSeqNum == 0)) {
                            nextQnID = logic.getNext_q_id();
                            resultSeqNum = logic.getSequence_num();
                        }
                    }
                    break;

                case ("AND"):
                    if (satisfyANDLogic(logic.getRel_id(), currQnID, currQnnID, patientID)) {
                        if ((logic.getSequence_num() < resultSeqNum) || (resultSeqNum == 0)) {
                            nextQnID = logic.getNext_q_id();
                            resultSeqNum = logic.getSequence_num();
                        }
                    }
                    break;

                case ("OR"):
                    if (satisfyORLogic(logic.getRel_id(), currQnID, currQnnID, patientID)) {
                        if ((logic.getSequence_num() < resultSeqNum) || (resultSeqNum == 0)) {
                            nextQnID = logic.getNext_q_id();
                            resultSeqNum = logic.getSequence_num();
                        }
                    }
                    break;

                default:    // no skip logic
                    if ((logic.getSequence_num() < resultSeqNum) || (resultSeqNum == 0)) {
                        nextQnID = logic.getNext_q_id();
                        resultSeqNum = logic.getSequence_num();
                    }
                    break;
            }
        }
        return nextQnID;
    }

    private List<LogicTable> getLogicObjects(int currQnID, int currQnnID) throws ExecutionException, InterruptedException {
        Future<List<LogicTable>> task = executor.submit(() -> db.logicDao().getLogicObjects(currQnID, currQnnID));
        return task.get();
    }

    private List<Answer> getQnAns(Integer qnID, Integer currQnnID) throws ExecutionException, InterruptedException {
        List<Answer> result = new ArrayList<>();
        List<Integer> ansIDs = getAllAnsID(qnID, currQnnID);

        for (Integer ansID : ansIDs) {
            result.add(getSingleAns(ansID, currQnnID));
        }
        return result;
    }

    // helper function to get all answer choices for a question
    // getAllAnsID queries in the QuestionAnswerTable and returns the ID for all the answer choices for a question
    private List<Integer> getAllAnsID(Integer qnID, Integer qnnID) throws ExecutionException, InterruptedException {
        Future<List<Integer>> task = executor.submit(() -> db.questionAnswerDao().getAllAnsID(qnID, qnnID));
        return task.get();
    }

    // helper function to get all answer choices for a question
    // getSingleAns queries in the AnswerTable and creates an Answer object of a given answer ID
    private Answer getSingleAns(Integer ansID, Integer qnnID) throws ExecutionException, InterruptedException {
        Future<Answer> task = executor.submit(() -> {
            AnswerTable answerTable = db.answerDao().getAnswer(ansID, qnnID);
            return new Answer(answerTable.getAnswer_id(), answerTable.getAnswer_string());
        });

        return task.get();
    }

    public void storeSingleResponseToDb(Response response) throws ExecutionException, InterruptedException {
        ResponseTable responseTable = responseToResponseTableConverter(response);
        executor.execute(() -> db.responseDao().insert(responseTable));
    }

    public void storeHouseholdToDb(HouseholdTable household) {
        executor.execute(() -> db.householdDao().insert(household));
    }

    public void storePatientToDb(PatientTable patientTable) {
        executor.execute(() -> db.patientDao().insert(patientTable));
    }

    private int getResponseTableLastIndex() throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.responseDao().getLastIndex());
        return task.get();
    }

    public int getHouseholdTableLastIndex() throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.householdDao().countAllHouseholds());
        return task.get();
    }

    public int getPatientTableLastIndex() throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.patientDao().countAllPatients());
        return task.get();
    }

    private ResponseTable responseToResponseTableConverter(Response response) throws ExecutionException, InterruptedException {
        int lastIndex = getResponseTableLastIndex();
        return new ResponseTable(lastIndex + 1, response.getPatientID(), response.getQnID(),
                response.getAnsID(), response.getAnsText(), response.getQnnID(), response.getDate().toString(), 1);
    }


    /* Household Choose Fragment */
    public void addHouseholdData(String jsonArray) throws JSONException {
        List<HouseholdTable> householdTables = parseHouseholdJSONArray(jsonArray);
        executor.execute(() -> db.householdDao().insertAll(householdTables));
    }

    private List<HouseholdTable> parseHouseholdJSONArray(String jsonArray) throws JSONException {
        List<HouseholdTable> householdTables = new ArrayList<>();
        JSONArray households = new JSONArray(jsonArray);

        for (int i = 0; i < households.length(); i++) {
            JSONObject household = households.getJSONObject(i);

            String household_id = household.getString("householdID");
            Integer parent_loc_id = household.getInt("parentLocID");
            String enum_id = household.getString("enumeratorID");
            String date = household.getString("date");
            String gps_latitude = household.getString("gps_latitude");
            String gps_longitude = household.getString("gps_longitude");

            HouseholdTable householdTable = new HouseholdTable(household_id, parent_loc_id, enum_id, date, gps_latitude, gps_longitude, 0);
            householdTable.setVillage_street_name(household.getString("village_street_name"));
            householdTable.setAvailability(household.getString("availability"));
            householdTable.setReason_refusal(household.optString("reason_refusal", ""));
            householdTable.setVisit_num(household.getInt("visit_num"));
            householdTable.setKey_informer(household.getString("key_informer"));
            householdTable.setTel1_num(household.optString("tel1_num", ""));
            householdTable.setTel1_owner(household.optString("tel1_owner", ""));
            householdTable.setTel2_num(household.optString("tel2_num", ""));
            householdTable.setTel2_owner(household.optString("tel2_owner", ""));
            householdTable.setConsent(household.getString("consent"));
            householdTable.setA2_q1(household.getString("a2q1"));
            householdTable.setA2_q2(household.getString("a2q2"));
            householdTable.setA2_q3(household.getString("a2q3"));
            householdTable.setA2_q4(household.getString("a2q4"));
            householdTable.setA2_q5(household.getString("a2q5"));
            householdTable.setA2_q6(household.getString("a2q6"));
            householdTable.setA2_q7(household.getString("a2q7"));
            householdTable.setA2_q8(household.getString("a2q8"));
            householdTable.setA2_q9(household.getString("a2q9"));
            householdTable.setA2_q10(household.getString("a2q10"));
            householdTable.setA2_q11(household.getString("a2q11"));
            householdTable.setA2_q12(household.getString("a2q12"));
            householdTable.setA2_q13(household.getString("a2q13"));

            householdTables.add(householdTable);
        }

        return householdTables;
    }

    public void addPatientData(String jsonArray) throws JSONException {
        List<PatientTable> patientTables = parsePatientJSONArray(jsonArray);
        executor.execute(() -> db.patientDao().insertAll(patientTables));
    }

    private List<PatientTable> parsePatientJSONArray(String jsonArray) throws JSONException {
        List<PatientTable> patientTables = new ArrayList<>();
        JSONArray patients = new JSONArray(jsonArray);

        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);

            String patient_id = patient.getString("patientID");
            String study_id = patient.getString("studyID");
            String hh_id = patient.getString("householdID");

            PatientTable patientTable = new PatientTable(patient_id, study_id, hh_id, 0);

            patientTable.setDate_of_birth(patient.getString("date_of_birth"));
            patientTable.setPrefix(patient.optString("prefix", ""));
            patientTable.setFirst_name(patient.getString("firstName"));
            patientTable.setMiddle_name(patient.optString("middleName", ""));
            patientTable.setLast_name(patient.getString("lastName"));
            patientTable.setSuffix(patient.optString("suffix", ""));
            patientTable.setCom_name(patient.optString("com_name", ""));
            patientTable.setGender(patient.getString("gender"));
            patientTable.setDur_hh(patient.getString("dur_hh"));
            patientTable.setNotes(patient.optString("notes", ""));
            patientTable.setLvl_edu(patient.getString("lvl_edu"));
            patientTable.setWork_status(patient.getString("work_status"));
            patientTable.setMarital_status(patient.getString("marital_status"));
            patientTable.setMother_first(patient.optString("motherFirstName"));
            patientTable.setMother_last(patient.optString("motherLastName", ""));
            patientTable.setTel1_num(patient.optString("tel1_num", ""));
            patientTable.setTel1_owner(patient.optString("tel1_owner", ""));
            patientTable.setTel1_owner_rel(patient.optString("tel1_owner_rel", ""));
            patientTable.setTel2_num(patient.optString("tel2_num", ""));
            patientTable.setTel2_owner(patient.optString("tel2_owner", ""));
            patientTable.setTel2_owner_rel(patient.optString("tel2_owner_rel", ""));
            patientTable.setEnum_id(patient.getString("enumeratorID"));
            patientTable.setNational_id(patient.optString("nationalID", ""));
            patientTable.setDeceased(patient.optInt("deceased", 0));
            patientTable.setDeceased_date(patient.optString("deceased_date", ""));
            patientTable.setResponder(patient.getString("responder"));
            patientTable.setProxy_name(patient.optString("proxy_name", ""));
            patientTable.setProxy_rel(patient.optString("proxy_rel", ""));

            patientTables.add(patientTable);
        }

        return patientTables;
    }

    public void addPatientAssessmentData(String jsonArray) throws JSONException {
        List<PatientAssessmentStatusTable> patientAssessmentStatusTables = parsePatientAssessmentJSONArray(jsonArray);
        executor.execute(() -> db.patientAssessmentStatusDao().insertAll(patientAssessmentStatusTables));
    }

    private List<PatientAssessmentStatusTable> parsePatientAssessmentJSONArray(String jsonArray) throws JSONException {
        List<PatientAssessmentStatusTable> patientAssessmentStatusTables = new ArrayList<>();
        JSONArray assessmentList = new JSONArray(jsonArray);

        for (int i = 0; i < assessmentList.length(); i++) {
            JSONObject assessment = assessmentList.getJSONObject(i);

            Integer index = assessment.getInt("index");
            String patient_id = assessment.getString("assess_patientID");
            Integer qnnaire_id = assessment.getInt("assess_questionnaireID");
            String qnnaire_status = assessment.getString("questionnaireStatus");
            String start = assessment.getString("start");
            String end = assessment.optString("end", "");
            Integer lastAnsweredQnID = assessment.optInt("last_answered_qn", -1);

            patientAssessmentStatusTables.add(new PatientAssessmentStatusTable(index, patient_id, qnnaire_id, qnnaire_status, start, end, lastAnsweredQnID));
        }

        return patientAssessmentStatusTables;
    }

    public void addResponseData(String jsonArray) throws JSONException {
        List<ResponseTable> responseTables = parseResponseJSONArray(jsonArray);
        executor.execute(() -> db.responseDao().insertAll(responseTables));
    }

    private List<ResponseTable> parseResponseJSONArray(String jsonArray) throws JSONException {
        List<ResponseTable> responseTables = new ArrayList<>();
        JSONArray responses = new JSONArray(jsonArray);

        for (int i = 0; i < responses.length(); i++) {
            JSONObject response = responses.getJSONObject(i);

            Integer index = response.getInt("index");
            String patientID = response.getString("patientID");
            Integer questionID = response.getInt("questionID");
            Integer answerID = response.optInt("answerID", -1);
            String answerText = response.optString("text", "");
            Integer questionnaireID = response.getInt("questionnaireID");
            String date = response.getString("date");

            responseTables.add(new ResponseTable(index, patientID, questionID, answerID, answerText, questionnaireID, date, 0));
        }

        return responseTables;
    }

    public List<HouseholdTable> loadHouseholdsForCluster(int clusterID) throws ExecutionException, InterruptedException {
        Future<List<HouseholdTable>> task = executor.submit(() -> db.householdDao().getHouseholdsForCluster(clusterID));
        return task.get();
    }

    public List<PatientTable> loadAllPatients() throws ExecutionException, InterruptedException {
        Future<List<PatientTable>> task = executor.submit(() -> db.patientDao().getAllPatients());
        return task.get();
    }

    public List<PatientTable> loadPatientsForHousehold(String householdID) throws ExecutionException, InterruptedException {
        Future<List<PatientTable>> task = executor.submit(() -> db.patientDao().getPatientsForHousehold(householdID));
        return task.get();
    }

    public HouseholdTable getCurrentHousehold(String householdID) throws ExecutionException, InterruptedException {
        Future<HouseholdTable> task = executor.submit(() -> db.householdDao().getHouseholdForPatient(householdID));
        return task.get();
    }

    public PatientTable getCurrentPatient(String patientID) throws ExecutionException, InterruptedException {
        Future<PatientTable> task = executor.submit(() -> db.patientDao().getSinglePatient(patientID));
        return task.get();
    }

    public List<PatientAssessmentStatusTable> loadAssessmentStatusForPatient(String patientID) throws ExecutionException, InterruptedException {
        Future<List<PatientAssessmentStatusTable>> task = executor.submit(() -> db.patientAssessmentStatusDao().getAssessmentStatusForPatient(patientID));
        return task.get();
    }

    public QuestionnaireTable getQuestionnaireInfo(int questionnaireID) throws ExecutionException, InterruptedException {
        Future<QuestionnaireTable> task = executor.submit(() -> db.questionnaireDao().getSingleQuestionnaireInfo(questionnaireID));
        return task.get();
    }

    public int getAssessmentStatusTableLastIndex() throws ExecutionException, InterruptedException {
        Future<Integer> task = executor.submit(() -> db.patientAssessmentStatusDao().getLastIndex());
        return task.get();
    }

    public void addNewAssessmentStatus(PatientAssessmentStatusTable assessmentStatusTable) {
        executor.execute(() -> db.patientAssessmentStatusDao().insert(assessmentStatusTable));
    }

    public PatientAssessmentStatusTable findExistingAssessment(String patientID, int questionnaireID, String startDate) throws ExecutionException, InterruptedException {
        Future<PatientAssessmentStatusTable> task = executor.submit(() -> db.patientAssessmentStatusDao().findExistingAssessment(patientID, questionnaireID, startDate));
        return task.get();
    }

    public void updateExistingAssessmentStatusTable(PatientAssessmentStatusTable assessmentStatusTable) {
        // use patientAssessmentStatusDao().insert(PatientAssessmentStatusTable table) because the onConflictStrategy is REPLACE
        // therefore, the old entry will be replaced by the newly inserted (updated) row
        executor.execute(() -> db.patientAssessmentStatusDao().insert(assessmentStatusTable));
    }
}
