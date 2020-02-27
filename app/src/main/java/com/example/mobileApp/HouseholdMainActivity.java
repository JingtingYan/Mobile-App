package com.example.mobileApp;

import android.os.Bundle;

import com.example.mobileApp.utilities.Constants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class HouseholdMainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_household_home) Toolbar toolbar;
    @BindViews({R.id.txt_hh_home_country, R.id.txt_hh_home_region, R.id.txt_hh_home_cluster})
    List<TextView> projectLocation;

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        setProjectLocation();

        if (findViewById(R.id.household_fragment_container) != null){

            if (savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.household_fragment_container, new HouseholdHomeFragment()).commit();
        }

    }

    private void setProjectLocation() {
        projectLocation.get(0).setText(Constants.getCountry());
        projectLocation.get(1).setText(Constants.getRegion());
        projectLocation.get(2).setText(Constants.getCluster());
    }
}
