package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText txtUsername, txtPassword;
    private Button bnLogin;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtUsername = view.findViewById(R.id.txt_login_username);
        txtPassword = view.findViewById(R.id.txt_login_password);
        bnLogin = view.findViewById(R.id.bn_login);

        bnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        // debug
        Toast.makeText(getActivity(), username + "\n" + password, Toast.LENGTH_SHORT).show();

        MainActivity.fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, new LocationFragment())
                                    .addToBackStack(null)
                                    .commit();

        // clear previous input
        txtUsername.setText("");
        txtPassword.setText("");
    }
}
