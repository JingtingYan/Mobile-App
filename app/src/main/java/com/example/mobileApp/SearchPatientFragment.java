package com.example.mobileApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobileApp.datatype.PatientRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.PatientRecyclerAdapter;
import com.example.mobileApp.viewmodel.SearchPatientViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPatientFragment extends Fragment {

    @BindView(R.id.recycler_view_all_patients) RecyclerView patientsRecyclerView;

    private SearchPatientViewModel searchPatientViewModel;

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
        Log.i("search patient fragment - loaded patientItems", patientItems.toString());    // debug

        patientsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new PatientRecyclerAdapter(patientItems);

        patientsRecyclerView.setLayoutManager(layoutManager);
        patientsRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Constants.setCurrentPatientID(patientItems.get(position).getPatientID());
            Constants.setCurrentHouseholdID(patientItems.get(position).getHouseholdID());

            // debug
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
}
