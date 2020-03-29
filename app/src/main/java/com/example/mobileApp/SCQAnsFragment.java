package com.example.mobileApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.viewmodel.QuestionnaireViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 * The SCQAnsFragment class initialises and adds functions for views defined in fragment_scq_ans.xml.
 * It follows the recommended Android Architecture: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: SCQAnsFragment, /viewmodel/QuestionnaireViewModel, /database/MobileAppRepository, and database package.
 *
 * Function: It loads a Spinner to hold the answer choices of a single choice question.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class SCQAnsFragment extends Fragment {

    /* view */
    @BindView(R.id.spinner_qn_single_ans) Spinner answerSpinner;

    private QuestionnaireViewModel questionnaireViewModel;
    static Answer selectedAns;  // store patient's selected answer choice

    public SCQAnsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scq_ans, container, false);

        ButterKnife.bind(this, view);

        initViewModel();

        loadAnswerChoice();

        return view;
    }

    private void initViewModel() {
        questionnaireViewModel = new ViewModelProvider(requireActivity()).get(QuestionnaireViewModel.class);
    }

    // display all answer choices in spinner
    private void loadAnswerChoice() {
        ArrayAdapter<Answer> answerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item,
                                                                 questionnaireViewModel.loadAnswerChoices());

        answerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   // set dropdown list layout
        answerSpinner.setAdapter(answerAdapter);
    }

    // react to selecting an answer choice from the list
    @OnItemSelected(R.id.spinner_qn_single_ans) void onAnsSelected(Spinner spinner, int position) {
        selectedAns = (Answer) spinner.getItemAtPosition(position);
    }
}
