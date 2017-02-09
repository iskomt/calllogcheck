package com.example.matt.helloworld.Presentation.History;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.matt.helloworld.Persistence.MyAdapter;
import com.example.matt.helloworld.Persistence.MyRecylerAdapter;
import com.example.matt.helloworld.R;
import com.example.matt.helloworld.Utilities.CallLogObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CallHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CallHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    public CallHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallHistoryFragment newInstance(String param1, String param2) {
        CallHistoryFragment fragment = new CallHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_call_history, container, false);

        ArrayList<CallLogObject> calllogList = getCallDetails(this.getActivity());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.call_history_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

          if ( ContextCompat.checkSelfPermission( this.getActivity(), Manifest.permission.READ_CALL_LOG ) == PackageManager.PERMISSION_GRANTED ) {
              mAdapter = new MyRecylerAdapter(calllogList);
              mRecyclerView.setAdapter(mAdapter);

          }
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private ArrayList<CallLogObject> getCallDetails(Context context) {

        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<CallLogObject> callLogList = new ArrayList<>();
        Cursor cursor;

        if ( ContextCompat.checkSelfPermission( this.getActivity(), Manifest.permission.READ_CALL_LOG ) == PackageManager.PERMISSION_GRANTED ) {

            cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null, null, null, CallLog.Calls.DATE + " DESC");


            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()) {

                CallLogObject callLogInfo = new CallLogObject();

                callLogInfo.setCallDate(new Date(Long.valueOf(cursor.getString(date))));
                callLogInfo.setCallNumber(cursor.getString(number));
                callLogInfo.setCallDuration(cursor.getString(duration));
                callLogInfo.setCallType(cursor.getInt(type));

                callLogList.add(callLogInfo);

            }
            cursor.close();

        }

        return callLogList;
    }
}
