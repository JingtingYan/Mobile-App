package com.example.mobileApp.database;

import android.content.Context;

import com.example.mobileApp.database.entity.AnswerTable;
import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.database.entity.LogicTable;
import com.example.mobileApp.database.entity.QuestionAnswerTable;
import com.example.mobileApp.database.entity.QuestionRelationTable;
import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.database.entity.QuestionnaireTable;
import com.example.mobileApp.datatype.Location;

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

    private static MobileAppRepository ourInstance;

    private MobileAppDatabase db;
    private ExecutorService executor = Executors.newFixedThreadPool(1);


    public static MobileAppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MobileAppRepository(context);
        }
        return ourInstance;
    }

    private MobileAppRepository(Context context) {
        db = MobileAppDatabase.getDatabase(context);
    }


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

            questionnaireTables.add(new QuestionnaireTable(questionnaire_id, questionnaire_name, questionnaire_version));
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
            //Integer rel_ans_id = jsonObject.getInt("rel_ans_ID");
            Integer next_q_id = jsonObject.getInt("next_qID");
            String rel_type = jsonObject.getString("rel_type");
            //Integer rel_id = jsonObject.getInt("rel_ID");
            Integer qnnaire_id = jsonObject.getInt("questionnaireID");

            /* this needs to be fixed later */
            Integer rel_ans_id = 0, rel_id = 0;

            logicTables.add(new LogicTable(sequence_num, q_id, rel_ans_id, next_q_id, rel_type,
                                rel_id, qnnaire_id));
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

            questionRelationTables.add(new QuestionRelationTable(i, rel_id, q_id, rel_q_id,
                                                                 rel_ans_id, qnnaire_id));
        }

        return questionRelationTables;
    }
}
