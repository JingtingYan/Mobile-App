package com.example.mobileApp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.datatype.Question;
import com.example.mobileApp.datatype.Response;
import com.example.mobileApp.utilities.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HouseholdCreateViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    private static Question currQn;
    private static Integer prevQnID;
    public MutableLiveData<String> qnInstruction = new MutableLiveData<>();
    public MutableLiveData<String> qnString = new MutableLiveData<>();

    private List<Response> allResponses = new ArrayList<>();

    public HouseholdCreateViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public void loadFirstQuestion() {
        try {
            Log.i("HHCreateViewModel", "call loadFirstQuestion() with" + "QnnID: " + Constants.getCurrentQuestionnaireID());     // debug
            currQn = repo.loadFirstQuestion(Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Repo", "finish getFirstQnID");    // debug

        Log.i("HHCreateViewModel", "first qn instruction is: " + currQn.getQuestionInstruction());     // debug
        Log.i("HHCreateViewModel", "first qn string is: " + currQn.getQuestionString());     // debug
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
            nextQnID = repo.getNextQnID(currQn.getQuestionID(), Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        // if nextQnID is -1 then the current question is the last question in this questionnaire
        // otherwise there is a next question
        return nextQnID != -1;
    }

    public void loadNextQuestion() {
        try {
            currQn = repo.loadNextQuestion(prevQnID, Constants.getCurrentQuestionnaireID());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        prevQnID = currQn.getQuestionID();
        qnInstruction.postValue(currQn.getQuestionInstruction());
        qnString.postValue(currQn.getQuestionString());
    }

    public void storeSCQResponse(Answer selectedAns) {
        Response response = new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                selectedAns.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now());
        allResponses.add(response);
        Log.i("patientID", Constants.getCurrentPatientID());    //debug
    }

    public void storeMCQResponse(List<Answer> selectedAns) {
        for (Answer answer : selectedAns) {
            Response response = new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                    answer.getAnswerID(), Constants.getCurrentQuestionnaireID(), "", LocalDate.now());
            allResponses.add(response);
            Log.i("patientID", Constants.getCurrentPatientID());    //debug
        }
    }

    public void storeTextQnResponse(String responseString) {
        Response response = new Response(Constants.getCurrentPatientID(), currQn.getQuestionID(),
                -1, Constants.getCurrentQuestionnaireID(), responseString, LocalDate.now());
        allResponses.add(response);
        Log.i("patientID", Constants.getCurrentPatientID());    //debug
    }

    public void storeResponsesToDb() {
        try {
            repo.storeResponsesToDb(allResponses);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
