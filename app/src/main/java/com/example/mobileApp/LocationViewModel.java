package com.example.mobileApp;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel
{
    private Repository repo;
    private LiveData<List<Location>> countries;

    public LocationViewModel(@NonNull Application application)
    {
        super(application);
        repo = new Repository(application);
    }
}
