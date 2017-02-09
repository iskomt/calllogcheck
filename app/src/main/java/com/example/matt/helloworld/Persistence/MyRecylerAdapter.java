package com.example.matt.helloworld.Persistence;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.matt.helloworld.R;
import com.example.matt.helloworld.Utilities.CallLogObject;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.matt.helloworld.Utilities.CallTypeHelper.getCallType;

public class MyRecylerAdapter extends RecyclerView.Adapter<MyRecylerAdapter.ViewHolder> {
    private ArrayList<CallLogObject> mDataset = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView number;
        public TextView date;
        public TextView duration;
        public ImageView type;
        public ViewHolder(View v) {
            super(v);
            number = (TextView) v.findViewById(R.id.number);
            date = (TextView) v.findViewById(R.id.date);
            duration = (TextView) v.findViewById(R.id.duration);
            type = (ImageView) v.findViewById(R.id.type);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRecylerAdapter(ArrayList<CallLogObject>  myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyRecylerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
       View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.call_log_object_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.duration.setText(mDataset.get(position).getCallDuration() + " seconds");
        holder.date.setText(mDataset.get(position).getCallDate().toString().replaceAll("CST 2016", ""));
        if(mDataset.get(position).getCallType() == 1) {
            holder.type.setImageResource(R.drawable.ic_call_received_black_48dp);
        }
        else if (mDataset.get(position).getCallType() == 2) {
            holder.type.setImageResource(R.drawable.ic_call_made_black_48dp);
        }
        else {
            holder.type.setImageResource(R.drawable.ic_call_missed_black_48dp);
        }

        holder.number.setText(mDataset.get(position).getCallNumber());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
