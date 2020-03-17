package com.example.mobileApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.QuestionnaireViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */

// this Fragment is shared with HearX and PEEK Assessment

public class AssessmentPlaceholderFragment extends Fragment {

    @BindView(R.id.txt_asmt_placeholder) TextView txtPlaceholderPrompt;
    @BindView(R.id.bn_asmt_placeholder_next) Button bnNext;

    private QuestionnaireViewModel questionnaireViewModel;

    public AssessmentPlaceholderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_assessment_placeholder, container, false);

        ButterKnife.bind(this, view);

        setActivityTitle();

        setPrompt();

        initViewModel();

        return view;
    }

    private void setActivityTitle() {
        if (Constants.getCurrentQuestionnaireID() == Constants.HEARING_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_asmt_hearx);
        } else if (Constants.getCurrentQuestionnaireID() == Constants.VISION_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_asmt_peek);
        } else {
            requireActivity().setTitle("Questionnaire/Assessment");
        }
    }

    private void setPrompt() {
        if (Constants.getCurrentQuestionnaireID() == Constants.HEARING_QUESTIONNAIRE_ID) {
            txtPlaceholderPrompt.setText(R.string.asmt_placeholder_hearx_prompt);
        } else if (Constants.getCurrentQuestionnaireID() == Constants.VISION_QUESTIONNAIRE_ID) {
            txtPlaceholderPrompt.setText(R.string.asmt_placeholder_peek_prompt);
        } else {
            txtPlaceholderPrompt.setText(R.string.asmt_placeholder_default_prompt);
        }
    }

    private void initViewModel() {
        questionnaireViewModel = new ViewModelProvider(requireActivity()).get(QuestionnaireViewModel.class);
    }

    @OnClick(R.id.bn_asmt_placeholder_next) void onClickNext() {
        // move to the end page of Questionnaire/Assessment
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new QuestionnaireFinishFragment()).commit();
    }
}
