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

import com.example.mobileApp.datatype.PatientRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.PatientRecyclerAdapter;
import com.example.mobileApp.viewmodel.SingleHouseholdViewModel;

import java.time.LocalDate;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.PATIENT_BASIC_INFORMATION_QUESTIONNAIRE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleHouseholdFragment extends Fragment {

    @BindView(R.id.txt_single_hh_loc) TextView txtHouseholdLocation;
    @BindView(R.id.txt_single_hh_informant) TextView txtHouseholdInformant;
    @BindView(R.id.recycler_view_patients) RecyclerView patientsRecyclerView;
    @BindView(R.id.bn_hh_add_patient) Button bnAddPatient;

    private SingleHouseholdViewModel singleHouseholdViewModel;

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
        singleHouseholdViewModel.loadCurrentHousehold();

        txtHouseholdLocation.setText(singleHouseholdViewModel.getHouseholdLocation());
        txtHouseholdInformant.setText(singleHouseholdViewModel.getHouseholdInformant());
    }

    private void initRecyclerView() {
        List<PatientRecyclerViewItem> patientItems = singleHouseholdViewModel.loadPatients();

        patientsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new PatientRecyclerAdapter(patientItems);

        patientsRecyclerView.setLayoutManager(layoutManager);
        patientsRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Constants.setCurrentPatientID(patientItems.get(position).getPatientID());
            Toast.makeText(requireContext(), "clicked patient id: " + patientItems.get(position).getPatientID(), Toast.LENGTH_SHORT).show();    // debug

            // display patient information for the selected patient
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new SinglePatientFragment()).commit();
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        // set the SearchView to be visible
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setVisible(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQueryHint("Search patient id or name...");

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

    @OnClick(R.id.bn_hh_add_patient) void onClickAddPatient() {
        Constants.setCurrentQuestionnaireID(PATIENT_BASIC_INFORMATION_QUESTIONNAIRE);
        Constants.setQnnExists(false);
        // Go to take the Patient Basic Information Questionnaire for this patient
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new QuestionnaireFragment()).commit();
    }
}
