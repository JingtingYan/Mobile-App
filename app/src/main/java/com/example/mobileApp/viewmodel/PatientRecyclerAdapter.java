package com.example.mobileApp.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileApp.R;
import com.example.mobileApp.datatype.PatientRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The PatientRecyclerAdapter defines a customised Adapter for RecyclerView used in SearchPatientFragment and SingleHouseholdFragment.
 * (it shares a similar logic with HouseholdRecyclerAdapter and AssessmentRecyclerAdapter)
 *
 * Functions:
 *  1. It defines PatientViewHolder (a customised ViewHolder for RecyclerView) that binds the views defined in
 *     layout_recycler_item_patient.xml with a PatientRecyclerViewItem object.
 *  2. It loads a list of PatientRecyclerViewItem objects as the data source for the RecyclerView.
 *  3. It defines an OnItemClickListener interface to deal with item clicked event on PatientRecyclerAdapter.
 *     This interface will pass the click to SearchPatientFragment and SingleHouseholdFragment.
 *  4. It customises a Filter object to support the SearchView in SearchPatientFragment and SingleHouseholdFragment.
 *
 *  Please refer to the code comments in HouseholdRecyclerAdapter class.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class PatientRecyclerAdapter extends RecyclerView.Adapter<PatientRecyclerAdapter.PatientViewHolder> implements Filterable {

    private List<PatientRecyclerViewItem> items;
    private List<PatientRecyclerViewItem> itemsFull;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class PatientViewHolder extends RecyclerView.ViewHolder {

        TextView patientID, patientName, patientDOB, studyID, householdID;

        PatientViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            patientID = itemView.findViewById(R.id.recycler_item_patient_id);
            patientName = itemView.findViewById(R.id.recycler_item_patient_name);
            patientDOB = itemView.findViewById(R.id.recycler_item_patient_dob);
            studyID = itemView.findViewById(R.id.recycler_item_patient_study);
            householdID = itemView.findViewById(R.id.recycler_item_patient_hh);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) { // check if the position is valid
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public PatientRecyclerAdapter(List<PatientRecyclerViewItem> items) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item_patient, parent, false);
        return new PatientViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        PatientRecyclerViewItem item = items.get(position);

        holder.patientID.setText(item.getPatientID());
        holder.patientName.setText(item.getPatientName());
        holder.patientDOB.setText(item.getPatientDOB());
        holder.studyID.setText(item.getStudyID());
        holder.householdID.setText(item.getHouseholdID());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return patientFilter;
    }

    private Filter patientFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PatientRecyclerViewItem> filteredItems = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredItems.addAll(itemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PatientRecyclerViewItem item : itemsFull) {
                    if (item.getPatientInfoToFilter().toLowerCase().contains(filterPattern)) {
                        filteredItems.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredItems;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
