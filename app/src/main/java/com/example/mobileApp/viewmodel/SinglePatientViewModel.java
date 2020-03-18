package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.database.entity.QuestionnaireTable;
import com.example.mobileApp.datatype.AssessmentRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SinglePatientViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    private static PatientTable currentPatient;

    public SinglePatientViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public void loadCurrentPatient() {
        try {
            currentPatient = repo.getCurrentPatient(Constants.getCurrentPatientID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String loadPatientName() {
        return currentPatient.getPrefix().trim() + " " + currentPatient.getFirst_name().trim() + " " + currentPatient.getMiddle_name().trim()
                + " " + currentPatient.getLast_name().trim() + " " + currentPatient.getSuffix().trim();
    }

    public String loadHouseholdID() {
        return currentPatient.getHh_id();
    }

    public String loadPatientGender() {
        return currentPatient.getGender();
    }

    public String loadPatientDOB() {
        return currentPatient.getDate_of_birth();
    }

    public List<AssessmentRecyclerViewItem> loadAssessmentStatusList() {
        List<AssessmentRecyclerViewItem> items = new ArrayList<>();
        List<PatientAssessmentStatusTable> tables = loadAssessmentStatusForPatient();

        for (PatientAssessmentStatusTable table : tables) {
            String qnnName = getQuestionnaireName(table.getQnnaire_id());
            items.add(new AssessmentRecyclerViewItem(qnnName, table.getQnnaire_status(), table.getQnnaire_id(),
                    table.getStart(), table.getEnd(), table.getLast_answered_qn_id()));
        }

        return items;
    }

    private List<PatientAssessmentStatusTable> loadAssessmentStatusForPatient() {
        List<PatientAssessmentStatusTable> assessmentStatusTables = new ArrayList<>();
        try {
            assessmentStatusTables.addAll(repo.loadAssessmentStatusForPatient(Constants.getCurrentPatientID()));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return assessmentStatusTables;
    }

    private String getQuestionnaireName(int questionnaireID) {
        try {
            QuestionnaireTable questionnaireTable = repo.getQuestionnaireInfo(questionnaireID);
            String qnnName = questionnaireTable.getQuestionnaire_name().trim();
            String qnnVersion = questionnaireTable.getQuestionnaire_version().trim();
            return qnnName + " (version: " + qnnVersion + ")";
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
