package com.example.mobileApp;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class HouseholdCreateFragment extends Fragment {

    @BindView(R.id.txt_qn_instruction) TextView txtQnInstruction;
    @BindView(R.id.txt_qn_string) TextView txtQnString;
    @BindView(R.id.bn_hh_create_next) Button bnNext;

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

        // update relevant data fields that need to be stored in db later
        Constants.setCurrentQuestionnaireID(HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID);
        Constants.setHouseholdRosterQuestionnaireStartDate(LocalDate.now().toString());

        answerFragmentManager = getChildFragmentManager();

        initViewModel();

        loadFirstQuestion();

        return view;
    }

    private void initViewModel() {
        householdCreateViewModel = new ViewModelProvider(requireActivity()).get(HouseholdCreateViewModel.class);

        householdCreateViewModel.qnInstruction.observe(getViewLifecycleOwner(), this::updateQnInstruction);
        householdCreateViewModel.qnString.observe(getViewLifecycleOwner(), this::updateQnString);
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

    @OnClick(R.id.bn_hh_create_next) void onClickNext() {
        storeQnResponse(householdCreateViewModel.getQnType());
        if (householdCreateViewModel.hasNextQuestion()) {
            loadNextQuestion();
        } else {
            Toast.makeText(requireContext(), "this is the last question", Toast.LENGTH_SHORT).show();
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
                householdCreateViewModel.storeSCQResponse(SCQAnsFragment.selectedAns);
                break;
            case (2):
                //Toast.makeText(requireContext(), "MCQ Response is: " + MCQAnsFragment.getSelectedAns().toString(), Toast.LENGTH_SHORT).show();
                householdCreateViewModel.storeMCQResponse(MCQAnsFragment.getSelectedAns());
                break;
            case (3):
                //Toast.makeText(requireContext(), "TextQn Response is: " + TextAnsFragment.getAnswer(), Toast.LENGTH_SHORT).show();
                householdCreateViewModel.storeTextQnResponse(TextAnsFragment.responseString);
                break;
        }
    }
}
