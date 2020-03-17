package com.example.mobileApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.QuestionnaireViewModel;

import java.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.MOBILITY_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.PATIENT_BASIC_INFORMATION_QUESTIONNAIRE;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionnaireFragment extends Fragment {

    @BindView(R.id.txt_qn_instruction) TextView txtQnInstruction;
    @BindView(R.id.txt_qn_string) TextView txtQnString;
    @BindView(R.id.bn_qnn_next) Button bnNext;
    @BindView(R.id.bn_qnn_exit) Button bnExit;

    private QuestionnaireViewModel questionnaireViewModel;
    private static FragmentManager answerFragmentManager;

    public QuestionnaireFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionnaire, container, false);

        ButterKnife.bind(this, view);

        setActivityTitle();

        answerFragmentManager = getChildFragmentManager();

        initViewModel();

//        initData();

        Log.i("Questionnaire Fragment", "this qnn is existed?" + Constants.isQnnExists());  // debug

        loadFirstQuestion();

        return view;
    }

    private void setActivityTitle() {
        switch (Constants.getCurrentQuestionnaireID()) {
            case (GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID):
                requireActivity().setTitle(R.string.title_qnn_washington);
                break;

            case (MOBILITY_QUESTIONNAIRE_ID):
                requireActivity().setTitle(R.string.title_qnn_mobility);
                break;

            case (PATIENT_BASIC_INFORMATION_QUESTIONNAIRE):
                requireActivity().setTitle(R.string.title_create_patient);
                break;

            default:
                requireActivity().setTitle("Questionnaire/Assessment");
                break;
        }
    }

    private void initViewModel() {
        questionnaireViewModel = new ViewModelProvider(requireActivity()).get(QuestionnaireViewModel.class);

        questionnaireViewModel.qnInstruction.observe(getViewLifecycleOwner(), this::updateQnInstruction);
        questionnaireViewModel.qnString.observe(getViewLifecycleOwner(), this::updateQnString);
    }

//    // update relevant data fields in Constants that need to be stored in db later
//    private void initData() {
//        Constants.setCurrentQuestionnaireID(GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID);
//        Constants.setWashingtonQuestionnaireStartDate(LocalDate.now().toString());
//    }

    private void updateQnInstruction(String qnInstruction) {
        txtQnInstruction.setText(qnInstruction);
    }

    private void updateQnString(String qnString) {
        txtQnString.setText(qnString);
    }

    private void loadFirstQuestion() {
        if (Constants.isQnnExists()) {
            questionnaireViewModel.loadLastAnsweredQuestion();
        } else {
            questionnaireViewModel.loadFirstQuestion();
        }
        displayQuestion(questionnaireViewModel.getQnType());
    }

    @OnClick(R.id.bn_qnn_next)
    void onClickNext() {
        storeQnResponse(questionnaireViewModel.getQnType());

        if (questionnaireViewModel.hasNextQuestion()) {
            loadNextQuestion();
        } else {
            Log.i("qnn fragment - onClickNext", "reached the last qn in qnn");    // debug
            // move to the end page of Questionnaire/Assessment
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
        // store directly to Response table
        switch (qnType) {
            case (1):
                Toast.makeText(requireContext(), "SCQ Response is: " + SCQAnsFragment.selectedAns.toString(), Toast.LENGTH_SHORT).show();
                questionnaireViewModel.storeSCQResponse(SCQAnsFragment.selectedAns);
                break;
            case (2):
                Toast.makeText(requireContext(), "MCQ Response is: " + MCQAnsFragment.getSelectedAns().toString(), Toast.LENGTH_SHORT).show();
                questionnaireViewModel.storeMCQResponse(MCQAnsFragment.getSelectedAns());
                break;
            case (3):
                Toast.makeText(requireContext(), "TextQn Response is: " + TextAnsFragment.responseString, Toast.LENGTH_SHORT).show();
                questionnaireViewModel.storeTextQnResponse(TextAnsFragment.responseString);
                TextAnsFragment.responseString = "";    // reset
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.bn_qnn_exit) void onClickExit() {
        if (Constants.isQnnExists()) {
            questionnaireViewModel.updateExistingAssessmentToIncomplete(Constants.getSelectedAssessment().getStartDate());
        } else {
            questionnaireViewModel.updateNewAssessmentToIncomplete();
        }
        // move to the Patient home page
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new SinglePatientFragment()).commit();
    }
}
