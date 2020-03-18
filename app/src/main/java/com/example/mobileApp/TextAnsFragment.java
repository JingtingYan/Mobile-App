package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextAnsFragment extends Fragment {

    @BindView(R.id.txt_qn_text_entry_ans) EditText txtAns;

    static String responseString;

    public TextAnsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_ans, container, false);

        ButterKnife.bind(this, view);

        txtAns.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no action
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no action
            }

            @Override
            public void afterTextChanged(Editable s) {
                responseString = txtAns.getText().toString();
            }
        });

        return view;
    }
}
