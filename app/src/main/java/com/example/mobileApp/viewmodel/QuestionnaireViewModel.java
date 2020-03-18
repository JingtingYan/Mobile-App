package com.example.mobileApp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.datatype.Question;
import com.example.mobileApp.datatype.Response;
import com.example.mobileApp.utilities.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.mobileApp.utilities.Constants.DEFAULT_QN_INSTRUCTION_MCQ;
import static com.example.mobileApp.utilities.Constants.DEFAULT_QN_INSTRUCTION_SCQ;
import static com.example.mobileApp.utilities.Constants.DEFAULT_QN_INSTRUCTION_TEXT_ENTRY;

public class QuestionnaireViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    private static Question currQn;
    private static Integer prevQnID;
    public MutableLiveData<String> qnInstruction = new MutableLiveData<>();
    public MutableLiveData<String> qnString = new MutableLiveData<>();

    private String gpsLatitude;
    private String gpsLongitude;

    public volatile List<String> allResponses = new ArrayList<>();

    public QuestionnaireViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }


    // new HouseholdID = CountryID ++ RegionID ++ ClusterID ++ first 4 chars of EnumeratorID ++ LatestIndexInLocalHHTable
    public String generateNewHouseholdID() {
        int lastHHIndex = -1;
        try {
            lastHHIndex = repo.getHouseholdTableLastIndex();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return Constants.getCountry().getLocationID() + String.valueOf(Constants.getRegion().getLocationID()) +
                Constants.getCluster().getLocationID() + Constants.getEnumeratorID().substring(0,3) + (lastHHIndex + 1);
    }

    // new PatientID = HouseholdID ++ first 4 chars of EnumeratorID ++ LatestIndexInLocalPatientTable
    public String generateNewPatientID() {
        int lastPatientIndex = -1;
        try {
            lastPatientIndex = repo.getPatientTableLastIndex();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return Constants.getCurrentHouseholdID() + Constants.getEnumeratorID().substring(0,3) + (lastPatientIndex + 1);
    }

    // studyID = ClusterID ++ HouseholdID ++ PatientID
    private String generateStudyID() {
        return Constants.getCluster().getLocationID() + Constants.getCurrentHouseholdID() + Constants.getCurrentPatientID();
    }

    /**
     * This method is used to set question instruction for a certain question.
     * If not specified, the app would set the pre-defined question instruction for each type of question.
     * @return The question instruction that is going to be set.
     */
    private String setQnInstruction() {
        if (!currQn.getQuestionInstruction().equals("")) {
            return currQn.getQuestionInstruction();
        }
        switch (getQnType()) {
            case (1):
                return DEFAULT_QN_INSTRUCTION_SCQ;
            case (2):
                return DEFAULT_QN_INSTRUCTION_MCQ;
            case (3):
                return DEFAULT_QN_INSTRUCTION_TEXT_ENTRY;
            default:
                return "";
        }
    }

    public void loadFirstQuestion() {
        try {
            currQn = repo.loadFirstQuestion(Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        prevQnID = currQn.getQuestionID();
        qnInstruction.postValue(setQnInstruction());
        qnString.postValue(currQn.getQuestionString());
    }

    public void loadLastAnsweredQuestion() {
        try {
            currQn = repo.loadThisQuestion(Constants.getSelectedAssessment().getLastAnsweredQnID(), Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        prevQnID = currQn.getQuestionID();
        qnInstruction.postValue(setQnInstruction());
        qnString.postValue(currQn.getQuestionString());
    }

    /* questionType = 1 means Single Choice Question
       questionType = 2 means Multiple Choice Question
       questionType = 3 means Text Entry Question */
    public Integer getQnType() {
        return currQn.getQuestionType();
    }

    public List<Answer> loadAnswerChoices() {
        return currQn.getAnswers();
    }

    public boolean hasNextQuestion() {
        int nextQnID = -2;  // initialise nextQnID to be a meaningless integer
        try {
            nextQnID = repo.getNextQnID(Constants.getCurrentPatientID(), currQn.getQuestionID(), Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        // if nextQnID is -1 then the current question is the last question in this questionnaire
        // otherwise there is a next question
        return (nextQnID != -1) && (nextQnID != -2);
    }

    public void loadNextQuestion() {
        try {
            currQn = repo.loadNextQuestion(Constants.getCurrentPatientID(), prevQnID, Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        prevQnID = currQn.getQuestionID();
        qnInstruction.postValue(setQnInstruction());
        qnString.postValue(currQn.getQuestionString());
    }

    public void storeHouseholdResponsesToDb() {
        HouseholdTable householdTable = new HouseholdTable(Constants.getCurrentHouseholdID(), Constants.getCluster().getLocationID(),
                Constants.getEnumeratorID(), Constants.getCurrentQuestionnaireStartDate(), gpsLatitude, gpsLongitude, 1);

        householdTable.setVillage_street_name(allResponses.get(0));
        householdTable.setAvailability(allResponses.get(1));
        householdTable.setReason_refusal(allResponses.get(2));
        householdTable.setVisit_num(Integer.parseInt(allResponses.get(3)));
        householdTable.setKey_informer(allResponses.get(4));
        householdTable.setTel1_num(allResponses.get(5));
        householdTable.setTel1_owner(allResponses.get(6));
        householdTable.setTel2_num(allResponses.get(7));
        householdTable.setTel2_owner(allResponses.get(8));
        householdTable.setConsent(allResponses.get(9));
        householdTable.setA2_q1(allResponses.get(10));
        householdTable.setA2_q2(allResponses.get(11));
        householdTable.setA2_q3(allResponses.get(12));
        householdTable.setA2_q4(allResponses.get(13));
        householdTable.setA2_q5(allResponses.get(14));
        householdTable.setA2_q6(allResponses.get(15));
        householdTable.setA2_q7(allResponses.get(16));
        householdTable.setA2_q8(allResponses.get(17));
        householdTable.setA2_q9(allResponses.get(18));
        householdTable.setA2_q10(allResponses.get(19));
        householdTable.setA2_q11(allResponses.get(20));
        householdTable.setA2_q12(allResponses.get(21));
        householdTable.setA2_q13(allResponses.get(22));

        repo.storeHouseholdToDb(householdTable);
    }

    public void storePatientInfoToDb() {
        PatientTable patientTable = new PatientTable(Constants.getCurrentPatientID(), generateStudyID(),
                Constants.getCurrentHouseholdID(), 1);

        patientTable.setEnum_id(Constants.getEnumeratorID());
        patientTable.setDate_of_birth(allResponses.get(0));
        patientTable.setPrefix(allResponses.get(1));
        patientTable.setFirst_name(allResponses.get(2));
        patientTable.setMiddle_name(allResponses.get(3));
        patientTable.setLast_name(allResponses.get(4));
        patientTable.setSuffix(allResponses.get(5));
        patientTable.setCom_name(allResponses.get(6));
        patientTable.setGender(formatGender(allResponses.get(7)));
        patientTable.setDur_hh(allResponses.get(8));
        patientTable.setLvl_edu(allResponses.get(9));
        patientTable.setWork_status(allResponses.get(10));
        patientTable.setMarital_status(allResponses.get(11));
        patientTable.setMother_first(allResponses.get(12));
        patientTable.setMother_last(allResponses.get(13));
        patientTable.setTel1_num(allResponses.get(14));
        patientTable.setTel1_owner(allResponses.get(15));
        patientTable.setTel1_owner_rel(allResponses.get(16));
        patientTable.setTel2_num(allResponses.get(17));
        patientTable.setTel2_owner(allResponses.get(18));
        patientTable.setTel2_owner_rel(allResponses.get(19));
        patientTable.setNational_id(allResponses.get(20));
        patientTable.setResponder(allResponses.get(21));
        patientTable.setProxy_name(allResponses.get(22));
        patientTable.setProxy_rel(allResponses.get(23));
        patientTable.setNotes(allResponses.get(24));

        repo.storePatientToDb(patientTable);
    }

    private String formatGender(String response) {
        response = response.trim().toLowerCase();
        if (response.equals("female")) {
            return "F";
        } else if (response.equals("male")) {
            return "M";
        } else {
            return "O";
        }
    }

    public void storeSCQResponse(Answer selectedAns) {
        storeSingleResponseToDb(new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                selectedAns.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now()));
    }

    public void storeMCQResponse(List<Answer> selectedAns) {
        for (Answer answer : selectedAns) {
            storeSingleResponseToDb(new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                    answer.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now()));
        }
    }

    public void storeTextQnResponse(String responseString) {
        storeSingleResponseToDb(new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                -1, Constants.getCurrentQuestionnaireID(), responseString, LocalDate.now()));
    }

    private void storeSingleResponseToDb (Response response) {
        try {
            repo.storeSingleResponseToDb(response);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this method is used to create a NEW AssessmentStatus table entry and mark the status as complete
    public void updateNewAssessmentToComplete() {
        int lastIndex = 0;
        try {
            lastIndex = repo.getAssessmentStatusTableLastIndex();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        PatientAssessmentStatusTable assessmentStatusTable =
                new PatientAssessmentStatusTable(lastIndex + 1, Constants.getCurrentPatientID(),
                        Constants.getCurrentQuestionnaireID(), "COMPLETE",
                        Constants.getCurrentQuestionnaireStartDate(), LocalDate.now().toString(),
                        -1);   // set last_answered_qn_id = -1 to indicate this qnn is COMPLETED
        repo.addNewAssessmentStatus(assessmentStatusTable);
    }

    public void updateNewAssessmentToIncomplete() {
        int lastIndex = 0;
        try {
            lastIndex = repo.getAssessmentStatusTableLastIndex();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        PatientAssessmentStatusTable assessmentStatusTable =
                new PatientAssessmentStatusTable(lastIndex + 1, Constants.getCurrentPatientID(),
                        Constants.getCurrentQuestionnaireID(), "INCOMPLETE",
                        Constants.getCurrentQuestionnaireStartDate(), "", prevQnID);
        // set the last_answered_qn_id = prevQnID, the current question's response won't be stored
        // next time will continue with the current question
        repo.addNewAssessmentStatus(assessmentStatusTable);
    }

    public void updateExistingAssessmentToComplete(String startDate) {
        try {
            PatientAssessmentStatusTable assessmentStatusTable = repo.findExistingAssessment(Constants.getCurrentPatientID(), Constants.getCurrentQuestionnaireID(), startDate);
            repo.updateExistingAssessmentStatusTable(new PatientAssessmentStatusTable(assessmentStatusTable.getIndex(),
                    Constants.getCurrentPatientID(), Constants.getCurrentQuestionnaireID(), "COMPLETE", startDate,
                    LocalDate.now().toString(), -1));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateExistingAssessmentToIncomplete(String startDate) {
        try {
            PatientAssessmentStatusTable assessmentStatusTable = repo.findExistingAssessment(Constants.getCurrentPatientID(), Constants.getCurrentQuestionnaireID(), startDate);
            repo.updateExistingAssessmentStatusTable(new PatientAssessmentStatusTable(assessmentStatusTable.getIndex(),
                    Constants.getCurrentPatientID(), Constants.getCurrentQuestionnaireID(), "INCOMPLETE", startDate, "",
                    prevQnID));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
