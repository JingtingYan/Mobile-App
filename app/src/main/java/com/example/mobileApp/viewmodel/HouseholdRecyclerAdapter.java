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
import com.example.mobileApp.datatype.HouseholdRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The HouseholdRecyclerAdapter defines a customised Adapter for RecyclerView used in SearchHouseholdFragment.
 * (it shares a similar logic with PatientRecyclerAdapter and AssessmentRecyclerAdapter)
 *
 * Functions:
 *  1. It defines HouseholdViewHolder (a customised ViewHolder for RecyclerView) that binds the views defined in
 *     layout_recycler_item_household.xml with a HouseholdRecyclerViewItem object.
 *  2. It loads a list of HouseholdRecyclerViewItem objects as the data source for the RecyclerView.
 *  3. It defines an OnItemClickListener interface to deal with item clicked event on HouseholdRecyclerAdapter.
 *     This interface will pass the click to SearchHouseholdFragment.
 *  4. It customises a Filter object to support the SearchView in SearchHouseholdFragment.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class HouseholdRecyclerAdapter extends RecyclerView.Adapter<HouseholdRecyclerAdapter.HouseholdViewHolder> implements Filterable {

    private List<HouseholdRecyclerViewItem> items;
    private List<HouseholdRecyclerViewItem> itemsFull;  // a full copy of 'items' that will be used in the searching function

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class HouseholdViewHolder extends RecyclerView.ViewHolder {

        // views
        TextView householdID, householdLoc, householdInformant;

        /**
         * The is the constructor for HouseholdViewHolder which binds the views and registers the OnItemClickListener.
         * @param itemView The CardView defined for a single HouseholdRecyclerView item.
         * @param listener The class-scope OnItemClickListener variable 'listener'.
         */
        HouseholdViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            householdID = itemView.findViewById(R.id.recycler_item_hh_id);
            householdLoc = itemView.findViewById(R.id.recycler_item_hh_loc);
            householdInformant = itemView.findViewById(R.id.recycler_item_hh_informant);

            // pass the click to SearchHouseholdFragment
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

    /**
     * This is the constructor for HouseholdRecyclerAdapter.
     * @param items The data source for this adapter. A list of HouseholdRecyclerViewItem objects.
     */
    public HouseholdRecyclerAdapter(List<HouseholdRecyclerViewItem> items) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items);    // create an independent copy of 'items'
    }

    /**
     * This method is used to create a new HouseholdViewHolder and pass the layout used by it.
     * It is called because the RecyclerView needs a HouseholdViewHolder to represent its recycler view item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new HouseholdViewHolder that holds the item's layout and an OnItemClickListener.
     */
    @NonNull
    @Override
    public HouseholdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item_household, parent, false);
        return new HouseholdViewHolder(view, listener);
    }

    /**
     * This method is used to pass information inside the views.
     * @param holder Contains all of the information about the views.
     * @param position The position each item in the recycler view. It matches with the position in the ArrayList 'item'.
     */
    @Override
    public void onBindViewHolder(@NonNull HouseholdViewHolder holder, int position) {
        HouseholdRecyclerViewItem item = items.get(position);
        // pass the info stored in each HouseholdRecyclerViewItem to a HouseholdViewHolder
        holder.householdID.setText(item.getHouseholdID());
        holder.householdLoc.setText(item.getHouseholdLocation());
        holder.householdInformant.setText(item.getHouseholdKeyInformant());
    }

    /**
     * This method gets how many items will be inside the RecyclerView
     * @return The size of ArrayList 'item' that is used to create this HouseholdRecyclerView
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * This method gets a customised filter used by HouseholdRecyclerAdapter.
     * @return A filter that can be used to constrain data with a filtering pattern.
     */
    @Override
    public Filter getFilter() {
        return householdFilter;
    }

    private Filter householdFilter = new Filter() {
        /**
         * This method will be automatically run at the background thread.
         * @param constraint The input in the search view field
         * @return A FilterResults object, the values of which is the filtered HouseholdRecyclerViewItem's
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // 'filteredItems' only contains the filtered HouseholdRecyclerViewItem
            List<HouseholdRecyclerViewItem> filteredItems = new ArrayList<>();

            // if the search view is empty, display the entire items list
            if (constraint == null || constraint.length() == 0) {
                filteredItems.addAll(itemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HouseholdRecyclerViewItem item : itemsFull) {
                    if (item.getHouseholdInfoToFilter().toLowerCase().contains(filterPattern)) {
                        filteredItems.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredItems;
            return results;
        }

        /**
         * This method is called to publish the filtering results in the user interface.
         * @param constraint The constraint used to filter the data.
         * @param results The results of the filtering operation.
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update the 'items' list to contain only the filtered items
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
