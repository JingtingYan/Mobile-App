package com.example.prototype1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner countrySpinner, regionSpinner, clusterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countrySpinner = (Spinner) findViewById(R.id.spinner);
        regionSpinner = (Spinner) findViewById(R.id.spinner2);
        clusterSpinner = (Spinner) findViewById(R.id.spinner3);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = parent.getItemAtPosition(position).toString();
                switch (selectedCountry){
                    case "Indonesia":
                        regionSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        getResources().getStringArray(R.array.indonesia_regions)));
                        break;
                    case "India":
                        regionSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.india_regions)));
                        break;
                    case "Nigeria":
                        regionSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nigeria_regions)));
                        break;
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
                String selectedRegion = parent.getItemAtPosition(position).toString();
                switch (selectedRegion){
                    case "Java":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.indonesia_java_clusters)));
                        break;
                    case "Srinigar":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.india_strinigar_clusters)));
                        break;
                    case "Chandigargh":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.india_chandigargh_clusters)));
                        break;
                    case "Simla":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.india_simla_cluster)));
                    case "Ado":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nigeria_ado_cluster)));
                        break;
                    case "Bauchi":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nigeria_bauchi_cluster)));
                        break;
                    case "Sokoto":
                        clusterSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nigeria_sokoto_cluster)));
                        break;
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // leave it blank
            }
        });


















//        I have made 3 spinners, their ID is spinner, spinner2 and spinner3 which is how the activity knows which spinner to put on screen
//        Look at res/layout/activity_main.xml to see the UI design. Make sure the bottom tab is set to "design" not "text" so u can see the visual
//        display rather than the code of the display


//        in java/com.example.prototype1:
//        Cluster, Country, Region classes are each a @Entity (table) within the database
//        I have set up the primary and foreign keys that link the tables together

//        DbDAO -> the DAO of the database. DAO holds all the database queries (text me if you don't understand the queries).
//                  - the DAO always executes queries on a separate thread from the main activity thread
//                   - LiveData<> type is a data that can be observed (by an Observer object). The observer is able to pull the LATEST data held by the
//                     LiveData<> type.

//        DbRepository -> In theory, it is meant to hold access to multiple data sources (eg if you're pulling from multiple databases or backend sources)
//                       - The DbRepository is meant to be used by the ViewModel (I dont have one -> part of the problem)
//                       - The ViewModel holds the data needed by the UI and interacts directly with the UI (its a separation between the Repository (which holds the DAO) and the UI)


//        I have made the Database, DAO with query logic, Repository, Main activity Layout

//        Problem: I don't know how to link the repository which contains the data from SQLite to the MainActivity UI
//                  In "Room with a View" Tutorial, I get stuck at tutorial 9
//                  - How to implement a DYNAMIC spinner?


    }

}
