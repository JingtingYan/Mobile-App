package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.mobileApp.utilities.Constants.LOGIN_URL;


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

        /* POST json object (username & password) to server */
        JSONObject object = new JSONObject();
        try {
            object.put("username", username);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, object,
                response -> {
                    try {
                        Constants.setTOKEN(response.getString("token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // successfully logged in - go to next page
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, new DataSyncFragment())
                            .addToBackStack(null)
                            .commit();
                    // clear previous input
                    txtUsername.setText("");
                    txtPassword.setText("");
                }, error ->
                    Toast.makeText(getActivity(), "Invalid username or password...", Toast.LENGTH_LONG).show());

        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonPostRequest);
    }
}
