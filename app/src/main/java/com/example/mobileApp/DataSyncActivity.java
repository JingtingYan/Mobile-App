package com.example.mobileApp;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.utilities.MySingleton;
import com.example.mobileApp.viewmodel.DataSyncViewModel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mobileApp.utilities.Constants.GET_ANSWER_URL;
import static com.example.mobileApp.utilities.Constants.GET_LOCATION_URL;
import static com.example.mobileApp.utilities.Constants.GET_LOGIC_URL;
import static com.example.mobileApp.utilities.Constants.GET_QA_URL;
import static com.example.mobileApp.utilities.Constants.GET_QUESTIONNAIRE_URL;
import static com.example.mobileApp.utilities.Constants.GET_QUESTION_RELATION_URL;
import static com.example.mobileApp.utilities.Constants.GET_QUESTION_URL;
import static com.example.mobileApp.utilities.Constants.POST_HOUSEHOLD_URL;
import static com.example.mobileApp.utilities.Constants.POST_RESPONSE_URL;

/**
 * The DataSyncActivity class initialises layout components and adds functions for views in activity_data_sync.xml.
 * It follows the recommended Android Architecture Components: UI Controller - ViewModel - Repository - RoomDatabase.
 * The relevant classes are: DataSyncActivity, /viewmodel/DataSyncViewModel, /database/MobileAppRepository, and database package.
 * Functions:
 *  1. It supports bi-directional (local SQLite <-> server MySQL) data synchronisation.
 *     [The mobile app uses Volley, an Android HTTP library, to format and schedule network requests.]
 *  2. It also extends OverflowMenuActivity class to support customised Toolbar.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class DataSyncActivity extends OverflowMenuActivity {

    /* class-scope variables */
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.bn_data_sync_download_data) Button bnDownload;
    @BindView(R.id.bn_data_sync_upload_data) Button bnUpload;
    @BindView(R.id.bn_data_sync_delete_data) Button bnDelete;
    @BindView(R.id.bn_data_sync_next) Button bnNext;

    @BindView(R.id.txt_data_sync_debug) TextView txtDebug;  // debug

    private DataSyncViewModel dataSyncViewModel;


    /**
     * This method is called when the DataSyncActivity is first created.
     * It creates the activity according to its layout defined in /res/layout/activity_data_sync.xml.
     * It instantiates some class-scope variables (bind by ButterKnife) and associates the activity
     * with a viewmodel instance.
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     *                           This object would be null if the activity has never existed before.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sync);

        // Call ButterKnife to automatically cast and bind the view ID with variables.
        ButterKnife.bind(this);

        // Set a Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);
        // Customise this Activity's title in Toolbar.
        DataSyncActivity.this.setTitle("Data Sync Activity");

        // Add the parent activity (the MainActivity) to DataSyncActivity.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initViewModel();
    }

    /**
     * This method initialises the dataSyncViewModel instance and
     * associates DataSyncActivity with DataSyncViewModel.
     */
    private void initViewModel() {
        dataSyncViewModel = new ViewModelProvider(this).get(DataSyncViewModel.class);
    }

    /**
     * This method is called when the DOWNLOAD DATA button is clicked.
     * It invokes a list of methods to fetch all necessary data from server MySQL database
     * to local SQLite database via APIs.
     * The server entities required to be downloaded are:
     *  - Location Table
     *  - Questionnaire Table
     *  - Question Table
     *  - Answer Table
     *  - Question_and_Answer Table
     *  - Logic Table
     *  - Question Relation Table
     */
    @OnClick(R.id.bn_data_sync_download_data) void onClickDownload() {
        downloadLocationData();
        downloadQuestionnaireData();
        downloadQuestionData();
        downloadAnswerData();
        downloadQAData();
        downloadLogicData();
        downloadQuestionRelationData();
    }

    /**
     * This method is called when the UPLOAD DATA button is clicked.
     * It invokes a list of methods to post all patients data gathered from the mobile app
     * to the server MySQL database via APIs.
     * The local entities required to be uploaded are:
     *  - Response Table
     */
    @OnClick(R.id.bn_data_sync_upload_data) void onClickUpload() {
        //uploadResponseData();
        uploadHouseholdData();
    }

    /**
     * This method is called when the DELETE DATA button is clicked. The aim of the DELETE DATA button
     * is to let users be able to manually clear the entire local SQLite database. The process of data
     * deletion is also automatically handled after successfully uploading local data to server.
     * Therefore, this button just aims to offer users a possibility to do data deletion manually.
     *
     * It invokes a list of method to delete data stored in all local tables.
     * The local entities required to be cleared are:
     *  - Location Table
     *  - Questionnaire Table
     *  - Question Table
     *  - Answer Table
     *  - Question_and_Answer Table
     *  - Logic Table
     *  - Question Relation Table
     *  - Response Table
     */
    @OnClick(R.id.bn_data_sync_delete_data) void onClickDelete() {
        deleteLocationData();
        deleteQuestionnaireData();
        deleteQuestionData();
        deleteAnswerData();
        deleteQAData();
        deleteLogicData();
        deleteQuestionRelationData();
        deleteResponseData();   // need to be modified later
        //deleteHouseholdData();  // need to be removed later
    }

    /**
     * This method is called when the NEXT button is clicked.
     * It proceeds to Location Selection Activity.
     */
    @OnClick(R.id.bn_data_sync_next) void onClickNext() {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }


    /* Below is the list of methods invoked when clicking DOWNLOAD DATA.
       (they all share a similar logic) */

    /**
     * This method is called to download the entire Location table from server database.
     * It contains three-step functions:
     *  1. Create a StringRequest object using Volley.
     *      - The HTTP method is GET
     *      - GET_LOCATION_URL is the API for requesting for server database's Location Table
     *         It is a static final string value stored in /utilities/Constants.GET_LOCATION_URL.
     *      - 'this' refers to the String response received from the server
     *         If successfully get the string response (i.e. the Location Table data string in JSON format),
     *         call the method 'addLocationData' and pass the string response as its argument.
     *         Otherwise, receive a VolleyError object 'error', then prompt the error message on screen.
     *      - add the user token in request's Authorization header
     *  2. Set a tag on this StringRequest object which is convenient to track and cancel this request later.
     *  3. Get a singleton instance of Volley RequestQueue from MySingleton class, and add this request to the RequestQueue.
     *     The RequestQueue manages worker threads for running the network operations, reading from and
     *     writing to the cache, and parsing responses.
     */
    private void downloadLocationData() {
        StringRequest locationDownloadRequest = new StringRequest(Request.Method.GET, GET_LOCATION_URL, this::addLocationData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        locationDownloadRequest.setTag("Download Location Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(locationDownloadRequest);
    }

    /**
     * This method is called to download the entire Questionnaire table from server database.
     * It shares a similar logic with the 'downloadLocationData' method.
     * The GET_QUESTIONNAIRE_URL is the API for requesting for server database's Questionnaire Table.
     * It is a static final string value stored in /utilities/Constants.GET_QUESTIONNAIRE_URL.
     */
    private void downloadQuestionnaireData() {
        StringRequest qnnDownloadRequest = new StringRequest(Request.Method.GET, GET_QUESTIONNAIRE_URL, this::addQuestionnaireData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        qnnDownloadRequest.setTag("Download Questionnaire Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(qnnDownloadRequest);
    }

    /**
     * This method is called to download the entire Question table from server database.
     * It shares a similar logic with the 'downloadLocationData' method.
     * The GET_QUESTION_URL is the API for requesting for server database's Question Table.
     * It is a static final string value stored in /utilities/Constants.GET_QUESTION_URL.
     */
    private void downloadQuestionData() {
        StringRequest qnDownloadRequest = new StringRequest(Request.Method.GET, GET_QUESTION_URL, this::addQuestionData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        qnDownloadRequest.setTag("Download Question Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(qnDownloadRequest);
    }

    /**
     * This method is called to download the entire Answer table from server database.
     * It shares a similar logic with the 'downloadLocationData' method.
     * The GET_ANSWER_URL is the API for requesting for server database's Answer Table.
     * It is a static final string value stored in /utilities/Constants.GET_ANSWER_URL.
     */
    private void downloadAnswerData() {
        StringRequest ansDownloadRequest = new StringRequest(Request.Method.GET, GET_ANSWER_URL, this::addAnswerData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        ansDownloadRequest.setTag("Download Answer Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(ansDownloadRequest);
    }

    /**
     * This method is called to download the entire Question_and_Answer table from server database.
     * It shares a similar logic with the 'downloadLocationData' method.
     * The GET_QA_URL is the API for requesting for server database's Question_and_Answer Table.
     * It is a static final string value stored in /utilities/Constants.GET_QA_URL.
     */
    private void downloadQAData() {
        StringRequest qaDownloadRequest = new StringRequest(Request.Method.GET, GET_QA_URL, this::addQAData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        qaDownloadRequest.setTag("Download QuestionAnswer Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(qaDownloadRequest);
    }

    /**
     * This method is called to download the entire Logic table from server database.
     * It shares a similar logic with the 'downloadLocationData' method.
     * The GET_LOGIC_URL is the API for requesting for server database's Logic Table.
     * It is a static final string value stored in /utilities/Constants.GET_LOGIC_URL.
     */
    private void downloadLogicData() {
        StringRequest logicDownloadRequest = new StringRequest(Request.Method.GET, GET_LOGIC_URL, this::addLogicData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        logicDownloadRequest.setTag("Download Logic Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(logicDownloadRequest);
    }

    /**
     * This method is called to download the entire QuestionRelation table from server database.
     * It shares a similar logic with the 'downloadLocationData' method.
     * The GET_QUESTION_RELATION_URL is the API for requesting for server database's QuestionRelation Table.
     * It is a static final string value stored in /utilities/Constants.GET_QUESTION_RELATION_URL.
     */
    private void downloadQuestionRelationData() {
        StringRequest qnRelDownloadRequest = new StringRequest(Request.Method.GET, GET_QUESTION_RELATION_URL, this::addQnRelData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        qnRelDownloadRequest.setTag("Download QuestionRelation Table from Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(qnRelDownloadRequest);
    }


    /* Below is the list of methods invoked when clicking UPLOAD DATA. */

    /**
     * This method is called to upload the entire local Response table to server database.
     * It contains four-step functions:
     *  1. Get the entire local Response table data and parse them into a JSON object by invoking the
     *     method 'getResponseJSONObject' defined in DataSyncViewModel.
     *  2. Create a JSONObjectRequest request using Volley.
     *      - The HTTP method is POST
     *      - POST_RESPONSE_URL is the API for sending data to server database' Response Table.
     *         It is a static final string value stored in /utilities/Constants.POST_RESPONSE_URL.
     *      - 'this' refers to the JSON response received from the server.
     *         If successfully receive the JSON response (by convention, it contains the confirmation
     *         response from the server - it sends back a list of data that has been received at the
     *         server side), then call the method ...
     *         Otherwise, receive a VolleyError object 'error', then prompt the error message on screen.
     *      - Add the user token in request's Authorization header.
     *  3. Set a tag on this JSONObjectRequest object which is convenient to track and cancel this request later.
     *  4. Get a singleton instance of Volley RequestQueue from MySingleton class, and add this request to the RequestQueue.
     */
    private void uploadResponseData() {
        JSONObject responseJsonObject = dataSyncViewModel.getResponseJSONObject();

        //txtDebug.setText(responseJsonObject.toString());    // debug

        JsonObjectRequest responseUploadRequest = new JsonObjectRequest(Request.Method.POST, POST_RESPONSE_URL, responseJsonObject,
                response -> {
                    Toast.makeText(getApplicationContext(),
                            "Successfully uploaded responses data. " + response, Toast.LENGTH_SHORT).show();
                    //txtDebug.setText(String.valueOf(response));     // debug
                    // delete local Response table
                }, error -> {
                    Toast.makeText(getApplicationContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    txtDebug.setText(String.valueOf(error));    // debug
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Token " + Constants.getToken());
                        return headers;
                    }
                };
        responseUploadRequest.setTag("Upload Response Table Data to Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(responseUploadRequest);
    }

    private void uploadHouseholdData() {
        JSONArray householdJsonArray = dataSyncViewModel.getHouseholdJSONArray();

        JsonArrayRequest householdUploadRequest = new JsonArrayRequest(Request.Method.POST, POST_HOUSEHOLD_URL, householdJsonArray,
                response -> {
                    Toast.makeText(getApplicationContext(), "Successfully uploaded households data.", Toast.LENGTH_SHORT).show();

                    //Log.i("confirmed household response", response.toString());     // debug

                    // delete local Household table
                    try {
                        deleteConfirmedHouseholdData(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    Toast.makeText(getApplicationContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    txtDebug.setText(String.valueOf(error));    // debug
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Token " + Constants.getToken());
                        return headers;
                    }
                };
        householdUploadRequest.setTag("Upload Household Table Data to Server Db");
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(householdUploadRequest);
    }

    private void deleteConfirmedHouseholdData(JSONArray response) throws JSONException {
        dataSyncViewModel.deleteConfirmedHouseholdData(response);
    }


    /* Below is the list of methods invoked when successfully receiving the string response for downloading
       tables from server. The string response contains the table data and is in a format of JSON array.
       This string will then be passed to corresponding method in DataSyncViewModel for parsing and
       adding data into the local SQLite database.
       (The list of methods all share a similar logic) */

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * Location table from server. This method further invokes the 'addLocationData' method defined in
     * DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the Location table data.
     */
    private void addLocationData(String jsonArray) {
        dataSyncViewModel.addLocationData(jsonArray);
    }

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * Questionnaire table from server. This method further invokes the 'addQuestionnaireData' method
     * defined in DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the Questionnaire table data.
     */
    private void addQuestionnaireData(String jsonArray) {
        dataSyncViewModel.addQuestionnaireData(jsonArray);
    }

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * Question table from server. This method further invokes the 'addQuestionData' method defined in
     * DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the Question table data.
     */
    private void addQuestionData(String jsonArray) {
        dataSyncViewModel.addQuestionData(jsonArray);
    }

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * Answer table from server. This method further invokes the 'addAnswerData' method defined in
     * DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the Answer table data.
     */
    private void addAnswerData(String jsonArray) {
        dataSyncViewModel.addAnswerData(jsonArray);
    }

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * Question_and_Answer table from server. This method further invokes the 'addQAData' method
     * defined in DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the Question_and_Answer table data.
     */
    private void addQAData(String jsonArray) {
        dataSyncViewModel.addQAData(jsonArray);
    }

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * Logic table from server. This method further invokes the 'addLogicData' method defined in
     * DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the Logic table data.
     */
    private void addLogicData(String jsonArray) {
        dataSyncViewModel.addLogicData(jsonArray);
    }

    /**
     * This method is called when receiving the response string from the StringRequest for downloading
     * QuestionRelation table from server. This method further invokes the 'addQuestionRelationData'
     * method defined in DataSyncViewModel and passes the response string to its argument.
     *
     * @param jsonArray The response string received from the request. It is of JSON array format and
     *                  contains the QuestionRelation table data.
     */
    private void addQnRelData(String jsonArray) {
        dataSyncViewModel.addQuestionRelationData(jsonArray);
    }


    /* Below is the list of methods invoked when clicking DELETE DATA.
       (they all share a similar logic) */

    /**
     * This method is called to delete all data stored in the local SQLite Location table.
     * It further invokes the 'deleteLocationData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteLocationData() {
        dataSyncViewModel.deleteLocationData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite Questionnaire table.
     * It further invokes the 'deleteQuestionnaireData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteQuestionnaireData() {
        dataSyncViewModel.deleteQuestionnaireData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite Question table.
     * It further invokes the 'deleteQuestionData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteQuestionData() {
        dataSyncViewModel.deleteQuestionData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite Answer table.
     * It further invokes the 'deleteAnswerData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteAnswerData() {
        dataSyncViewModel.deleteAnswerData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite Question_and_Answer table.
     * It further invokes the 'deleteQAData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteQAData() {
        dataSyncViewModel.deleteQAData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite Logic table.
     * It further invokes the 'deleteLogicData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteLogicData() {
        dataSyncViewModel.deleteLogicData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite QuestionRelation table.
     * It further invokes the 'deleteQuestionRelationData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteQuestionRelationData() {
        dataSyncViewModel.deleteQuestionRelationData();
    }

    /**
     * This method is called to delete all data stored in the local SQLite Response table.
     * It further invokes the 'deleteResponseData' method defined in DataSyncViewModel to do deletion.
     */
    private void deleteResponseData() {
        dataSyncViewModel.deleteResponseData();
    }

    private void deleteHouseholdData() {
        dataSyncViewModel.deleteHouseholdData();
    }
}
