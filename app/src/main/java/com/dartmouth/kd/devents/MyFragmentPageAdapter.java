package com.dartmouth.kd.devents;

/**
 * Based on MyRuns Functionality
 * Created by kathrynflattum on 2/25/18.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    //create a constructor
    //pass fragment manager, pass arraylist that is specific to program
    public MyFragmentPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int pos){

        return fragments.get(pos);
    }

    @Override
    //counts the number of fragments stored in array list
    public int getCount(){
        return fragments.size();
    }

    @Override
    //this specifies the order of the pages
    //char sequence is base class of string, returns string of title
    public CharSequence getPageTitle(int position){
        if(position == 0)
            return "Profile";
        else if(position == 1)
            return "Create";
        else if(position == 2)
            return "Calendar";
        else if(position == 3)
            return "Map";
        else
            return null;
    }
}

