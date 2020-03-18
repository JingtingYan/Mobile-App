package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.datatype.PatientRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SingleHouseholdViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    private static HouseholdTable currentHousehold;

    public SingleHouseholdViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public List<PatientRecyclerViewItem> loadPatients() {
        List<PatientRecyclerViewItem> items = new ArrayList<>();
        List<PatientTable> patientTables = loadPatientsForHousehold();

        for (PatientTable patientTable : patientTables) {
            items.add(patientTableToRecyclerViewItemConverter(patientTable));
        }
        return items;
    }

    private List<PatientTable> loadPatientsForHousehold() {
        List<PatientTable> patientTables = new ArrayList<>();
        try {
             patientTables.addAll(repo.loadPatientsForHousehold(Constants.getCurrentHouseholdID()));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return patientTables;
    }

    private PatientRecyclerViewItem patientTableToRecyclerViewItemConverter(PatientTable patientTable) {
        String patientName = patientTable.getPrefix().trim() + " " + patientTable.getFirst_name().trim() + " " +
                patientTable.getMiddle_name().trim() + " " + patientTable.getLast_name().trim() + " " + patientTable.getSuffix().trim();

        return new PatientRecyclerViewItem(patientTable.getPatient_id(), patientName, patientTable.getDate_of_birth(),
                patientTable.getStudy_id(), patientTable.getHh_id());
    }

    public void loadCurrentHousehold() {
        try {
            currentHousehold = repo.getCurrentHousehold(Constants.getCurrentHouseholdID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getHouseholdLocation() {
        return currentHousehold.getVillage_street_name();
    }

    public String getHouseholdInformant() {
        return currentHousehold.getKey_informer();
    }
}
