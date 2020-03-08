package com.example.mobileApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobileApp.viewmodel.HouseholdCreateViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HouseholdCreateFinishFragment extends Fragment {

    @BindView(R.id.bn_hh_finish) Button bnFinish;

    private HouseholdCreateViewModel householdCreateViewModel;

    public HouseholdCreateFinishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_household_create_finish, container, false);
        ButterKnife.bind(this, view);

        initViewModel();

        return view;
    }

    private void initViewModel() {
        householdCreateViewModel = new ViewModelProvider(requireActivity()).get(HouseholdCreateViewModel.class);
    }

    @OnClick(R.id.bn_hh_finish) void onClickFinish() {
        householdCreateViewModel.storeResponsesToDb();

        // go back to Household Home page
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new HouseholdHomeFragment()).commit();
    }
}
