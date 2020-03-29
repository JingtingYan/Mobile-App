package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.datatype.PatientRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * The SearchPatientViewModel class contains a list of methods used by SearchPatientFragment.
 *
 * Functions:
 *  1. It is the communication centre between SearchPatientFragment and MobileAppRepository.
 *  2. It prepares data that is going to be displayed on views inside SearchPatientFragment.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class SearchPatientViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    public SearchPatientViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public List<PatientRecyclerViewItem> loadAllPatients() {
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
