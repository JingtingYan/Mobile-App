package com.example.mobileApp;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.QuestionnaireViewModel;

import java.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.PATIENT_BASIC_INFORMATION_QUESTIONNAIRE;

// This class is used to create new Household or new Patient;
// these two processes share a similar logic, therefore, merge them together to reduce code redundancy.

public class UserCreateFragment extends Fragment implements LocationListener{

    @BindView(R.id.txt_qn_instruction) TextView txtQnInstruction;
    @BindView(R.id.txt_qn_string) TextView txtQnString;
    @BindView(R.id.bn_qnn_next) Button bnNext;
    @BindView(R.id.bn_qnn_exit) Button bnExit;

    private QuestionnaireViewModel questionnaireViewModel;
    private static FragmentManager answerFragmentManager;

    public UserCreateFragment() {
        // Empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionnaire, container, false);

        ButterKnife.bind(this, view);

        setActivityTitle();

        // Hide the "Exit and Continue later" button to ensure user finishes the Questionnaire at one time
        bnExit.setVisibility(View.GONE);

        answerFragmentManager = getChildFragmentManager();

        initViewModel();

        initData();

        // Household Roster Questionnaire requires to check permission for accessing location
        if (Constants.getCurrentQuestionnaireID() == HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            }
        }

        loadFirstQuestion();

        return view;
    }

    private void setActivityTitle() {
        if (Constants.getCurrentQuestionnaireID() == HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_create_hh);
        } else if (Constants.getCurrentQuestionnaireID() == PATIENT_BASIC_INFORMATION_QUESTIONNAIRE) {
            requireActivity().setTitle(R.string.title_create_patient);
        } else {
            requireActivity().setTitle(R.string.title_asmt_default);
        }
    }

    private void initViewModel() {
        questionnaireViewModel = new ViewModelProvider(requireActivity()).get(QuestionnaireViewModel.class);

        questionnaireViewModel.qnInstruction.observe(getViewLifecycleOwner(), this::updateQnInstruction);
        questionnaireViewModel.qnString.observe(getViewLifecycleOwner(), this::updateQnString);
    }

    // update relevant data fields that need to be stored in db later
    private void initData() {
        // clear prev responses stored
        questionnaireViewModel.allResponses.clear();

        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        if (Constants.getCurrentQuestionnaireID() == PATIENT_BASIC_INFORMATION_QUESTIONNAIRE) {
            Constants.setCurrentPatientID(questionnaireViewModel.generateNewPatientID());
        }

        if (Constants.getCurrentQuestionnaireID() == HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID) {
            Constants.setCurrentHouseholdID(questionnaireViewModel.generateNewHouseholdID());
            getGPSCoordinates();
        }
    }

    private void updateQnInstruction(String qnInstruction) {
        txtQnInstruction.setText(qnInstruction);
    }

    private void updateQnString(String qnString) {
        txtQnString.setText(qnString);
    }

    private void loadFirstQuestion() {
        questionnaireViewModel.loadFirstQuestion();
        displayQuestion(questionnaireViewModel.getQnType());
    }

    @OnClick(R.id.bn_qnn_next)
    void onClickNext() {
        storeQnResponse(questionnaireViewModel.getQnType());

        if (questionnaireViewModel.hasNextQuestion()) {
            loadNextQuestion();
            hideKeyboard(requireActivity());
        } else {
            // move to the end page of the Questionnaire
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new QuestionnaireFinishFragment()).commit();
        }
    }

    private void loadNextQuestion() {
        questionnaireViewModel.loadNextQuestion();
        displayQuestion(questionnaireViewModel.getQnType());
    }

    /* questionType = 1 means Single Choice Question
       questionType = 2 means Multiple Choice Question
       questionType = 3 means Text Entry Question */
    private void displayQuestion(Integer qnType) {
        switch (qnType) {
            case (1):
                answerFragmentManager.beginTransaction().replace(R.id.answer_container, new SCQAnsFragment()).commit();
                break;
            case (2):
                answerFragmentManager.beginTransaction().replace(R.id.answer_container, new MCQAnsFragment()).commit();
                break;
            case (3):
                answerFragmentManager.beginTransaction().replace(R.id.answer_container, new TextAnsFragment()).commit();
                break;
            default:
                break;
        }
    }

    private void storeQnResponse(Integer qnType) {
        switch (qnType) {
            case (1):
                questionnaireViewModel.allResponses.add(SCQAnsFragment.selectedAns.getAnswerString());
                break;
            case (2):
                questionnaireViewModel.allResponses.add(MCQAnsFragment.getSelectedAns().toString());
                break;
            case (3):
                questionnaireViewModel.allResponses.add(TextAnsFragment.responseString);
                TextAnsFragment.responseString = "";    // reset
                break;
            default:
                break;
        }
    }

    private void getGPSCoordinates() {
        try {
            LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, this);
            }
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        questionnaireViewModel.setGpsLatitude(String.valueOf(location.getLatitude()));
        questionnaireViewModel.setGpsLongitude(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // leave blank
    }

    @Override
    public void onProviderEnabled(String provider) {
        // leave blank
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(requireActivity(), "Please Enable GPS", Toast.LENGTH_SHORT).show();
        getGPSCoordinates();
    }

    // hide keyboard when switching to next question fragment
    private void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
