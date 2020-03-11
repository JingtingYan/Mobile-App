package com.example.mobileApp;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.HouseholdCreateViewModel;

import java.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID;

public class HouseholdCreateFragment extends Fragment implements LocationListener{

    @BindView(R.id.txt_qn_instruction)
    TextView txtQnInstruction;
    @BindView(R.id.txt_qn_string)
    TextView txtQnString;
    @BindView(R.id.bn_hh_create_next)
    Button bnNext;

    private HouseholdCreateViewModel householdCreateViewModel;
    private static FragmentManager answerFragmentManager;

    public HouseholdCreateFragment() {
        // Empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_household_create, container, false);

        ButterKnife.bind(this, view);

        requireActivity().setTitle(R.string.title_create_hh);

        answerFragmentManager = getChildFragmentManager();

        initViewModel();

        // check permission for accessing location
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        initData();

        loadFirstQuestion();

        return view;
    }

    private void initViewModel() {
        householdCreateViewModel = new ViewModelProvider(requireActivity()).get(HouseholdCreateViewModel.class);

        householdCreateViewModel.qnInstruction.observe(getViewLifecycleOwner(), this::updateQnInstruction);
        householdCreateViewModel.qnString.observe(getViewLifecycleOwner(), this::updateQnString);
    }

    // update relevant data fields that need to be stored in db later
    private void initData() {
        Constants.setCurrentQuestionnaireID(HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID);
        Constants.setHouseholdRosterQuestionnaireDate(LocalDate.now().toString());
        Constants.setCurrentHouseholdID(householdCreateViewModel.generateNewHouseholdID());
        getGPSCoordinates();
    }

    private void updateQnInstruction(String qnInstruction) {
        txtQnInstruction.setText(qnInstruction);
    }

    private void updateQnString(String qnString) {
        txtQnString.setText(qnString);
    }


    private void loadFirstQuestion() {
        householdCreateViewModel.loadFirstQuestion();
        displayQuestion(householdCreateViewModel.getQnType());
    }

    @OnClick(R.id.bn_hh_create_next)
    void onClickNext() {
        storeQnResponse(householdCreateViewModel.getQnType());

        if (householdCreateViewModel.hasNextQuestion()) {
            loadNextQuestion();
        } else {
            // move to the end page of Household Roaster Questionnaire
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new HouseholdCreateFinishFragment())
                    .commit();
        }
    }

    private void loadNextQuestion() {
        householdCreateViewModel.loadNextQuestion();
        displayQuestion(householdCreateViewModel.getQnType());
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
        }
    }

    private void storeQnResponse(Integer qnType) {
        switch (qnType) {
            case (1):
                //Toast.makeText(requireContext(), "SCQ Response is: " + SCQAnsFragment.selectedAns.toString(), Toast.LENGTH_SHORT).show();
                householdCreateViewModel.allResponses.add(SCQAnsFragment.selectedAns.getAnswerString());
                break;
            case (2):
                //Toast.makeText(requireContext(), "MCQ Response is: " + MCQAnsFragment.getSelectedAns().toString(), Toast.LENGTH_SHORT).show();
                householdCreateViewModel.allResponses.add(MCQAnsFragment.getSelectedAns().toString());
                break;
            case (3):
                //Toast.makeText(requireContext(), "TextQn Response is: " + TextAnsFragment.getAnswer(), Toast.LENGTH_SHORT).show();
                householdCreateViewModel.allResponses.add(TextAnsFragment.responseString);
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
        householdCreateViewModel.setGpsLatitude(String.valueOf(location.getLatitude()));
        householdCreateViewModel.setGpsLongitude(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(requireActivity(), "Please Enable GPS", Toast.LENGTH_SHORT).show();
        getGPSCoordinates();
    }
}
