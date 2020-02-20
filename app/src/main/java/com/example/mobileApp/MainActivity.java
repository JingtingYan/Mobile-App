package com.example.mobileApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);    // debugging
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){

            if (savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
        }



//      To start up AppSpector Connection
//        AppSpector
//                .build(this)
//                .withDefaultMonitors()
//                .run("android_Njc0ZGExMjUtMmFhMy00ZTg2LTkxN2YtNGVhMDVmZDM3OWZh");

    }

}
