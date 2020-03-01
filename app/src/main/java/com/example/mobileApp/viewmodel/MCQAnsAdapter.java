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

public class MCQAnsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Answer> answers;
    private SparseBooleanArray sparseBooleanArray;

    public MCQAnsAdapter(Context context, List<Answer> answers) {
        inflater = LayoutInflater.from(context);
        this.answers = answers;
        sparseBooleanArray = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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
