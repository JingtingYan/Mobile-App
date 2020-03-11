package com.example.mobileApp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.ResponseTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DataSyncViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    public DataSyncViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public JSONObject getResponseJSONObject() {
        JSONObject result = new JSONObject();
        List<JSONObject> jsonArray = new ArrayList<>();
        List<ResponseTable> responseTables = new ArrayList<>();

        try {
            responseTables.addAll(repo.getResponses());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        for (ResponseTable responseTable : responseTables) {
            try {
                jsonArray.add(responseTableConverter(responseTable));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            result.put("data", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    // helper function to parse a ResponseTable object to JSON object format
    private JSONObject responseTableConverter(ResponseTable responseTable) throws JSONException {
        JSONObject result = new JSONObject();

        result.put("index", responseTable.getIndex());
        result.put("patientID", responseTable.getPatient_id());
        result.put("questionID", responseTable.getQ_id());
        result.put("answerID", responseTable.getAns_id());
        result.put("text", responseTable.getText());
        result.put("questionnaireID", responseTable.getQnnaire_id());
        result.put("date", responseTable.getDate());

        return result;
    }

    public JSONArray getHouseholdJSONArray() {
        JSONArray result = new JSONArray();
        List<HouseholdTable> householdTables = new ArrayList<>();

        try {
            householdTables.addAll(repo.getAllHouseholds());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        for (HouseholdTable householdTable : householdTables) {
            try {
                result.put(householdTableConverter(householdTable));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private JSONObject householdTableConverter(HouseholdTable householdTable) throws JSONException {
        JSONObject result = new JSONObject();

        result.put("householdID", householdTable.getHousehold_id());
        result.put("parentLocID", householdTable.getParent_loc_id());
        result.put("enumeratorID", "1");    // hard-code enumeratorID to be 1 - need to be changed later
        result.put("date", householdTable.getDate());
        result.put("village_street_name", householdTable.getVillage_street_name());
        result.put("gps_latitude", householdTable.getGps_latitude());
        result.put("gps_longitude", householdTable.getGps_longitude());
        result.put("availability", householdTable.getAvailability());
        result.put("reason_refusal", householdTable.getReason_refusal());
        result.put("visit_num", householdTable.getVisit_num());
        result.put("key_informer", householdTable.getKey_informer());
        result.put("tel1_num", householdTable.getTel1_num());
        result.put("tel1_owner", householdTable.getTel1_owner());
        result.put("tel2_num", householdTable.getTel2_num());
        result.put("tel2_owner", householdTable.getTel2_owner());
        result.put("consent", householdTable.getConsent());
        result.put("a2q1", householdTable.getA2_q1());
        result.put("a2q2", householdTable.getA2_q2());
        result.put("a2q3", householdTable.getA2_q3());
        result.put("a2q4", householdTable.getA2_q4());
        result.put("a2q5", householdTable.getA2_q5());
        result.put("a2q6", householdTable.getA2_q6());
        result.put("a2q7", householdTable.getA2_q7());
        result.put("a2q8", householdTable.getA2_q8());
        result.put("a2q9", householdTable.getA2_q9());
        result.put("a2q10", householdTable.getA2_q10());
        result.put("a2q11", householdTable.getA2_q11());
        result.put("a2q12", householdTable.getA2_q12());
        result.put("a2q13", householdTable.getA2_q13());

        Log.i("parentLocID is", result.get("parentLocID").toString());
        Log.i("parentLocID type is", result.get("parentLocID").getClass().toString());

        return result;
    }

    public void deleteConfirmedHouseholdData(JSONArray response) throws JSONException {
        for (int i = 0; i < response.length(); i++) {
            JSONObject household = response.getJSONObject(i);
            String householdID = household.getString("householdID");
            repo.deleteSingleHousehold(householdID);
        }
    }

    public void deleteLocationData() {
        repo.deleteLocationData();
    }

    public void deleteQuestionnaireData() {
        repo.deleteQuestionnaireData();
    }

    public void deleteQuestionData() {
        repo.deleteQuestionData();
    }

    public void deleteAnswerData() {
        repo.deleteAnswerData();
    }

    public void deleteQAData() {
        repo.deleteQAData();
    }

    public void deleteLogicData() {
        repo.deleteLogicData();
    }

    public void deleteQuestionRelationData() {
        repo.deleteQuestionRelationData();
    }

    public void deleteResponseData() {
        repo.deleteResponseData();
    }

    public void deleteHouseholdData() {
        repo.deleteHouseholdData();
    }

    public void addLocationData(String jsonArray) {
        try {
            repo.addLocationData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addQuestionnaireData(String jsonArray) {
        try {
            repo.addQuestionnaireData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addQuestionData(String jsonArray) {
        try {
            repo.addQuestionData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addAnswerData(String jsonArray) {
        try {
            repo.addAnswerData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addQAData(String jsonArray) {
        try {
            repo.addQAData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addLogicData(String jsonArray) {
        try {
            repo.addLogicData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addQuestionRelationData(String jsonArray) {
        try {
            repo.addQuestionRelationData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
