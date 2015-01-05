package com.example.anubhavkaushik.makemyband;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public void setNoOfTabs(int noOfTabs) {
        // as no of tabs in application will depend upon whether user is logged in or not so ,this will be set during run time
        this.noOfTabs = noOfTabs;
    }

    int noOfTabs;
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        //constructor
        setNoOfTabs(1);
    }
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                 return  new WorldView();
            case 1:
                  return new FriendsView();

        }
        return null;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return noOfTabs; //No of Tabs
    }
}