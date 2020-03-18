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

import static com.example.mobileApp.utilities.Constants.GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.HEARING_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.MOBILITY_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.PATIENT_BASIC_INFORMATION_QUESTIONNAIRE;
import static com.example.mobileApp.utilities.Constants.VISION_QUESTIONNAIRE_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionnaireFinishFragment extends Fragment {

    @BindView(R.id.txt_qnn_finish_prompt) TextView txtPrompt;
    @BindView(R.id.bn_qnn_finish) Button bnFinish;

    private QuestionnaireViewModel questionnaireViewModel;

    public QuestionnaireFinishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questionnaire_finish, container, false);

        ButterKnife.bind(this, view);

        setActivityTitle();

        initViewModel();

        setPrompt();

        return view;
    }

    private void setActivityTitle() {
        if (Constants.getCurrentQuestionnaireID() == HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_create_hh);
        } else if (Constants.getCurrentQuestionnaireID() == GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_qnn_washington);
        } else if (Constants.getCurrentQuestionnaireID() == MOBILITY_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_qnn_mobility);
        } else if (Constants.getCurrentQuestionnaireID() == PATIENT_BASIC_INFORMATION_QUESTIONNAIRE) {
            requireActivity().setTitle(R.string.title_create_patient);
        } else if (Constants.getCurrentQuestionnaireID() == VISION_QUESTIONNAIRE_ID)  {
            requireActivity().setTitle(R.string.title_asmt_peek);
        } else if (Constants.getCurrentQuestionnaireID() == HEARING_QUESTIONNAIRE_ID) {
            requireActivity().setTitle(R.string.title_asmt_hearx);
        } else {
            requireActivity().setTitle("Questionnaire/Assessment");
        }
    }

    private void initViewModel() {
        questionnaireViewModel = new ViewModelProvider(requireActivity()).get(QuestionnaireViewModel.class);
    }

    private void setPrompt() {

        if (Constants.getCurrentQuestionnaireID() == HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID) {
            txtPrompt.setText(R.string.hh_finish_prompt);
        } else if (Constants.getCurrentQuestionnaireID() == GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID) {
            txtPrompt.setText(R.string.washington_finish_prompt);
        } else if (Constants.getCurrentQuestionnaireID() == MOBILITY_QUESTIONNAIRE_ID) {
            txtPrompt.setText(R.string.mobility_finish_prompt);
        } else if (Constants.getCurrentQuestionnaireID() == PATIENT_BASIC_INFORMATION_QUESTIONNAIRE) {
            txtPrompt.setText(R.string.patient_finish_prompt);
        } else if (Constants.getCurrentQuestionnaireID() == VISION_QUESTIONNAIRE_ID) {
            txtPrompt.setText(R.string.peek_finish_prompt);
        } else if (Constants.getCurrentQuestionnaireID() == HEARING_QUESTIONNAIRE_ID) {
            txtPrompt.setText(R.string.hearx_finish_prompt);
        } else {
            txtPrompt.setText(R.string.default_finish_prompt);
        }
    }

    @OnClick(R.id.bn_qnn_finish)
    void onClickFinish() {
        // if taking Household Roster Questionnaire, then need to store the responses into Household Table
        if (Constants.getCurrentQuestionnaireID() == HOUSEHOLD_ROSTER_QUESTIONNAIRE_ID) {
            questionnaireViewModel.storeHouseholdResponsesToDb();

            // go back to Household Home page
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new HouseholdHomeFragment()).commit();

        } else if (Constants.getCurrentQuestionnaireID() == PATIENT_BASIC_INFORMATION_QUESTIONNAIRE) {
            questionnaireViewModel.storePatientInfoToDb();

            // go to single Household home page
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new SingleHouseholdFragment()).commit();

        } else {    // otherwise, store the responses into Response table and update Assessment status
            // mark this patient's Assessment Status on the undertaking questionnaire/assessment to be COMPLETE
            if (Constants.isQnnExists()) {
                questionnaireViewModel.updateExistingAssessmentToComplete(Constants.getSelectedAssessment().getStartDate());
            } else {
                questionnaireViewModel.updateNewAssessmentToComplete();
            }
            // go to Patient home page
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new SinglePatientFragment()).commit();
        }
    }
}
