package com.dartmouth.kd.devents;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class FunctionActivity extends AppCompatActivity {
    //see stuff under the tabs
    ViewPager viewPager;

    //need this for the tabs
     MyDevents myDevents;
    TabLayout tabLayout;
    MapFragment fragmentMap;
    CalendarActivity fragmentCal;
    CreateFragment fragmentCreate;
    SettingsFragment fragmentSettings;

    //create a fragment class
    MyFragmentPageAdapter myFragmentPagerAdapter;
    //this is how to not do this from scratch
    ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAGG","Made it in function activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        EventUploader eu = new EventUploader(this);
        //eu.syncBackend();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab);


        fragmentMap = new MapFragment();
        fragmentCal = new CalendarActivity();
        fragmentCreate = new CreateFragment();
        fragmentSettings = new SettingsFragment();
        myDevents = new MyDevents();

        fragments = new ArrayList<>();
        fragments.add(fragmentSettings);
        fragments.add(fragmentCreate);
        fragments.add(fragmentCal);
        fragments.add(fragmentMap);
        fragments.add(myDevents);


        myFragmentPagerAdapter = new MyFragmentPageAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
