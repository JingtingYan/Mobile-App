package com.example.mobileApp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.datatype.Question;
import com.example.mobileApp.datatype.Response;
import com.example.mobileApp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HouseholdCreateViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    private static Question currQn;
    private static Integer prevQnID;
    public MutableLiveData<String> qnInstruction = new MutableLiveData<>();
    public MutableLiveData<String> qnString = new MutableLiveData<>();
    private String GPSCoordinates;

    public volatile List<String> allResponses = new ArrayList<>();

    public HouseholdCreateViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public String getGPSCoordinates() {
        return GPSCoordinates;
    }

    public void setGPSCoordinates(String GPSCoordinates) {
        this.GPSCoordinates = GPSCoordinates;
    }


    // new HouseholdID = CountryID ++ RegionID ++ ClusterID ++ EnumID ++ LatestIndexInHHTABLEServer
    public String generateNewHouseholdID() {
        int lastHHIndex = -1;
        try {
            lastHHIndex = repo.getHouseholdTableLastIndex();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("last hh ID", String.valueOf(lastHHIndex));   // debug

        String newHHID = (Constants.getCountry().getLocationID().toString()) + (Constants.getRegion().getLocationID().toString()) +
               (Constants.getCluster().getLocationID().toString()) + (Constants.getEnumeratorID()) + (lastHHIndex + 1);

        Log.i("generate new hh ID", newHHID);   // debug

        return newHHID;
    }

    public void loadFirstQuestion() {
        try {
            currQn = repo.loadFirstQuestion(Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        prevQnID = currQn.getQuestionID();
        qnInstruction.postValue(currQn.getQuestionInstruction());
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
        return nextQnID != -1;
    }

    public void loadNextQuestion() {
        try {
            currQn = repo.loadNextQuestion(Constants.getCurrentPatientID(), prevQnID, Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        prevQnID = currQn.getQuestionID();
        qnInstruction.postValue(currQn.getQuestionInstruction());
        qnString.postValue(currQn.getQuestionString());
    }

    public void storeResponsesToDb() {
        HouseholdTable householdTable = new HouseholdTable(generateNewHouseholdID(), Constants.getCluster().getLocationID(),
                Constants.getEnumeratorID(), Constants.getHouseholdRosterQuestionnaireDate(), GPSCoordinates);

        householdTable.setVillage_name(allResponses.get(0));
        householdTable.setStreet_name(allResponses.get(0));     // need to change this later
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

    /*
    public void storeSCQResponse(Answer selectedAns) {
        Response response = new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                selectedAns.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now());
        allResponses.add(response);
    }

    public void storeMCQResponse(List<Answer> selectedAns) {
        for (Answer answer : selectedAns) {
            Response response = new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                    answer.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now());
            allResponses.add(response);
        }
    }

    public void storeTextQnResponse(String responseString) {
        Response response = new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                -1, Constants.getCurrentQuestionnaireID(), responseString, LocalDate.now());
        allResponses.add(response);
    }

    public void storeResponsesToDb() {
        try {
            repo.storeResponsesToDb(allResponses);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
     */
}
