package com.dartmouth.kd.devents;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


// Display a campus event
public class DisplayEventActivity extends Activity {

    private static final int MENU_ID_DELETE = 0;
    @SuppressWarnings("unused")
    private static final int MENU_ID_UPDATE = 1;

    private long mEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        //mContext = this;

        Bundle extras = getIntent().getExtras();

        if ( extras != null){
            mEventId = extras.getLong(Globals.KEY_ROWID);
            ((EditText) findViewById(R.id.editDispTitle)).setText(extras.getString(Globals.KEY_TITLE));
            ((EditText) findViewById(R.id.editDispDate)).setText(extras.getString(Globals.KEY_DATE));
            ((EditText) findViewById(R.id.editDispStart)).setText(extras.getString(Globals.KEY_START));
            ((EditText) findViewById(R.id.editDispEnd)).setText(extras.getString(Globals.KEY_END));
            ((EditText) findViewById(R.id.editDispLocation)).setText(extras.getString(Globals.KEY_LOCATION));
            ((EditText) findViewById(R.id.editDispDescription)).setText(extras.getString(Globals.KEY_DESCRIPTION));
            ((EditText) findViewById(R.id.editDispUrl)).setText(extras.getString(Globals.KEY_URL));
            int food = extras.getInt(Globals.KEY_FOOD);
            ((EditText) findViewById(R.id.editDispFood)).setText(getFoodString(food));
            ((EditText) findViewById(R.id.editDispEventType)).setText(getFoodString(food));
            int et = extras.getInt(Globals.KEY_EVENT_TYPE);
            ((EditText) findViewById(R.id.editDispEventType)).setText(getEventTypeString(et));
            int pt = extras.getInt(Globals.KEY_PROGRAM_TYPE);
            ((EditText) findViewById(R.id.editDispProgramType)).setText(getProgramTypeString(pt));
            int year = extras.getInt(Globals.KEY_YEAR);
            ((EditText) findViewById(R.id.editDispYear)).setText(getYearString(year));
            int major = extras.getInt(Globals.KEY_MAJOR);
            ((EditText) findViewById(R.id.editDispMajor)).setText(getMajorString(major));
            int gs = extras.getInt(Globals.KEY_GREEK_SOCIETY);
            ((EditText) findViewById(R.id.editDispGreekSociety)).setText(getGreekString(gs));
            int gender = extras.getInt(Globals.KEY_GENDER);
            ((EditText) findViewById(R.id.editDispGender)).setText(getGenderString(gender));
        }
    }

    // "Save to MyDEvents" button is clicked
    public void onSavetoMyDEvents(View v) {
        //new InsertIntoDbTask().execute(newEvent);
        //Log.d(Globals.TAGG, "Saving to my devents ");
        Toast.makeText(this, "Saved to My DEvents", Toast.LENGTH_SHORT).show();
        finish();
    }

    /*
     * Handle the back button
     */
    public void onBackClicked(View view) {
        // close
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuItem menuitem;
        menuitem = menu.add(Menu.NONE, MENU_ID_DELETE, MENU_ID_DELETE, "Delete");
        menuitem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_DELETE:
                CampusEventDbHelper campusEventDbHelper = new CampusEventDbHelper(this);
                campusEventDbHelper.removeEvent(mEventId);

                finish();
                return true;

            //case MENU_ID_SAVE_DEVENTS:

            default:
                finish();
                return false;
        }
    }

    String getFoodString(int id){
        switch(id){
            case 0: return "ALL";
            case 1: return "No";
            case 2: return "YES!";
        }
        return "ALL";
    }

    String getMajorString(int id){
        switch(id){

            case 0: return "ALL";
            case 1: return "Arts";
            case 2: return "Humanities";
            case 4: return "Science,Technology and Math";
            case 5: return "Others";
        }
        return "ALL";
    }

    String getGenderString(int id){
        switch(id){

            case 0: return "ALL";
            case 1: return "Female";
            case 2: return "Male";
        }
        return "ALL";
    }

    String getEventTypeString(int id){
        switch(id){

            case 0: return "ALL";
            case 1: return "Cultural Events";
            case 2: return "Sports";
            case 4: return "Workshops";
            case 5: return "Guest Speakers";
            case 6: return "Greek";
        }
        return "ALL";
    }

    String getProgramTypeString(int id){
        switch(id){

            case 0: return "ALL";
            case 1: return "Undergraduate";
            case 2: return "Graduate Masters";
            case 4: return "Graduate PhD";
            case 5: return "Faculty";
        }
        return "ALL";
    }

    String getYearString(int id){
        switch(id){

            case 0: return "ALL";
            case 1: return "N/A";
            case 2: return "2018";
            case 4: return "2019";
            case 5: return "2020";
            case 6: return "2021";
        }
        return "ALL";
    }

    String getGreekString(int id) {
            switch (id) {
                case 0:
                    return "ALL";
                case 1:
                    return "N/A";
                case 2:
                    return "Alpha Chi Alpha";
                case 3:
                    return "Alpha Kappa Alpha Sorority";
                case 4:
                    return "Alpha Phi";
                case 5:
                    return "Alpha phi Alpha, Fraternity";
                case 6:
                    return "Alpha Pi Omega, Sorority";
                case 7:
                    return "Alpha Theta";
                case 8:
                    return "Alpha Xi Delta";
                case 9:
                    return "Beta Alpha Omega";
                case 10:
                    return "Bones Gate";
                case 11:
                    return "Chi Delta";
                case 12:
                    return "Chi Gamma Epsilon";
                case 13:
                    return "Chi Heorot";
                case 14:
                    return "Epsilon Kappa Theta";
                case 15:
                    return "Gamma Delta Chi";
                case 16:
                    return "Kappa Delta";
                case 17:
                    return "Kappa Delta Epsilon";
                case 18:
                    return "Kappa Kappa Gamma";
                case 19:
                    return "Kappa Kappa Kappa";
                case 20:
                    return "Lambda Upsilon Lambda";
                case 21:
                    return "Phi Delta Alpha";
                case 22:
                    return "Phi Tau";
                case 23:
                    return "Psi Upsilon";
                case 24:
                    return "Sigma Delta";
                case 25:
                    return "Sigma Nu";
                case 26:
                    return "Sigma Phi Epsilon";
                case 27:
                    return "The Tabard";
                case 28:
                    return "Theta Delta Chi";
                case 29:
                    return "Zeta Psi";
            }
            return "ALL";
        }


}

