package com.example.mobileApp;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {

    private Repository repo;
    private LiveData<List<Location>> countries;
    private MutableLiveData<List<Location>> regions;
    private MutableLiveData<List<Location>> clusters;

    /*
    public MutableLiveData<List<Location>> getCountries() {
        if (countries == null) {
            countries = new MutableLiveData<List<Location>>();
            loadCountries();
        }
        return countries;
    }
    */


    public LocationViewModel(@NonNull Application application)
    {
        super(application);
        repo = new Repository(application);
        countries= repo.getCountries();
    }

    LiveData<List<Location>> getCountries() {
        return countries;
    }


}
