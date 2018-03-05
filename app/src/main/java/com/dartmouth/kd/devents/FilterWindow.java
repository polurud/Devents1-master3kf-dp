package com.dartmouth.kd.devents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class FilterWindow extends FragmentActivity {

    private FilterDbHelper filterDbHelper;
    Filters newFilter;
    ListView listview2;
    public static final int LIST_ITEM_ID_FOOD = 0;
    public static final int LIST_ITEM_ID_EVENT_TYPE = 1;
    public static final int LIST_ITEM_ID_PROGRAM_TYPE = 2;
    public static final int LIST_ITEM_ID_YEAR = 3;
    public static final int LIST_ITEM_ID_MAJOR = 4;
    public static final int LIST_ITEM_ID_GREEK_SOCIETY = 5;
    public static final int LIST_ITEM_ID_GENDER = 6;

    private int mFood;
    private String mEventType;
    private String mProgramType;
    private String mYear;
    private String mMajor;
    private String mGreekSociety;
    private String mGender;
    public static final String TAG = "KF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_window);

        listview2 = (ListView)findViewById(R.id.listview2);
        listview2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int dialogId2 =0;
                Intent myIntent;
                // Figuring out what dialog to show based on the position clicked
                // (more readable, also could use dialogId = position + 2)
                switch (position) {
                    case LIST_ITEM_ID_FOOD:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_FOOD;
                        break;
                    case LIST_ITEM_ID_EVENT_TYPE:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_EVENT_TYPE;
                        break;
                    case LIST_ITEM_ID_PROGRAM_TYPE:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_PROGRAM_TYPE;
                        break;
                    case LIST_ITEM_ID_YEAR:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_YEAR;
                        break;
                    case LIST_ITEM_ID_MAJOR:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_MAJOR;
                        break;
                    case LIST_ITEM_ID_GREEK_SOCIETY:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_GREEK_SOCIETY;
                        break;
                    case LIST_ITEM_ID_GENDER:
                        dialogId2 = DialogFragment.DIALOG_ID_FILTER_GENDER;
                        break;
                    default:
                        dialogId2 = DialogFragment.DIALOG_ID_ERROR;
                }

                displayDialog(dialogId2);
            }
        });
        newFilter = new Filters();
        filterDbHelper = new FilterDbHelper(this);
    }


    // "Save" button is clicked
    public void onSaveClicked(View v) {
        new InsertIntoDbTask().execute(newFilter);
        Toast.makeText(getApplicationContext(), "Filter applied",
                Toast.LENGTH_SHORT).show();
        //sendCurrentFilters(newFilter);

        finish();
    }

    // "Cancel" button is clicked
    public void onCancelClicked(View v) {
        // Discard the input and close the activity directly
        Toast.makeText(getApplicationContext(), "Filter discarded",
                Toast.LENGTH_SHORT).show();
        finish();
    }


    // Display dialog based on id. See DialogFragment for details
    public void displayDialog(int id) {
        android.app.DialogFragment fragment = DialogFragment.newInstance(id);
        fragment.show(getFragmentManager(),
                getString(R.string.dialog_fragment_tag_general));
    }


    public void onEventTypeSet(int eventType) {
        newFilter.setfEventType(eventType);
    }

    public void onProgramTypeSet(int programType) {
        newFilter.setfProgramType(programType);
    }

    public void onMajorSet(int major) {
        newFilter.setfMajor(major);
    }

    public void onGenderSet(int gender) {
        newFilter.setfGender(gender);
    }

    public void onGreekSocietySet(int greekSociety) {
        newFilter.setfGreekSociety(greekSociety);
    }

    public void onYearSet(int year) {
        newFilter.setfYear(year);
    }

    public void onFoodSet(int food) {
        newFilter.setfFood(food);
    }

    public Filters getCurrentFilters(){
        return newFilter;
    }

    public class InsertIntoDbTask extends AsyncTask<Filters, Void, String> {
        @Override
        protected String doInBackground(Filters... filterList) {
            long id = filterDbHelper.insertFilter(filterList[0]);

            return ""+id;
            // Pop up a toast

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Event #" + result + " saved.", Toast.LENGTH_SHORT)
                    .show();
        }

    }

}
