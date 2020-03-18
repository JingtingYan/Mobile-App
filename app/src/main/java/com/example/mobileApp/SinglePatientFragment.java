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
 * A simple {@link Fragment} subclass.
 */
public class SinglePatientFragment extends Fragment {

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
        singlePatientViewModel.loadCurrentPatient();

        txtPatientName.setText(singlePatientViewModel.loadPatientName());
        txtHouseholdID.setText(singlePatientViewModel.loadHouseholdID());
        txtGender.setText(singlePatientViewModel.loadPatientGender());
        txtDOB.setText(singlePatientViewModel.loadPatientDOB());
    }

    private void initRecyclerView() {
        List<AssessmentRecyclerViewItem> assessmentItems = singlePatientViewModel.loadAssessmentStatusList();

        assessmentRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new AssessmentRecyclerAdapter(assessmentItems);

        assessmentRecyclerView.setLayoutManager(layoutManager);
        assessmentRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            AssessmentRecyclerViewItem selectedItem = assessmentItems.get(position);

            if (selectedItem.getStatus().equals("COMPLETE")) {
                Toast.makeText(requireContext(), "This assessment has been completed.", Toast.LENGTH_SHORT).show();
            }

            if (selectedItem.getStatus().equals("INCOMPLETE")) {
                Constants.setCurrentQuestionnaireID(selectedItem.getQuestionnaireID());
                Constants.setSelectedAssessment(selectedItem);
                Constants.setQnnExists(true);

                HouseholdMainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.household_fragment_container, new QuestionnaireFragment()).commit();
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        // set the SearchView to be visible
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setVisible(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQueryHint("Search asmt name or date...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // leave blank because we want to show the querying results at the real-time
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // dynamically pass the SearchView text into the HouseholdAdapter's filter
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }

    @OnClick(R.id.bn_washington) void onClickWashington() {
        Constants.setCurrentQuestionnaireID(GENERAL_WASHINGTON_GROUP_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);

        // Go to take the Washington Group Questionnaire for this patient
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new QuestionnaireFragment()).commit();
    }

    @OnClick(R.id.bn_hearx) void onClickHearX() {
        Constants.setCurrentQuestionnaireID(HEARING_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);

        // Go to the Placeholder Fragment for HearX
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new AssessmentPlaceholderFragment()).commit();
    }

    @OnClick(R.id.bn_peek) void onClickPEEK() {
        Constants.setCurrentQuestionnaireID(VISION_QUESTIONNAIRE_ID);
        Constants.setCurrentQuestionnaireStartDate(LocalDate.now().toString());
        Constants.setQnnExists(false);

        // Go to the Placeholder Fragment for PEEK
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
