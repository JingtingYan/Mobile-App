package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.ResponseTable;

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
