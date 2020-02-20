package com.example.mobileApp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mobileApp.utilities.Constants;
import com.example.mobileApp.utilities.MySingleton;
import com.example.mobileApp.viewmodel.DataSyncViewModel;

import java.util.HashMap;
import java.util.Map;

import static com.example.mobileApp.utilities.Constants.GET_LOCATION_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataSyncFragment extends Fragment implements View.OnClickListener {

    private Button bnDownload, bnUpload, bnDelete, bnNext;

    private DataSyncViewModel dataSyncViewModel;

    public DataSyncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_sync, container, false);

        bnDownload = view.findViewById(R.id.bn_data_sync_download_data);
        bnUpload = view.findViewById(R.id.bn_data_sync_upload_data);
        bnDelete = view.findViewById(R.id.bn_data_sync_delete_data);
        bnNext = view.findViewById(R.id.bn_data_sync_next);

        bnDownload.setOnClickListener(this);
        bnUpload.setOnClickListener(this);
        bnDelete.setOnClickListener(this);
        bnNext.setOnClickListener(this);

        initViewModel();

        return view;
    }

    private void initViewModel() {
        dataSyncViewModel = new ViewModelProvider(this).get(DataSyncViewModel.class);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bn_data_sync_download_data:
                syncLocationData();
                break;

            case R.id.bn_data_sync_upload_data:
                break;

            case R.id.bn_data_sync_delete_data:
                deleteLocationData();
                break;

            case R.id.bn_data_sync_next:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new LocationFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    private void syncLocationData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_LOCATION_URL, this::addLocationData,
                error -> { Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Token " + Constants.getTOKEN());
                return headers;
            }
        };

        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void deleteLocationData() {
        dataSyncViewModel.deleteLocationData();
    }

    private void addLocationData(String jsonArray) {
        dataSyncViewModel.addLocationData(jsonArray);
    }

}
