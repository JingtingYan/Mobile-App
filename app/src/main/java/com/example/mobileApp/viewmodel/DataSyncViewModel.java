package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;

import org.json.JSONException;

public class DataSyncViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    public DataSyncViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
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
