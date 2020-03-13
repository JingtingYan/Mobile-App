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

public class HouseholdRecyclerAdapter extends RecyclerView.Adapter<HouseholdRecyclerAdapter.HouseholdViewHolder> implements Filterable {

    private List<HouseholdRecyclerViewItem> items;
    private List<HouseholdRecyclerViewItem> itemsFull;  // a full copy of 'items' that will be used in the searching function

    private OnItemClickListener listener;

    // Define an interface to deal with item clicked event on HouseholdRecyclerView
    // This interface will pass the click to HouseholdSearchFragment
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // create a view holder for this adapter
    static class HouseholdViewHolder extends RecyclerView.ViewHolder {

        // variables for views defined in layout_recycler_hh_item.xml
        TextView householdID, householdLoc, householdInformant;

        // itemView is the defined CardView for a single HouseholdRecyclerView item
        HouseholdViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            // views variables
            householdID = itemView.findViewById(R.id.recycler_item_hh_id);
            householdLoc = itemView.findViewById(R.id.recycler_item_hh_loc);
            householdInformant = itemView.findViewById(R.id.recycler_item_hh_informant);

            // pass the click to HouseholdSearchFragment
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

    // Constructor for HouseholdRecyclerAdapter.
    // Need to pass in the list of recycler items that want to be displayed.
    public HouseholdRecyclerAdapter(List<HouseholdRecyclerViewItem> items) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items);    // create an independent copy of 'items'
    }

    /**
     * This method is used to pass the layout for the views (the recycler item).
     * The recycler item's layout is defined in /res/layout/layout_recycler_hh_item.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public HouseholdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_hh_item, parent, false);
        return new HouseholdViewHolder(view, listener);
    }

    /**
     * This method is used to pass information inside the views.
     * @param holder Contains all of the information about the views.
     * @param position The position each item in the recycler view.
     *                 This position matches with that in the ArrayList 'item'.
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
     * This method defined how many items will be inside the RecyclerView
     * @return The size of ArrayList 'item' that is used to create this HouseholdRecyclerView
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

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

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update the 'items' list to contain only the filtered items
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
