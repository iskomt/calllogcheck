package com.example.matt.helloworld.Presentation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.matt.helloworld.Presentation.Calls.DailyUsageTab;
import com.example.matt.helloworld.Presentation.Calls.MonthlyUsageTab;
import com.example.matt.helloworld.Presentation.Calls.TabbedFragment;
import com.example.matt.helloworld.Presentation.Calls.WeeklyUsageTab;
import com.example.matt.helloworld.Presentation.History.CallHistoryFragment;
import com.example.matt.helloworld.Presentation.Settings.SettingsFragment;
import com.example.matt.helloworld.R;
import com.example.matt.helloworld.Utilities.CallLogObject;

import java.util.ArrayList;
import java.util.Date;

import static com.example.matt.helloworld.Utilities.CommonUtil.print;


public class MainActivity extends AppCompatActivity implements CallHistoryFragment.OnFragmentInteractionListener,
        DailyUsageTab.OnFragmentInteractionListener, WeeklyUsageTab.OnFragmentInteractionListener,
        MonthlyUsageTab.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener
{


    private DrawerLayout navDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navDrawerListTitles;
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    final int REQUEST_PERMISSION = 123;
    ArrayList<CallLogObject> callLogList;



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_CALL_LOG} , REQUEST_PERMISSION);

        navDrawerListTitles = getResources().getStringArray(R.array.navigation_drawer_list_items);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        navDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, navDrawerListTitles));
        // Set the list's click listener
        navDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                navDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        navDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }





    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position

        if(position == 0) {

            fragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.content_frame, new TabbedFragment()).commit();
        }
        if(position == 1)
        {
            fragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.content_frame, new CallHistoryFragment())
                    .commit();

        }
        if(position ==2) {
                        fragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.content_frame, new SettingsFragment())
                    .commit();


        }


        // Highlight the selected item, update the title, and close the drawer
        navDrawerList.setItemChecked(position, true);
        setTitle(navDrawerListTitles[position]);
        navDrawerLayout.closeDrawer(navDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    private ArrayList<CallLogObject> getCallDetails(Context context) {

        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<CallLogObject> callLogList = new ArrayList<>();
        Cursor cursor;

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.READ_CALL_LOG ) == PackageManager.PERMISSION_GRANTED ) {

            print("Getting call details...");

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








