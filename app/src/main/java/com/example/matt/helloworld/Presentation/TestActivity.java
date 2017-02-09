package com.example.matt.helloworld.Presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matt.helloworld.Persistence.MyRecylerAdapter;
import com.example.matt.helloworld.R;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by User on 12/15/2016.
 */

public class TestActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        populateDataset(myDataset);
       // mAdapter = new MyRecylerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private static void populateDataset(ArrayList<String> dataset) {

        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");
        dataset.add("Anthony");
        dataset.add("Matt");
        dataset.add("Anna");
        dataset.add("Joe");
        dataset.add("Alundra");
        dataset.add("Natasha");



    }
}