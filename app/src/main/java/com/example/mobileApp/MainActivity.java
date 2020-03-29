package com.example.mobileApp;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.utilities.MySingleton;
import com.facebook.stetho.Stetho;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.LOGIN_URL;

/**
 * The MainActivity class initialises and adds functions for views defined in activity_main.xml.
 *
 * Functions:
 *  1. It supports user login.
 *  2. It extends ToolbarMenuActivity class to load customised toolbar.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class MainActivity extends ToolbarMenuActivity {

    /* views */
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.txt_login_username) EditText txtUsername;
    @BindView(R.id.txt_login_password) EditText txtPassword;
    @BindView(R.id.bn_login) Button bnLogin;

    /**
     * This method is called when the MainActivity is first created.
     * It creates the activity according to its layout defined in /res/layout/activity_main.xml.
     * It instantiates some class-scope variables (bind by ButterKnife).
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     *                           If the activity has never existed before, the value of the Bundle object is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);    // debugging tool to inspect the app's SQLite database
        setContentView(R.layout.activity_main);

        // Call ButterKnife to automatically cast and bind the view ID with variables.
        ButterKnife.bind(this);

        // Set a Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);
        // Customise this Activity's title in Toolbar.
        MainActivity.this.setTitle(R.string.title_activity_main);


        Constants.setCurrentPatientID("31");    // set a temp Patient ID, need to be removed later
    }

    /**
     * This method is called when the LOGIN button is clicked.
     * It has four-step functions:
     *  1. Get the username and password from user input.
     *  2. Add them into a JSON object and send that object to server authentication API.
     *  3. If the user is authenticated, the server sends back a JSON response that contains the
     *     user token. This token value is stored in utilities/Constants.token and will be included
     *     as a authentication header for future API requests.
     *  4. Proceed to DataSyncActivity if successfully authenticated; otherwise, prompt the error.
     */
    @OnClick(R.id.bn_login) void onClick() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        Toast.makeText(this, "Logging in... Please wait...", Toast.LENGTH_LONG).show();

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
                        Constants.setToken(response.getString("token"));
                        // let the enumeratorID the same as user token -- can be changed to other values as well
                        Constants.setEnumeratorID(response.getString("token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // successfully logged in - go to Location Selection page
                    Intent intent = new Intent(this, DataSyncActivity.class);
                    startActivity(intent);

                    // clear previous input
                    txtUsername.setText("");
                    txtPassword.setText("");
                }, error ->
                Toast.makeText(this, String.valueOf(error), Toast.LENGTH_LONG).show());

        MySingleton.getInstance(this).addToRequestQueue(jsonPostRequest);
    }
}
