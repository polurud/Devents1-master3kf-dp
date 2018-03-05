package com.dartmouth.kd.devents;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CalFragment extends Fragment {
    private Intent myIntent;
    public static Context mContext;
    Button calButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setRetainInstance(true);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View ret = inflater.inflate(R.layout.fragment_cal, container, false);

        calButton = ret.findViewById(R.id.startCalendar);

        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg){
                CalendarActivity nextFrag= new CalendarActivity();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(getView().getId(), nextFrag,"CalendarActivity")
                        .addToBackStack(null)
                        .commit();
                //myIntent = new Intent(getActivity(),  CalendarActivity.class);
                //startActivityForResult(myIntent, 0);
            }
        });

        return ret;
    }


}