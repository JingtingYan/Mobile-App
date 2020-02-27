package com.example.mobileApp;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileApp.viewmodel.HouseholdCreateViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseholdCreateFragment extends Fragment {


    private HouseholdCreateViewModel mViewModel;

    public static HouseholdCreateFragment newInstance() {
        return new HouseholdCreateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_household_create, container, false);

        ButterKnife.bind(this, view);

        getActivity().setTitle(R.string.title_create_hh);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HouseholdCreateViewModel.class);
        // TODO: Use the ViewModel
    }

}
