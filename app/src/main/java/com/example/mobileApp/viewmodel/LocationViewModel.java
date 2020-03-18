package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.datatype.Location;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LocationViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    private List<Location> spinnerCountries = new ArrayList<>();
    public MutableLiveData<List<Location>> spinnerRegions = new MutableLiveData<>();
    public MutableLiveData<List<Location>> spinnerClusters = new MutableLiveData<>();

    private static final String SELECT_COUNTRY_PROMPT = "Select a country...";
    private static final String SELECT_REGION_PROMPT = "Select a region...";
    private static final String SELECT_CLUSTER_PROMPT = "Select a cluster...";

    public LocationViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public List<Location> getSpinnerCountries() {
        return spinnerCountries;
    }

    public List<Location> loadCountrySpinner() {
        // create a new Location object for Country spinner hint with meaningless locationID '-2'
        spinnerCountries.add(new Location(-2, SELECT_COUNTRY_PROMPT));
        try {
            spinnerCountries.addAll(repo.getSpinnerCountries());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return spinnerCountries;
    }

    public void loadRegionSpinner(Integer countryID) {
        List<Location> regions = new ArrayList<>();
        // create a new Location object for Region spinner hint with meaningless locationID '-2'
        regions.add(new Location(-2, SELECT_REGION_PROMPT));

        try {
            List<Location> temp = repo.getSpinnerRegions(countryID);
            regions.addAll(temp);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        spinnerRegions.postValue(regions);
    }

    public void loadClusterSpinner(Integer regionID) {
        List<Location> clusters = new ArrayList<>();
        // create a new Location object for Cluster spinner hint with meaningless locationID '-2'
        clusters.add(new Location(-2, SELECT_CLUSTER_PROMPT));

        try {
            List<Location> temp = repo.getSpinnerClusters(regionID);
            clusters.addAll(temp);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        spinnerClusters.postValue(clusters);
    }

    public void addHouseholdData(String jsonArray) {
        try {
            repo.addHouseholdData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addPatientData(String jsonArray) {
        try {
            repo.addPatientData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addPatientAssessmentData(String jsonArray) {
        try {
            repo.addPatientAssessmentData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addResponseData(String jsonArray) {
        try {
            repo.addResponseData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
