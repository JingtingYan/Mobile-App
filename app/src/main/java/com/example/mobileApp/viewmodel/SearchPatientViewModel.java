package com.example.mobileApp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.datatype.PatientRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchPatientViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    public SearchPatientViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public List<PatientRecyclerViewItem> loadAllPatients() {
        Log.i("search patient vm", "loadAllPatients() is called");      // debug

        List<PatientRecyclerViewItem> items = new ArrayList<>();
        List<PatientTable> patientTables = new ArrayList<>();

        try {
            patientTables.addAll(repo.loadAllPatients());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        for (PatientTable patientTable : patientTables) {
            items.add(patientTableToRecyclerViewItemConverter(patientTable));
        }

        return items;
    }

    private PatientRecyclerViewItem patientTableToRecyclerViewItemConverter(PatientTable patientTable) {
        String patientName = patientTable.getPrefix().trim() + " " + patientTable.getFirst_name().trim() + " " +
                patientTable.getMiddle_name().trim() + " " + patientTable.getLast_name().trim() + " " + patientTable.getSuffix().trim();

        return new PatientRecyclerViewItem(patientTable.getPatient_id(), patientName, patientTable.getDate_of_birth(),
                patientTable.getStudy_id(), patientTable.getHh_id());
    }
}
