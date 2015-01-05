package com.example.anubhavkaushik.makemyband;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.example.anubhavkaushik.makemyband.R;
import com.example.anubhavkaushik.makemyband.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class interact extends FragmentActivity {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact);
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        Tab = (ViewPager)findViewById(R.id.pager);

        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getActionBar();
                        actionBar.setSelectedNavigationItem(position);                    }
                });

        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        if (actionBar != null) {
            // populating our action bar
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBar.setTitle("Namaskar");
            actionBar.setIcon(R.drawable.th);
            // this need to be changed , options list of action should be defined in more specific place
            SpinnerAdapter adapter;
            adapter = ArrayAdapter.createFromResource(this,R.array.menu_array, android.R.layout.simple_spinner_dropdown_item);

            // Callback
            ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

                String[] items = getResources().getStringArray(R.array.menu_array); // List items from res

                @Override
                public boolean onNavigationItemSelected(int position, long id) {

                    // Do stuff when navigation item is selected

                    Log.d("NavigationItemSelected", items[position]); // Debug

                    return true;

                }

            };

            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            actionBar.setListNavigationCallbacks(adapter,callback);
        }

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){
            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {

            }
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Tab.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {

            }};
        //Add New Tab

        actionBar.addTab(actionBar.newTab().setText("World View").setTabListener(tabListener));

        //add only when user is logged in
        SharedPreferences pref = getSharedPreferences("statusInfo", Context.MODE_PRIVATE);//0 for private mode
        boolean loggedIn = pref.getBoolean("loggedIn",false);
        Log.i("loggedin", Boolean.toString(loggedIn));
        if(loggedIn) {
            TabAdapter.setNoOfTabs(2);
            actionBar.addTab(actionBar.newTab().setText("Friends View").setTabListener(tabListener));
        }
        Tab.setAdapter(TabAdapter);
    }
}