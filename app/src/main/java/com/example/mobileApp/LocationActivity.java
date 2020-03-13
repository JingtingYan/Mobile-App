package com.example.mobileApp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mobileApp.datatype.Location;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.utilities.MySingleton;
import com.example.mobileApp.viewmodel.LocationViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static com.example.mobileApp.utilities.Constants.GET_HOUSEHOLD_FOR_CLUSTER_URL;
import static com.example.mobileApp.utilities.Constants.GET_PATIENT_FOR_CLUSTER_URL;

/**
 * The LocationActivity class initialises layout components and adds functions for views in activity_location.xml.
 * It follows the recommended Android Architecture Components: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: LocationActivity, /viewmodel/LocationViewModel, /database/MobileAppRepository, and database package.
 * Functions:
 *  1. It supports location selection for a survey. The selected location information will be stored in
 *     /utilities/Constants and will be referenced to in later activities.
 *  2. It also extends NavigationDrawerActivity class to support customised Toolbar and navigation drawer.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class LocationActivity extends OverflowMenuActivity {

    /* class-scope variables */
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.spinner_select_country) Spinner countrySpinner;
    @BindView(R.id.spinner_select_region) Spinner regionSpinner;
    @BindView(R.id.spinner_select_cluster) Spinner clusterSpinner;
    @BindView(R.id.bn_location_next) Button bnNext;
    @BindView(R.id.bn_location_download) Button bnDownload;

    private LocationViewModel locationViewModel;
    private static Location country, region, cluster;

    /**
     * This method is called when the Location Activity is first created.
     * It creates the activity according to its layout defined in /res/layout/activity_location.xml.
     * It instantiates and initialises some class-scope variables (bind by ButterKnife) and
     * associates the activity with a viewmodel instance.
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     *                           This object would be null if the activity has never existed before.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Call ButterKnife to automatically cast and bind the view ID with variables.
        ButterKnife.bind(this);

        // Set title for this Activity's toolbar.
        LocationActivity.this.setTitle(R.string.title_activity_location);


        // Set a Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);

        // Add the parent activity (the MainActivity) to DataSyncActivity.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // initialise the selected location values to be empty
        country = null;
        region = null;
        cluster = null;

        initViewModel();

        // When the Activity is created, only load the contents in Country Spinner.
        // Leave the Region spinner and Cluster spinner blank.
        initCountrySpinner();
    }

    /**
     * This method is called to initialise the locationViewModel instance, associate LocationActivity
     * with LocationViewModel, and register observer on views that are bind with MutableLiveData objects
     * defined in LocationViewModel.
     */
    private void initViewModel() {

        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        // regionSpinner (a Spinner object) observes spinnerRegions (a MutableLiveData<List<Location>> object
        // defined in LocationViewModel). Whenever the values stored in spinnerRegions change, the method
        // 'updateRegionSpinner' will be called to update regionSpinner values accordingly.
        locationViewModel.spinnerRegions.observe(this, this::updateRegionSpinner);

        // clusterSpinner (a Spinner object) observes spinnerClusters (a MutableLiveData<List<Location>> object
        // defined in LocationViewModel). Whenever the values stored in spinnerClusters change, the method
        // 'updateClusterSpinner' will be called to update clusterSpinner values accordingly.
        locationViewModel.spinnerClusters.observe(this, this::updateClusterSpinner);
    }

    /**
     * This method is called to initialise the contents in countrySpinner (a Spinner object).
     * The the spinner choices are provided through an ArrayAdapter.
     */
    private void initCountrySpinner() {
        // Create an ArrayAdapter of Location data type. This adapter uses a default spinner layout,
        // and the spinner choices come from the return value of method 'loadCountrySpinner' (defined
        // in LocationViewModel).
        ArrayAdapter<Location> countryAdapter = new ArrayAdapter<Location>(
                this, android.R.layout.simple_spinner_item, locationViewModel.loadCountrySpinner()) {

            @Override
            public boolean isEnabled(int position){
                // Disable the clickable of the first item (i.e. the hint) from Spinner
                return position != 0;
            }

            // This override method customises the render of the spinner
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    textView.setTextColor(Color.GRAY);    // Set the text color of hint to be gray
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // Specify the layout to use when the list of choices appears
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        countrySpinner.setAdapter(countryAdapter);
    }

    /**
     * This method is called to update the contents in regionSpinner (a Spinner object).
     * The the spinner choices are provided through an ArrayAdapter.
     * @param locations The source for regionSpinner choices. This will be passed into the ArrayAdapter.
     */
    private void updateRegionSpinner(List<Location> locations) {
        // Similar with the ArrayAdapter set up for countrySpinner.

        ArrayAdapter<Location> regionAdapter = new ArrayAdapter<Location>(
                this, android.R.layout.simple_spinner_item, locations) {

            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);
    }

    /**
     * This method is called to update the contents in clusterSpinner (a Spinner object).
     * The the spinner choices are provided through an ArrayAdapter.
     * @param locations The source for clusterSpinner choices. This will be passed into the ArrayAdapter.
     */
    private void updateClusterSpinner(List<Location> locations) {
        // Similar with the ArrayAdapter set up for countrySpinner.

        ArrayAdapter<Location> clusterAdapter = new ArrayAdapter<Location>(
                this, android.R.layout.simple_spinner_item, locations) {

            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        clusterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clusterSpinner.setAdapter(clusterAdapter);
    }

    /**
     * This method is called when the user selects an item from the countrySpinner drop-down.
     * When a country is selected, that country's regions will be displayed in the regionSpinner.
     * The backend logic is handled by the method 'loadRegionSpinner' (defined in LocationViewModel).
     * @param spinner Reference to the countrySpinner object.
     * @param position The position of the view in the adapter that was clicked.
     */
    @OnItemSelected(R.id.spinner_select_country) void onCountrySelected(Spinner spinner, int position) {
        // call getItemAtPosition(position) to access the data associated with the selected item
        Location selectedCountry = (Location) spinner.getItemAtPosition(position);
        if (position > 0) {     // the selected item is not the hint
            Integer countryID = selectedCountry.getLocationID();
            country = selectedCountry;
            locationViewModel.loadRegionSpinner(countryID);
        }
    }

    /**
     * This method is called when the user selects an item from the regionSpinner drop-down.
     * When a region is selected, that region's clusters will be displayed in the clusterSpinner.
     * The backend logic is handled by the method 'loadClusterSpinner' (defined in LocationViewModel).
     * @param spinner Reference to the regionSpinner object.
     * @param position The position of the view in the adapter that was clicked.
     */
    @OnItemSelected(R.id.spinner_select_region) void onRegionSelected(Spinner spinner, int position) {
        // call getItemAtPosition(position) to access the data associated with the selected item
        Location selectedRegion = (Location) spinner.getItemAtPosition(position);
        if (position > 0) {     // the selected item is not the hint
            Integer regionID = selectedRegion.getLocationID();
            region = selectedRegion;
            locationViewModel.loadClusterSpinner(regionID);
        }
    }

    /**
     * This method is called when the user selects an item from the clusterSpinner drop-down.
     * @param spinner Reference to the clusterSpinner object.
     * @param position The position of the view in the adapter that was clicked.
     */
    @OnItemSelected(R.id.spinner_select_cluster) void onClusterSelected(Spinner spinner, int position) {
        // call getItemAtPosition(position) to access the data associated with the selected item
        Location selectedCluster = (Location) spinner.getItemAtPosition(position);
        if (position > 0) {     // the selected item is not the hint
            cluster = selectedCluster;
        }
    }

    @OnClick(R.id.bn_location_download) void onClickDownload() {
        // store the current selected location for data downloading
        Constants.setCountry(country);
        Constants.setRegion(region);
        Constants.setCluster(cluster);

        loadHouseholdsForCluster();
        loadPatientsForCluster();
    }

    /**
     * This method is called when the NEXT button is clicked. It has two-step functions:
     *  1. Store selected location information in /utilities/Constants.
     *  2. Proceed to Household home page.
     */
    @OnClick(R.id.bn_location_next) void onClickNext() {
        // store the current selected location for later assessments
        Constants.setCountry(country);
        Constants.setRegion(region);
        Constants.setCluster(cluster);

        Intent intent = new Intent(this, DataSyncActivity.class);
        startActivity(intent);
    }

    private void loadHouseholdsForCluster() {
        // pass parameter 'clusterID' to url
        String url = GET_HOUSEHOLD_FOR_CLUSTER_URL + "?clusterID=" + Constants.getCluster().getLocationID();
        StringRequest loadHouseholdRequest = new StringRequest(Request.Method.GET, url, this::addHouseholdData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        loadHouseholdRequest.setTag("Download Household tables for cluster" + Constants.getCluster().getLocationID());
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(loadHouseholdRequest);
    }

    private void loadPatientsForCluster() {
        // pass parameter 'clusterID' to url
        String url = GET_PATIENT_FOR_CLUSTER_URL + "?clusterID=" + Constants.getCluster().getLocationID();
        StringRequest loadPatientRequest = new StringRequest(Request.Method.GET, url, this::addPatientData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        loadPatientRequest.setTag("Download Patient tables for cluster" + Constants.getCluster().getLocationID());
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(loadPatientRequest);
    }

    private void addHouseholdData(String jsonArray) {
        Log.i("received household data", jsonArray);    // debug
        locationViewModel.addHouseholdData(jsonArray);
    }

    private void addPatientData(String jsonArray) {
        Log.i("received patient data", jsonArray);    // debug
        locationViewModel.addPatientData(jsonArray);
    }
}
