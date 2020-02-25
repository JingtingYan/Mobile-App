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

import static com.example.mobileApp.utilities.Constants.GET_LOCATION_URL;

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
    }

    @OnClick(R.id.bn_data_sync_upload_data) void onClickUpload() {

    }

    @OnClick(R.id.bn_data_sync_delete_data) void onClickDelete() {
        deleteLocationData();
    }

    @OnClick(R.id.bn_data_sync_next) void onClickNext() {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    private void syncLocationData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_LOCATION_URL, this::addLocationData,
                error -> Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getTOKEN());
                return headers;
            }
        };

        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void deleteLocationData() {
        dataSyncViewModel.deleteLocationData();
    }

    private void addLocationData(String jsonArray) {
        dataSyncViewModel.addLocationData(jsonArray);
    }

}
