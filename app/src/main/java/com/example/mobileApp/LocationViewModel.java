package com.example.mobileApp;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {

    private  Repository repo;
    private MutableLiveData<List<Location>> spinnerCountries;
    private MutableLiveData<List<Location>> spinnerRegions;
    private MutableLiveData<List<Location>> spinnerClusters;
    private static int countryID;
    private static int regionID;


    public LocationViewModel(Application application)
    {
        super(application);
        repo = new Repository(application);
        spinnerCountries = repo.getSpinnerCountries();
        spinnerRegions = repo.getSpinnerRegions(countryID);
        spinnerClusters = repo.getSpinnerClusters(regionID);
    }

    /*
    // db pre-populate?
    public void insert(LocationTable location) {
        repo.insert(location);
    }
     */


    /* getter and setter */

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public MutableLiveData<List<Location>> getSpinnerCountries() {
        if (spinnerCountries == null) {
            spinnerCountries = new MutableLiveData<>();
        }
        return spinnerCountries;
    }

    public void setSpinnerCountries(MutableLiveData<List<Location>> spinnerCountries) {
        this.spinnerCountries = spinnerCountries;
    }

    public MutableLiveData<List<Location>> getSpinnerRegions() {
        if (spinnerRegions == null) {
            spinnerRegions = new MutableLiveData<>();
        }
        return spinnerRegions;
    }

    public void setSpinnerRegions(MutableLiveData<List<Location>> spinnerRegions) {
        this.spinnerRegions = spinnerRegions;
    }

    public MutableLiveData<List<Location>> getSpinnerClusters() {
        if (spinnerClusters == null) {
            spinnerClusters = new MutableLiveData<>();
        }
        return spinnerClusters;
    }

    public void setSpinnerClusters(MutableLiveData<List<Location>> spinnerClusters) {
        this.spinnerClusters = spinnerClusters;
    }

    LiveData<List<Location>> getCountries() {
        return countries;
    }


}
