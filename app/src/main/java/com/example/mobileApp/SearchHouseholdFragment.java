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

import com.example.mobileApp.datatype.HouseholdRecyclerViewItem;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.viewmodel.HouseholdRecyclerAdapter;
import com.example.mobileApp.viewmodel.SearchHouseholdViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The SearchHouseholdFragment class initialises and adds functions for views defined in fragment_search_household.xml.
 * It follows the recommended Android Architecture: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: SearchHouseholdFragment, /viewmodel/SearchHouseholdViewModel, /database/MobileAppRepository, and database package.
 *
 * Functions:
 *  1. It loads a list of existing households in a cluster
 *  2. It supports the search view for searching a particular household.
 *
 * (this class shares a similar logic with SearchPatientFragment class)
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class SearchHouseholdFragment extends Fragment {

    /* view */
    @BindView(R.id.recycler_view_hh) RecyclerView householdRecyclerView;

    private SearchHouseholdViewModel searchHouseholdViewModel;

    // the adapter for the RecyclerView of households in the cluster
    private HouseholdRecyclerAdapter adapter;

    public SearchHouseholdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_household, container, false);

        ButterKnife.bind(this, view);

        // refer to the current options menu (toolbar) - used to set the SearchView later
        setHasOptionsMenu(true);

        requireActivity().setTitle(R.string.title_choose_hh);

        initViewModel();

        initRecyclerView();

        return view;
    }

    private void initViewModel() {
        searchHouseholdViewModel = new ViewModelProvider(requireActivity()).get(SearchHouseholdViewModel.class);
    }

    private void initRecyclerView() {
        List<HouseholdRecyclerViewItem> hhItems = searchHouseholdViewModel.loadHouseholds();

        // setHasFixedSize = true means the RecyclerView won't change size no matter how many items it may contain.
        householdRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter = new HouseholdRecyclerAdapter(hhItems);

        householdRecyclerView.setLayoutManager(layoutManager);
        householdRecyclerView.setAdapter(adapter);

        // react to clicking a household card from the list
        adapter.setOnItemClickListener(position -> {
            Constants.setCurrentHouseholdID(hhItems.get(position).getHouseholdID());

            // go to the home page for the selected household
            HouseholdMainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.household_fragment_container, new SingleHouseholdFragment()).commit();
        });
    }

    /**
     * This method is called to set the SearchView to be visible in options menu (toolbar) when displaying SearchHouseholdFragment.
     * @param menu The tool bar menu defined in /res/menu/toolbar_menu.xml
     */
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setVisible(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQueryHint("Search hh location/informant...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // leave blank because we want to show the querying results at the real-time
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // dynamically pass the SearchView text into the HouseholdRecyclerAdapter's filter
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }
}
