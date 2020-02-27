package com.example.mobileApp;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.utilities.MySingleton;
import com.example.mobileApp.viewmodel.DataSyncViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Button;
import android.widget.Toast;

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

public class DataSyncActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_data_sync) Toolbar toolbar;
    @BindView(R.id.bn_data_sync_download_data) Button bnDownload;
    @BindView(R.id.bn_data_sync_upload_data) Button bnUpload;
    @BindView(R.id.bn_data_sync_delete_data) Button bnDelete;
    @BindView(R.id.bn_data_sync_next) Button bnNext;

    private DataSyncViewModel dataSyncViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sync);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initViewModel();
    }

    private void initViewModel() {
        dataSyncViewModel = new ViewModelProvider(this).get(DataSyncViewModel.class);
    }

    @OnClick(R.id.bn_data_sync_download_data) void onClickDownload() {
        syncLocationData();
        syncQuestionnaireData();
        syncQuestionData();
        syncAnswerData();
        syncQAData();
        syncLogicData();
        syncQuestionRelationData();
    }

    @OnClick(R.id.bn_data_sync_upload_data) void onClickUpload() {

    }

    @OnClick(R.id.bn_data_sync_delete_data) void onClickDelete() {
        deleteLocationData();
        deleteQuestionnaireData();
        deleteQuestionData();
        deleteAnswerData();
        deleteQAData();
        deleteLogicData();
        deleteQuestionRelationData();
    }

    @OnClick(R.id.bn_data_sync_next) void onClickNext() {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    private void syncLocationData() {
        StringRequest locationRequest = new StringRequest(Request.Method.GET, GET_LOCATION_URL, this::addLocationData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(locationRequest);
    }

    private void syncQuestionnaireData() {
        StringRequest questionnaireRequest = new StringRequest(Request.Method.GET, GET_QUESTIONNAIRE_URL, this::addQuestionnaireData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(questionnaireRequest);
    }

    private void syncQuestionData() {
        StringRequest questionRequest = new StringRequest(Request.Method.GET, GET_QUESTION_URL, this::addQuestionData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(questionRequest);
    }

    private void syncAnswerData() {
        StringRequest answerRequest = new StringRequest(Request.Method.GET, GET_ANSWER_URL, this::addAnswerData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(answerRequest);
    }

    private void syncQAData() {
        StringRequest qaRequest = new StringRequest(Request.Method.GET, GET_QA_URL, this::addQAData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(qaRequest);
    }

    private void syncLogicData() {
        StringRequest logicRequest = new StringRequest(Request.Method.GET, GET_LOGIC_URL, this::addLogicData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(logicRequest);
    }

    private void syncQuestionRelationData() {
        StringRequest qnRelRequest = new StringRequest(Request.Method.GET, GET_QUESTION_RELATION_URL, this::addQnRelData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getToken());
                return headers;
            }
        };
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(qnRelRequest);
    }

    private void addLocationData(String jsonArray) {
        dataSyncViewModel.addLocationData(jsonArray);
    }

    private void addQuestionnaireData(String jsonArray) {
        dataSyncViewModel.addQuestionnaireData(jsonArray);
    }

    private void addQuestionData(String jsonArray) {
        dataSyncViewModel.addQuestionData(jsonArray);
    }

    private void addAnswerData(String jsonArray) {
        dataSyncViewModel.addAnswerData(jsonArray);
    }

    private void addQAData(String jsonArray) {
        dataSyncViewModel.addQAData(jsonArray);
    }

    private void addLogicData(String jsonArray) {
        dataSyncViewModel.addLogicData(jsonArray);
    }

    private void addQnRelData(String jsonArray) {
        dataSyncViewModel.addQuestionRelationData(jsonArray);
    }

    private void deleteLocationData() {
        dataSyncViewModel.deleteLocationData();
    }

    private void deleteQuestionnaireData() {
        dataSyncViewModel.deleteQuestionnaireData();
    }

    private void deleteQuestionData() {
        dataSyncViewModel.deleteQuestionData();
    }

    private void deleteAnswerData() {
        dataSyncViewModel.deleteAnswerData();
    }

    private void deleteQAData() {
        dataSyncViewModel.deleteQAData();
    }

    private void deleteLogicData() {
        dataSyncViewModel.deleteLogicData();
    }

    private void deleteQuestionRelationData() {
        dataSyncViewModel.deleteQuestionRelationData();
    }
}
