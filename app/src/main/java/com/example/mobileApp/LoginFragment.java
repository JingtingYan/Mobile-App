package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText txtUsername, txtPassword;
    private Button bnLogin;
    private TextView result;  // debug
    private static final String postURL = "http://10.0.2.2:8000";


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
        result = view.findViewById(R.id.login_result);  // debug

        bnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        /* POST json object (username & password) to server */
        JSONObject object = new JSONObject();
        try {
            object.put("username", username);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = postURL;
        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, url, object,
           new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                result.setText("POST response: " + response.toString());
                //Toast.makeText(getActivity(), "POST response: " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.setText(String.valueOf(error));
                //Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });


        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonPostRequest);

        /*
        MainActivity.fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, new LocationFragment())
                                    .addToBackStack(null)
                                    .commit();
         */

        // clear previous input
        txtUsername.setText("");
        txtPassword.setText("");
    }
}
