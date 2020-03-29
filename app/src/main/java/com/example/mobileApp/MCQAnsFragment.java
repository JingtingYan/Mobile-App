package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mobileApp.datatype.Answer;
import com.example.mobileApp.viewmodel.QuestionnaireViewModel;
import com.example.mobileApp.viewmodel.MCQAnsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The MCQAnsFragment class initialises and adds functions for views defined in fragment_mcq_ans.xml.
 * It follows the recommended Android Architecture: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: MCQAnsFragment, /viewmodel/QuestionnaireViewModel, /database/MobileAppRepository, and database package.
 *
 * Function: It loads a list of CheckBox (in a ListView) to hold the answer choices of a multiple choice question.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class MCQAnsFragment extends Fragment {

    @BindView(R.id.mcq_button_list) ListView ansListView;

    private QuestionnaireViewModel questionnaireViewModel;

    // the adapter for the ListView of CheckBoxes
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
        questionnaireViewModel = new ViewModelProvider(requireActivity()).get(QuestionnaireViewModel.class);
    }

    private void loadAnswerChoice() {
        ansAdapter = new MCQAnsAdapter(requireContext(), questionnaireViewModel.loadAnswerChoices());
        ansListView.setAdapter(ansAdapter);
    }

    // static method called to get patient's selected answer choices
    static List<Answer> getSelectedAns() {
        List<Answer> result = new ArrayList<>();

        if (ansAdapter != null) {
            result.addAll(ansAdapter.getCheckedItems());
        }

        return result;
    }
}
