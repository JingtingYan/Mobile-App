package com.example.mobileApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.Bundle;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private LocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){

            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new LocationFragment()).commit();
        }

        System.out.println("\nSO FAR SO GOOD\n");
        // Reference: https://stackoverflow.com/questions/53903762/viewmodelproviders-is-deprecated-in-1-1-0
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

    }



}
