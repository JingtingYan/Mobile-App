package com.example.mobileApp;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.txt_login_username) EditText txtUsername;
    @BindView(R.id.txt_login_password) EditText txtPassword;
    @BindView(R.id.bn_login) Button bnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);    // debugging
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bn_login) void onClick() {
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
                    // successfully logged in - go to Location page
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
