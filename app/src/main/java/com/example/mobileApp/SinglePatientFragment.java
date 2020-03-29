package com.example.mobileApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileApp.datatype.AssessmentRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.AssessmentRecyclerAdapter;
import com.example.mobileApp.viewmodel.SinglePatientViewModel;

import java.time.LocalDate;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.HEARING_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.MOBILITY_QUESTIONNAIRE_ID;
import static com.example.mobileApp.utilities.Constants.VISION_QUESTIONNAIRE_ID;

/**
 * The SinglePatientFragment class initialises and adds functions for views defined in fragment_single_patient.xml.
 * It follows the recommended Android Architecture: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: SinglePatientFragment, /viewmodel/SinglePatientViewModel, /database/MobileAppRepository, and database package.
 *
 * Functions:
 *  1. It loads an Assessment Centre for a single patient, which includes patient info, a list of patient's assessment history,
 *     and buttons to take assessments.
 *  2. It supports the search view for searching a particular assessment history.
 *
 * (this class shares a similar logic with SingleHouseholdFragment class)
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class SinglePatientFragment extends Fragment {

    /* views */
    @BindView(R.id.txt_single_patient_name) TextView txtPatientName;
    @BindView(R.id.txt_single_patient_hh_id) TextView txtHouseholdID;
    @BindView(R.id.txt_single_patient_gender) TextView txtGender;
    @BindView(R.id.txt_single_patient_dob) TextView txtDOB;
    @BindView(R.id.recycler_view_assessments) RecyclerView assessmentRecyclerView;
    @BindView(R.id.bn_washington) Button bnWashington;
    @BindView(R.id.bn_hearx) Button bnHearX;
    @BindView(R.id.bn_peek) Button bnPEEK;
    @BindView(R.id.bn_mobility) Button bnMobility;
    @BindView(R.id.bn_back_to_hh) Button bnBack;

    private SinglePatientViewModel singlePatientViewModel;

    // the adapter for the RecyclerView of patient's previous assessment statuses
    private AssessmentRecyclerAdapter adapter;

    public SinglePatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_patient, container, false);

        ButterKnife.bind(this, view);

        // refer to the current options menu (toolbar) - used to set the SearchView later
        setHasOptionsMenu(true);

        requireActivity().setTitle("Patient: " + Constants.getCurrentPatientID());

        // reset TextViews to be blank
        txtPatientName.setText("");
        txtHouseholdID.setText("");
        txtGender.setText("");
        txtDOB.setText("");

        initViewModel();

        loadAssessmentInfo();

        initRecyclerView();

        return view;
    }

    private void initViewModel() {
        singlePatientViewModel = new ViewModelProvider(requireActivity()).get(SinglePatientViewModel.class);
    }

    private void loadAssessmentInfo() {
        // load the current patient into SinglePatientViewModel's currentPatient variable
        singlePatientViewModel.loadCurrentPatient();

        txtPatientName.setText(singlePatientViewModel.loadPatientName());
        txtHouseholdID.setText(singlePatientViewModel.loadHouseholdID());
        txtGender.setText(singlePatientViewModel.loadPatientGender());
        txtDOB.setText(singlePatientViewModel.loadPatientDOB());
    }

    private void initRecyclerView() {
        List<AssessmentRecyclerViewItem> assessmentItems = singlePatientViewModel.loadAssessmentStatusList();

        // setHasFixedSize = true means the RecyclerView won't change size no matter how many items it may contain.
        assessmentRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new AssessmentRecyclerAdapter(assessmentItems);

        assessmentRecyclerView.setLayoutManager(layoutManager);
        assessmentRecyclerView.setAdapter(adapter);

        // react to clicking an assessment status card from the list
        adapter.setOnItemClickListener(position -> {
            AssessmentRecyclerViewItem selectedItem = assessmentItems.get(position);

            if (selectedItem.getStatus().equals("COMPLETE")) {
                Toast.makeText(requireContext(), "This assessment has been completed.", Toast.LENGTH_SHORT).show();
            }

            if (selectedItem.getStatus().equals("INCOMPLETE")) {
                Constants.setCurrentQuestionnaireID(selectedItem.getQuestionnaireID());
                Constants.setSelectedAssessment(selectedItem);
                Constants.setQnnExists(true);

                // continue with an unfinished assessment
                HouseholdMainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.household_fragment_container, new QuestionnaireFragment()).commit();
            }
        });
    }

    /**
     * This method is called to set the SearchView to be visible in options menu (toolbar) when displaying SinglePatientFragment.
     * @param menu The tool bar menu defined in /res/menu/toolbar_menu.xml
     */
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setVisible(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQueryHint("Search asmt name or date...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // leave blank because we want to show the querying results in real-time
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // dynamically pass the SearchView text into the AssessmentRecyclerAdapter's filter
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }

    @OnClick(R.id.bn_washington) void onClickWashington() {
        Constants.setCurrentQuestionnaireID(GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);  // this is a new assessment (not an unfinished one)

        // Go to take the Washington Group Questionnaire for this patient
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new QuestionnaireFragment()).commit();
    }

    @OnClick(R.id.bn_hearx) void onClickHearX() {
        Constants.setCurrentQuestionnaireID(HEARING_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);

        // Go to the Assessment Placeholder Fragment for HearX
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new AssessmentPlaceholderFragment()).commit();
    }

    @OnClick(R.id.bn_peek) void onClickPEEK() {
        Constants.setCurrentQuestionnaireID(VISION_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);

        // Go to the Assessment Placeholder Fragment for PEEK
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new AssessmentPlaceholderFragment()).commit();
    }

    @OnClick(R.id.bn_mobility) void onClickMobility() {
        Constants.setCurrentQuestionnaireID(MOBILITY_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);

        // Go to take the Mobility Questionnaire for this patient
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new QuestionnaireFragment()).commit();
    }

    @OnClick(R.id.bn_back_to_hh) void onClickBackToHousehold() {
        // Go back to this patient's household page
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new SingleHouseholdFragment()).commit();
    }
}
