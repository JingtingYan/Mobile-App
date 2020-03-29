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
import com.example.mobileApp.datatype.AssessmentRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The AssessmentRecyclerAdapter defines a customised Adapter for RecyclerView used in SinglePatientFragment.
 * (it shares a similar logic with HouseholdRecyclerAdapter and PatientRecyclerAdapter)
 *
 * Functions:
 *  1. It defines AssessmentViewHolder (a customised ViewHolder for RecyclerView) that binds the views defined in
 *     layout_recycler_item_assessment_status.xml with an AssessmentRecyclerViewItem object.
 *  2. It loads a list of AssessmentRecyclerViewItem objects as the data source for the RecyclerView.
 *  3. It defines an OnItemClickListener interface to deal with item clicked event on AssessmentRecyclerAdapter.
 *     This interface will pass the click to SinglePatientFragment.
 *  4. It customises a Filter object to support the SearchView in SinglePatientFragment.
 *
 *  Please refer to the code comments in HouseholdRecyclerAdapter class.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class AssessmentRecyclerAdapter extends RecyclerView.Adapter<AssessmentRecyclerAdapter.AssessmentViewHolder> implements Filterable {

    private List<AssessmentRecyclerViewItem> items;
    private List<AssessmentRecyclerViewItem> itemsFull;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class AssessmentViewHolder extends RecyclerView.ViewHolder {

        TextView assessmentName, assessmentStatus, assessmentID, startDate, endDate;

        AssessmentViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            assessmentName = itemView.findViewById(R.id.txt_recycler_item_assessment_name);
            assessmentStatus = itemView.findViewById(R.id.txt_recycler_item_assessment_status);
            assessmentID = itemView.findViewById(R.id.txt_recycler_item_assessment_id);
            startDate = itemView.findViewById(R.id.txt_recycler_item_assessment_start);
            endDate = itemView.findViewById(R.id.txt_recycler_item_assessment_end);

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

    public AssessmentRecyclerAdapter(List<AssessmentRecyclerViewItem> items) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item_assessment_status, parent, false);
        return new AssessmentViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        AssessmentRecyclerViewItem item = items.get(position);

        holder.assessmentName.setText(item.getQuestionnaireName());
        holder.assessmentStatus.setText(item.getStatus());
        holder.assessmentID.setText(String.valueOf(item.getQuestionnaireID()));
        holder.startDate.setText(item.getStartDate());
        holder.endDate.setText(item.getEndDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return assessmentFilter;
    }

    private Filter assessmentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AssessmentRecyclerViewItem> filteredItems = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredItems.addAll(itemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (AssessmentRecyclerViewItem item : itemsFull) {
                    if (item.getAssessmentInfoToFilter().toLowerCase().contains(filterPattern)) {
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
