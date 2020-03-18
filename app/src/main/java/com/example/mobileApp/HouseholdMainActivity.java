package com.example.mobileApp;

import android.os.Bundle;

import com.example.mobileApp.utilities.Constants;

import androidx.fragment.app.FragmentManager;

import android.widget.TextView;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * The HouseholdMainActivity class initialises layout components for views in activity_household_main.xml.
 * Functions:
 *  1. It servers as a template and container for the set of Household Fragments.
 *      - Contain the project header which includes the location information selected from Location selection page.
 *        (the layout for project header is defined in /res/layout/layout_project_header.xml)
 *      - Instantiate a FragmentManager object which manages transactions between Household Fragments.
 *  2. It also extends NavigationDrawerActivity class to support customised Toolbar and navigation drawer.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class HouseholdMainActivity extends NavigationDrawerActivity {

    /* class-scope and package-scope variables */
    @BindViews({R.id.txt_hh_home_country, R.id.txt_hh_home_region, R.id.txt_hh_home_cluster})
    List<TextView> projectLocation;

    public static FragmentManager fragmentManager;  // fragmentManager is package-scope

    /**
     * This method is called when the HouseholdMain Activity is first created.
     * It creates the activity according to its layout defined in /res/layout/activity_household_main.xml.
     * It instantiates and initialises some class-scope variables (bind by ButterKnife).
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     *                           This object would be null if the activity has never existed before.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the HouseholdMainActivity layout into NavigationDrawerActivity's FrameLayout placeholder.
        getLayoutInflater().inflate(R.layout.activity_household_main, frameLayout);
        // Set title for this Activity's toolbar
        HouseholdMainActivity.this.setTitle(R.string.title_activity_household_home);

        // Call ButterKnife to automatically cast and bind the view ID with variables.
        ButterKnife.bind(this);

        // Return the FragmentManager object for interacting with fragments associated with this activity.
        fragmentManager = getSupportFragmentManager();

        setProjectLocation();

        // initialise the fragment transaction
        if (findViewById(R.id.household_fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.household_fragment_container, new HouseholdHomeFragment())
                    .addToBackStack(null).commit();
        }

    }

    /**
     * This method is called to load selected location information in the project header.
     */
    private void setProjectLocation() {
        projectLocation.get(0).setText(Constants.getCountry().getLocationName());
        projectLocation.get(1).setText(Constants.getRegion().getLocationName());
        projectLocation.get(2).setText(Constants.getCluster().getLocationName());
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }
}
