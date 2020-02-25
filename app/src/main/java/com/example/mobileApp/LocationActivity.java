package com.example.mobileApp;

import android.graphics.Color;
import android.os.Bundle;

import com.example.mobileApp.datatype.Location;
import com.example.mobileApp.viewmodel.LocationViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_location) Toolbar toolbar;
    @BindView(R.id.spinner_select_country) Spinner countrySpinner;
    @BindView(R.id.spinner_select_region) Spinner regionSpinner;
    @BindView(R.id.spinner_select_cluster) Spinner clusterSpinner;
    @BindView(R.id.bn_location_next) Button bnNext;

    private LocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initViewModel();

        initCountrySpinner();
    }

    private void initViewModel() {

        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        locationViewModel.spinnerRegions.observe(this, this::updateRegionSpinner);

        locationViewModel.spinnerClusters.observe(this, this::updateClusterSpinner);
    }

    private void initCountrySpinner() {
        // display all countries in countrySpinner
        ArrayAdapter<Location> countryAdapter = new ArrayAdapter<Location>(
                this, android.R.layout.simple_spinner_item, locationViewModel.loadCountrySpinner()) {

            @Override
            public boolean isEnabled(int position){
                return position != 0;   // Disable the first item (hint) from Spinner
            }

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

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);
    }

    private void updateRegionSpinner(List<Location> locations) {
        ArrayAdapter<Location> regionAdapter = new ArrayAdapter<Location>(
                this, android.R.layout.simple_spinner_item, locations) {

            @Override
            public boolean isEnabled(int position){
                return position != 0;   // Disable the first item (hint) from Spinner
            }

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

        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);
    }

    private void updateClusterSpinner(List<Location> locations) {
        ArrayAdapter<Location> clusterAdapter = new ArrayAdapter<Location>(
                this, android.R.layout.simple_spinner_item, locations) {

            @Override
            public boolean isEnabled(int position){
                return position != 0;   // Disable the first item (hint) from Spinner
            }

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

        clusterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clusterSpinner.setAdapter(clusterAdapter);
    }


    @OnItemSelected(R.id.spinner_select_country) void onCountrySelected(Spinner spinner, int position) {
        Location selectedCountry = (Location) spinner.getItemAtPosition(position);
        if (position > 0) {
            Integer countryID = selectedCountry.getLocationID();
            locationViewModel.loadRegionSpinner(countryID);
        }
    }

    @OnItemSelected(R.id.spinner_select_region) void onRegionSelected(Spinner spinner, int position) {
        Location selectedRegion = (Location) spinner.getItemAtPosition(position);
        if (position > 0) {
            Integer regionID = selectedRegion.getLocationID();
            locationViewModel.loadClusterSpinner(regionID);
        }
    }

    @OnItemSelected(R.id.spinner_select_cluster) void onClusterSelected(Spinner spinner, int position) {
        Location selectedCluster = (Location) spinner.getItemAtPosition(position);
        if (position > 0) {
            Integer clusterID = selectedCluster.getLocationID();
        }
    }

    @OnClick(R.id.bn_location_next) void onClick() {

    }

}
