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

import com.example.mobileApp.datatype.PatientRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.PatientRecyclerAdapter;
import com.example.mobileApp.viewmodel.SingleHouseholdViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.PATIENT_BASIC_INFORMATION_QUESTIONNAIRE;

/**
 * The SingleHouseholdFragment class initialises and adds functions for views defined in fragment_single_household.xml.
 * It follows the recommended Android Architecture: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: SingleHouseholdFragment, /viewmodel/SingleHouseholdViewModel, /database/MobileAppRepository, and database package.
 *
 * Functions:
 *  1. It loads a home page for a single household, which includes household info, a list of patient in that household,
 *     and a button to add new patients.
 *  2. It supports the search view for searching a particular patient.
 *
 * (this class shares a similar logic with SinglePatientFragment class)
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class SingleHouseholdFragment extends Fragment {

    /* views */
    @BindView(R.id.txt_single_hh_loc) TextView txtHouseholdLocation;
    @BindView(R.id.txt_single_hh_informant) TextView txtHouseholdInformant;
    @BindView(R.id.recycler_view_patients) RecyclerView patientsRecyclerView;
    @BindView(R.id.bn_hh_add_patient) Button bnAddPatient;

    private SingleHouseholdViewModel singleHouseholdViewModel;

    // the adapter for the RecyclerView of patients in this household
    private PatientRecyclerAdapter adapter;

    public SingleHouseholdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_household, container, false);

        ButterKnife.bind(this, view);

        // refer to the current options menu (toolbar) - used to set the SearchView later
        setHasOptionsMenu(true);

        requireActivity().setTitle("Household: " + Constants.getCurrentHouseholdID());

        // reset TextViews to be blank
        txtHouseholdLocation.setText("");
        txtHouseholdInformant.setText("");

        initViewModel();

        loadHouseholdInfo();

        initRecyclerView();

        return view;
    }

    private void initViewModel() {
        singleHouseholdViewModel = new ViewModelProvider(requireActivity()).get(SingleHouseholdViewModel.class);
    }

    private void loadHouseholdInfo() {
        // load the current household into SingleHouseholdViewModel's currentHousehold variable
        singleHouseholdViewModel.loadCurrentHousehold();

        txtHouseholdLocation.setText(singleHouseholdViewModel.getHouseholdLocation());
        txtHouseholdInformant.setText(singleHouseholdViewModel.getHouseholdInformant());
    }

    private void initRecyclerView() {
        List<PatientRecyclerViewItem> patientItems = singleHouseholdViewModel.loadPatients();

        // setHasFixedSize = true means the RecyclerView won't change size no matter how many items it may contain.
        patientsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new PatientRecyclerAdapter(patientItems);

        patientsRecyclerView.setLayoutManager(layoutManager);
        patientsRecyclerView.setAdapter(adapter);

        // react to clicking a patient card from the list
        adapter.setOnItemClickListener(position -> {
            Constants.setCurrentPatientID(patientItems.get(position).getPatientID());

            // go to the Assessment Centre for the selected patient
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new SinglePatientFragment()).commit();
        });
    }

    /**
     * This method is called to set the SearchView to be visible in options menu (toolbar) when displaying SingleHouseholdFragment.
     * @param menu The tool bar menu defined in /res/menu/toolbar_menu.xml
     */
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setVisible(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQueryHint("Search patient id or name...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // leave blank because we want to show the querying results in real-time
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // dynamically pass the SearchView text into the PatientRecyclerAdapter's filter
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }

    @OnClick(R.id.bn_hh_add_patient) void onClickAddPatient() {
        Constants.setCurrentQuestionnaireID(PATIENT_BASIC_INFORMATION_QUESTIONNAIRE);
        Constants.setQnnExists(false);  // this is a new assessment (not an unfinished one)

        // Go to take the Patient Basic Information Questionnaire for this patient
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new UserCreateFragment()).commit();
    }
}
