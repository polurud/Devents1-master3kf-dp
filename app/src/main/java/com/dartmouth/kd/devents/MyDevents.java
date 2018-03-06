package com.dartmouth.kd.devents;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 3/5/2018.
 */

public class MyDevents extends ListFragment implements LoaderManager.LoaderCallbacks<ArrayList<CampusEvent>> {

    private static CampusEventDbHelper mCampusEventDbHelper;
    public static ArrayList<CampusEvent>  entryList = new ArrayList<CampusEvent>();
    public static Context mContext; // context pointed to parent activity
    public static DActivityEntriesAdapter mAdapter; // customized adapter for displaying
    //My Devent
    public static LoaderManager loaderManager;
    public static int onCreateCheck=0;

    // retrieve records from the database and display them in the list view

    public void updateMyDevents(Context context) {
        if (this.mCampusEventDbHelper == null) {
            setUpDB(context);
        }
        if(onCreateCheck==1){
            onCreateCheck=0;
        } else {
            loaderManager.initLoader(1, null, this).forceLoad();
            Log.d("TAG", "Called");
        }
    }


    private void setUpDB(Context context) {
        this.mContext = context;
        CampusEventDbHelper campusEventDbHelper = new CampusEventDbHelper(context);
        this.mCampusEventDbHelper= campusEventDbHelper;
        DActivityEntriesAdapter dactivityEntriesAdapter = new DActivityEntriesAdapter(this,context);
        this.mAdapter = dactivityEntriesAdapter;
        loaderManager = getActivity().getLoaderManager();

        setListAdapter(this.mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();

        // Open data base for operations.
        mCampusEventDbHelper = new CampusEventDbHelper(mContext);
        loaderManager = getActivity().getLoaderManager();
        // Instantiate our customized array adapter
        mAdapter = new DActivityEntriesAdapter(this,mContext);
        // Set the adapter to the listview
        setListAdapter(mAdapter);
        loaderManager.initLoader(1, null, this).forceLoad();

        onCreateCheck=1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_devents, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Re-query in case the data base has changed.
        updateMyDevents(mContext);

    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(); // The intent to launch the activity after
        // click.
        Bundle extras = new Bundle(); // The extra information needed pass
        // through to next activity.

        // get the Event corresponding to user's selection
        CampusEvent event = mAdapter.getItem(position);

        // Write row id into extras.
        extras.putLong(Globals.KEY_ROWID, event.getmId());
        // Passing information for display in the DisaplayEntryActivity.
        extras.putString(Globals.KEY_TITLE,event.getTitle());
        extras.putString(Globals.KEY_DATE,
                Utils.parseDate(event.getDateTimeInMillis(), mContext));
        extras.putString(Globals.KEY_START,
                Utils.parseStart(event.getDateTimeInMillis(), mContext));
        extras.putString(Globals.KEY_END,
                Utils.parseEnd(event.getDateTimeInMillis(), mContext));
        extras.putString(Globals.KEY_LOCATION,event.getLocation());
        extras.putString(Globals.KEY_DESCRIPTION,event.getDescription());
        extras.putDouble(Globals.KEY_LATITUDE, event.getLatitude());
        extras.putDouble(Globals.KEY_LONGITUDE, event.getLongitude());

        extras.putInt(Globals.KEY_FOOD, event.getFood());
        extras.putInt(Globals.KEY_MAJOR,event.getMajor());
        extras.putInt(Globals.KEY_EVENT_TYPE,event.getEventType());
        extras.putInt(Globals.KEY_PROGRAM_TYPE,event.getProgramType());
        extras.putInt(Globals.KEY_YEAR,event.getYear());
        extras.putInt(Globals.KEY_GREEK_SOCIETY,event.getGreekSociety());
        extras.putInt(Globals.KEY_GENDER,event.getGender());



        // Manual mode requires DisplayEntryActivity
        intent.setClass(mContext, DisplayEventActivity.class);

        // start the activity
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public Loader<ArrayList<CampusEvent>> onCreateLoader(int i, Bundle bundle) {
        Log.d("TAGG", "Came here");

        return new DataLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<CampusEvent>> loader, ArrayList<CampusEvent> campusevents) {
        entryList = campusevents;
        mAdapter.clear();
        Log.d("TAGG", "Load Finished");


        mAdapter.addAll(entryList);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onLoaderReset(Loader<ArrayList<CampusEvent>> loader) {
        mAdapter.clear();
        mAdapter.addAll(entryList);
        mAdapter.notifyDataSetChanged();
    }
    // Subclass of ArrayAdapter to display interpreted database row values in
    // customized list view.
    private class DActivityEntriesAdapter extends ArrayAdapter<CampusEvent> {
        final /* synthetic */ MyDevents this_0;

        public DActivityEntriesAdapter(MyDevents myDevents, Context context) {
            // set layout to show two lines for each item

            super(context, android.R.layout.two_line_list_item);
            this.this_0 = myDevents;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View listItemView = convertView;
            if (null == convertView) {
                // we need to check if the convertView is null. If it's null,
                // then inflate it.
                listItemView = inflater.inflate(
                        android.R.layout.two_line_list_item, parent, false);
            }

            // Setting up view's text1 is main title, text2 is sub-title.
            TextView titleView = (TextView) listItemView
                    .findViewById(android.R.id.text1);
            TextView summaryView = (TextView) listItemView
                    .findViewById(android.R.id.text2);

            // get the corresponding ExerciseEntry
            CampusEvent event = getItem(position);

            //parse data to readable format
            String title = event.getTitle();

            String dateString = Utils.parseDate(event.getDateTimeInMillis(),
                    mContext);
            String startString = Utils.parseStart(event.getDateTimeInMillis(),
                    mContext);
            String endString = Utils.parseEnd(event.getDateTimeInMillis(),
                    mContext);

            // Set text on the view.
            titleView.setText(dateString + ": " + title);
            summaryView.setText(startString + "- " + endString);

            return listItemView;
        }

    }


    public static class InsertIntoDbTask extends AsyncTask<CampusEvent, Void, String> {
        @Override
        protected String doInBackground(CampusEvent... campusevents) {
            long id = mCampusEventDbHelper.insertEntry(campusevents[0]);

            return ""+id;
            // Pop up a toast

        }

        @Override
        protected void onPostExecute(String result) {

        }

    }

}





