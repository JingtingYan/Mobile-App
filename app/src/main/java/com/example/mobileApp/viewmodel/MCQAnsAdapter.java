package com.example.mobileApp.viewmodel;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.mobileApp.R;
import com.example.mobileApp.datatype.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * The MCQAnsAdapter class extends BaseAdapter to define a customised Adapter for ListView used in MCQAnsFragment.
 * It implements a list of abstract methods defined in Adapter.
 * It also includes a SparseBooleanArray object to dynamically check the selected or unselected status for checkboxes.
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class MCQAnsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Answer> answers;
    private SparseBooleanArray sparseBooleanArray;

    public MCQAnsAdapter(Context context, List<Answer> answers) {
        inflater = LayoutInflater.from(context);
        this.answers = answers;
        sparseBooleanArray = new SparseBooleanArray();
    }

    /**
     * This method gets how many items are in the data set represented by this Adapter.
     * @return 	Count of items in 'answers'.
     */
    @Override
    public int getCount() {
        return answers.size();
    }

    /**
     * This method gets the data item associated with the specified position in the data set.
     * @param position The position of the item whose data we want within the adapter's data set.
     * @return The Answer item at the specified position in 'answers'.
     */
    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    /**
     * This method gets the row id associated with the specified position in the list.
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return 	The id of the Answer item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * This method gets a View (inflated by the layout defined in layout_mcq_anslist_item.xml) that displays
     * the Answer item at the specified position in 'answers'.
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the Answer item at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_mcq_anslist_item, parent, false);
            CheckBox checkBox = convertView.findViewById(R.id.cb_mcq_ans);
            checkBox.setTag(position);
            checkBox.setSelected(sparseBooleanArray.get(position));
            checkBox.setText(answers.get(position).toString());
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> sparseBooleanArray.put((Integer) buttonView.getTag(), isChecked));
        }

        return convertView;
    }

    /**
     * This method is called to get all of the selected checkboxes.
     * @return A list of selected Answer items.
     */
    public ArrayList<Answer> getCheckedItems() {
        ArrayList<Answer> result = new ArrayList<>();

        for (int i = 0; i < answers.size(); i++) {
            if (sparseBooleanArray.get(i)) {
                result.add(answers.get(i));
            }
        }

        return result;
    }
}
