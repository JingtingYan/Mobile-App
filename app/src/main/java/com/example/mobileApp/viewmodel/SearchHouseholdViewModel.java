package com.example.mobileApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileApp.datatype.HouseholdRecyclerViewItem;
import com.example.mobileApp.database.MobileAppRepository;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchHouseholdViewModel extends AndroidViewModel {

    private MobileAppRepository repo;

    public SearchHouseholdViewModel(@NonNull Application application) {
        super(application);

        repo = MobileAppRepository.getInstance(application.getApplicationContext());
    }

    public List<HouseholdRecyclerViewItem> loadHouseholds() {
        List<HouseholdRecyclerViewItem> items = new ArrayList<>();

        List<HouseholdTable> householdTables = new ArrayList<>();
        try {
            householdTables.addAll(repo.loadHouseholdsForCluster(Constants.getCluster().getLocationID()));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        for (HouseholdTable householdTable : householdTables) {
            HouseholdRecyclerViewItem item = new HouseholdRecyclerViewItem(householdTable.getHousehold_id(),
                        householdTable.getVillage_street_name(), householdTable.getKey_informer());
            items.add(item);
        }
        return items;
    }
}
