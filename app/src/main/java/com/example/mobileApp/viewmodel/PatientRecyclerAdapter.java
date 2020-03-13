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

        TextView patientID, patientName, patientDOB, patientAssessmentStatus, studyID, householdID;

        PatientViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            patientID = itemView.findViewById(R.id.recycler_item_patient_id);
            patientName = itemView.findViewById(R.id.recycler_item_patient_name);
            patientDOB = itemView.findViewById(R.id.recycler_item_patient_dob);
            patientAssessmentStatus = itemView.findViewById(R.id.recycler_item_patient_status);
            studyID = itemView.findViewById(R.id.recycler_item_patient_study);
            householdID = itemView.findViewById(R.id.recycler_item_patient_hh);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) { // check if the position is valid
                            listener.onItemClick(position);
                        }
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_patient_item, parent, false);
        return new PatientViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        PatientRecyclerViewItem item = items.get(position);

        holder.patientID.setText(item.getPatientID());
        holder.patientName.setText(item.getPatientName());
        holder.patientDOB.setText(item.getPatientDOB());
        holder.patientAssessmentStatus.setText(item.getPatientAssessmentStatus());
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
