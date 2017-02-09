package com.example.matt.helloworld.Persistence;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.matt.helloworld.R;
import com.example.matt.helloworld.Utilities.CallLogObject;

import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter<CallLogObject> implements Filterable {

    ArrayList<CallLogObject> dataListArray;
    private Context context;

    @Override
    public int getCount() {
        return dataListArray.size();
    }

    @Override
    public CallLogObject getItem(int position) {
        return dataListArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public MyAdapter(Context context, ArrayList<CallLogObject> data) {
        super(context, R.layout.call_log_object_list_item, data);
        dataListArray = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.call_log_object_list_item, parent, false);

        CallLogObject item = getItem(position);
        TextView dateTextView = (TextView) theView.findViewById(R.id.date);
        TextView callNumberTextView = (TextView) theView.findViewById(R.id.number);
        TextView callDurationTextView = (TextView) theView.findViewById(R.id.duration);
//        TextView callTypeTextView = (TextView) theView.findViewById(R.id.type);
        dateTextView.setText(String.valueOf("Date: " + item.getCallDate()));
        callNumberTextView.setText("Call from: " + item.getCallNumber());
        callDurationTextView.setText("Duration: " + String.valueOf(item.getCallDuration()) + " seconds");
 //       callTypeTextView.setText("Call Type: " + CallTypeHelper.getCallType(item.getCallType()));

        return theView;
    }

}