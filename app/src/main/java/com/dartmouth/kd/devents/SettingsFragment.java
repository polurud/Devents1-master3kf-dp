package com.dartmouth.kd.devents;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Have to click back for it to work
 */

public class SettingsFragment extends Fragment {
    private Intent myIntent;
    Button changeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = inflater.inflate(com.dartmouth.kd.devents.R.layout.fragment_settings, container, false);

        changeButton = ret.findViewById(R.id.startProfile);
        changeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg){
                myIntent = new Intent(getActivity(),  UserProfile.class);
                startActivityForResult(myIntent, 0);

                //getActivity().startActivity(myIntent);
            }
        });
        return ret;
    }
}
