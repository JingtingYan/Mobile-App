package com.example.mobileApp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
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

    // new HouseholdID = CountryID ++ RegionID ++ ClusterID ++ LatestIndexInHHTABLEServer
    public String generateNewHouseholdID() {
        int lastHHIndex = -1;
        try {
            lastHHIndex = repo.getHouseholdTableLastIndex();
            Log.i("qnn vm - generateNewHouseholdID", "lastHHIndex is: " + lastHHIndex);     // debug
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return (Constants.getCountry().getLocationID().toString()) + (Constants.getRegion().getLocationID().toString()) +
               (Constants.getCluster().getLocationID().toString()) + (lastHHIndex + 1);
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
            Log.i("hh create vm - loadFirstQuestion", "currQn is: " + currQn.toString());   // debug
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
        Log.i("hh create vm - hasNextQuestion", "next qn id is: " + nextQnID);  // debug
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
        String newHHID = generateNewHouseholdID();
        Log.i("qnn vm - storeHouseholdResponsesToDb", "generated new hh id is: " + newHHID);    // debug

        HouseholdTable householdTable = new HouseholdTable(newHHID, Constants.getCluster().getLocationID(),
                Constants.getEnumeratorID(), Constants.getHouseholdRosterQuestionnaireDate(), gpsLatitude, gpsLongitude);

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

    public void storeSCQResponse(Answer selectedAns) {
        storeSingleResponseToDb(new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                selectedAns.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now()));
//        allResponses.add(response);
    }

    public void storeMCQResponse(List<Answer> selectedAns) {
        for (Answer answer : selectedAns) {
            storeSingleResponseToDb(new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                    answer.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now()));
//            allResponses.add(response);
        }
    }

    public void storeTextQnResponse(String responseString) {
        storeSingleResponseToDb(new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                -1, Constants.getCurrentQuestionnaireID(), responseString, LocalDate.now()));
//        allResponses.add(response);
    }

    private void storeSingleResponseToDb (Response response) {
        try {
            repo.storeSingleResponseToDb(response);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateAssessmentStatus() {
        int lastIndex = 0;
        try {
            lastIndex = repo.getAssessmentStatusTableLastIndex();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        PatientAssessmentStatusTable assessmentStatusTable =
                new PatientAssessmentStatusTable(lastIndex + 1, Constants.getCurrentPatientID(),
                        Constants.getCurrentQuestionnaireID(), "COMPLETE",
                        Constants.getWashingtonQuestionnaireStartDate(), LocalDate.now().toString());
        repo.addNewAssessmentStatus(assessmentStatusTable);
    }

//    public void storeResponsesToDb() {
//        try {
//            repo.storeResponsesToDb(allResponses);
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
