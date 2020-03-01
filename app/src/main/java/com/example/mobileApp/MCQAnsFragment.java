package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.viewmodel.HouseholdCreateViewModel;
import com.example.mobileApp.viewmodel.MCQAnsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MCQAnsFragment extends Fragment {

    @BindView(R.id.mcq_button_list) ListView ansListView;

    private HouseholdCreateViewModel householdCreateViewModel;
    private static MCQAnsAdapter ansAdapter;

    public MCQAnsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mcq_ans, container, false);

        ButterKnife.bind(this, view);

        initViewModel();

        loadAnswerChoice();

        return view;
    }

    private void initViewModel() {
        householdCreateViewModel = new ViewModelProvider(requireActivity()).get(HouseholdCreateViewModel.class);
    }

    private void loadAnswerChoice() {
        ansAdapter = new MCQAnsAdapter(requireContext(), householdCreateViewModel.loadAnswerChoices());
        ansListView.setAdapter(ansAdapter);
    }

    static List<Answer> getSelectedAns() {
        List<Answer> result = new ArrayList<>();

        if (ansAdapter != null) {
            result.addAll(ansAdapter.getCheckedItems());
        }

        return result;
    }
}
