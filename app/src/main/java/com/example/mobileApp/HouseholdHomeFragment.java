package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HouseholdHomeFragment extends Fragment {

    @BindView(R.id.bn_hh_home_create) Button createHousehold;
    @BindView(R.id.bn_hh_home_search_patient) Button searchPatient;
    @BindView(R.id.bn_hh_home_search_hh) Button searchHousehold;

    public HouseholdHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_household_home, container, false);

        ButterKnife.bind(this, view);
        getActivity().setTitle(R.string.title_activity_household_home);

        return view;
    }

    @OnClick(R.id.bn_hh_home_create) void onClickCreate() {
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new HouseholdCreateFragment())
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.bn_hh_home_search_patient) void onClickSearchPatient() {

    }

    @OnClick(R.id.bn_hh_home_search_hh) void onClickSearchHousehold() {
        HouseholdMainActivity.fragmentManager.beginTransaction()
                .replace(R.id.household_fragment_container, new HouseholdSearchFragment())
                .addToBackStack(null)
                .commit();
    }

}
