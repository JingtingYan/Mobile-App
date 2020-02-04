package com.example.mobileApp;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// Reference : https://github.com/ashishrawat2911/MVVM-Demo/blob/master/app/src/main/java/com/aeologic/moviemvvmdemo/viewmodel/MovieViewModel.java

public class LocationViewModel extends AndroidViewModel
{
    private Repository repo;
    private LiveData<List<Location>> countries;

    public LocationViewModel(@NonNull Application application)
    {
        super(application);
        repo = new Repository(application);
        System.out.println("\nREPO MADE\n");
//        countries = repo.getCountries();
    }

    public LiveData<List<Location>> getCountries()
    {
        return this.countries;
    }
}
