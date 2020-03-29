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

import com.example.mobileApp.datatype.PatientRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.PatientRecyclerAdapter;
import com.example.mobileApp.viewmodel.SearchPatientViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The SearchPatientFragment class initialises and adds functions for views defined in fragment_search_patient.xml.
 * It follows the recommended Android Architecture: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: SearchPatientFragment, /viewmodel/SearchPatientViewModel, /database/MobileAppRepository, and database package.
 *
 * Functions:
 *  1. It loads a list of existing patients in a cluster
 *  2. It supports the search view for searching a particular patient.
 *
 * (this class shares a similar logic with SearchHouseholdFragment class)
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class SearchPatientFragment extends Fragment {

    /* view */
    @BindView(R.id.recycler_view_all_patients) RecyclerView patientsRecyclerView;

    private SearchPatientViewModel searchPatientViewModel;

    // the adapter for the RecyclerView of patients in the cluster
    private PatientRecyclerAdapter adapter;

    public SearchPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_patient, container, false);

        ButterKnife.bind(this, view);

        // refer to the current options menu (toolbar) - used to set the SearchView later
        setHasOptionsMenu(true);

        requireActivity().setTitle(R.string.title_choose_patient);

        initViewModel();

        initRecyclerView();

        return view;
    }

    private void initViewModel() {
        searchPatientViewModel = new ViewModelProvider(requireActivity()).get(SearchPatientViewModel.class);
    }

    private void initRecyclerView() {
        List<PatientRecyclerViewItem> patientItems = searchPatientViewModel.loadAllPatients();

        patientsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new PatientRecyclerAdapter(patientItems);

        patientsRecyclerView.setLayoutManager(layoutManager);
        patientsRecyclerView.setAdapter(adapter);

        // react to clicking a patient card from the list
        adapter.setOnItemClickListener(position -> {
            Constants.setCurrentPatientID(patientItems.get(position).getPatientID());
            Constants.setCurrentHouseholdID(patientItems.get(position).getHouseholdID());

            // go to the Assessment Centre for the selected patient
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new SinglePatientFragment()).commit();
        });
    }

    /**
     * This method is called to set the SearchView to be visible in options menu (toolbar) when displaying SearchPatientFragment.
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
                // leave blank because we want to show the querying results at the real-time
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
}
