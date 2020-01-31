package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private Spinner countrySpinner, regionSpinner, clusterSpinner;
    private Button bnNext;


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

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedCountry = parent.getItemAtPosition(position).toString();
//                switch (selectedCountry){
//                    case "Indonesia":
//                        regionSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.indonesia_regions)));
//                        break;
//                    case "India":
//                        regionSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.india_regions)));
//                        break;
//                    case "Nigeria":
//                        regionSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.nigeria_regions)));
//                        break;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedRegion = parent.getItemAtPosition(position).toString();
//                switch (selectedRegion){
//                    case "Java":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.indonesia_java_clusters)));
//                        break;
//                    case "Srinigar":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.india_strinigar_clusters)));
//                        break;
//                    case "Chandigargh":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.india_chandigargh_clusters)));
//                        break;
//                    case "Simla":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.india_simla_cluster)));
//                    case "Ado":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.nigeria_ado_cluster)));
//                        break;
//                    case "Bauchi":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.nigeria_bauchi_cluster)));
//                        break;
//                    case "Sokoto":
//                        clusterSpinner.setAdapter(new ArrayAdapter<String>(parent.getContext(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                getResources().getStringArray(R.array.nigeria_sokoto_cluster)));
//                        break;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });

        clusterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });

        return view;
    }

}
