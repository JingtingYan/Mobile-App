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

    public void addLocationData(String jsonArray) {
        try {
            repo.addLocationData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
