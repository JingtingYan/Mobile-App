package com.example.mobileApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static MobileAppDatabase mobileAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


//      To start up AppSpector Connection
//        AppSpector
//                .build(this)
//                .withDefaultMonitors()
//                .run("android_Njc0ZGExMjUtMmFhMy00ZTg2LTkxN2YtNGVhMDVmZDM3OWZh");

    }

}
