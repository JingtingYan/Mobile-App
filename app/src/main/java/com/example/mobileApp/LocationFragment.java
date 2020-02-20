package com.example.mobileApp;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mobileApp.datatype.Location;
import com.example.mobileApp.utilities.MySingleton;
import com.example.mobileApp.viewmodel.LocationViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment implements View.OnClickListener{

    private Spinner countrySpinner, regionSpinner, clusterSpinner;
    private Button bnNext;

    private LocationViewModel locationViewModel;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        countrySpinner = view.findViewById(R.id.spinner_select_country);
        regionSpinner = view.findViewById(R.id.spinner_select_region);
        clusterSpinner = view.findViewById(R.id.spinner_select_cluster);
        bnNext = view.findViewById(R.id.bn_location_next);

        bnNext.setOnClickListener(this);

        initViewModel();

        initCountrySpinner();

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Location selectedCountry = (Location) parent.getItemAtPosition(position);
                if (position > 0) {
                    Integer countryID = selectedCountry.getLocationID();
                    locationViewModel.loadRegionSpinner(countryID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Location selectedRegion = (Location) parent.getItemAtPosition(position);
                if (position > 0) {
                    Integer regionID = selectedRegion.getLocationID();
                    locationViewModel.loadClusterSpinner(regionID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });

        clusterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Location selectedCluster = (Location) parent.getItemAtPosition(position);
                if (position > 0) {
                    Integer clusterID = selectedCluster.getLocationID();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });

        return view;
    }

    private void initViewModel() {

        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        locationViewModel.spinnerRegions.observe(getViewLifecycleOwner(), this::updateRegionSpinner);

        locationViewModel.spinnerClusters.observe(getViewLifecycleOwner(), this::updateClusterSpinner);
    }

    private void initCountrySpinner() {
        // display all countries in countrySpinner
        ArrayAdapter<Location> countryAdapter = new ArrayAdapter<Location>(
                getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, locationViewModel.loadCountrySpinner()) {

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
                getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, locations) {

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
                getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, locations) {

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

    @Override
    public void onClick(View v) {

    }
}
