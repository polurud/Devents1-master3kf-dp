package com.dartmouth.kd.devents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;


public class CreateCampusEvent extends FragmentActivity {

    private CampusEventDbHelper mEventDbHelper;
    CampusEvent newEvent;
    ListView listview, listview2;
    public static final int LIST_ITEM_ID_TITLE = 0;
    public static final int LIST_ITEM_ID_DATE = 1;
    public static final int LIST_ITEM_ID_START = 2;
    public static final int LIST_ITEM_ID_END = 3;
    public static final int LIST_ITEM_ID_LOCATION = 4;
    public static final int LIST_ITEM_ID_DESCRIPTION = 5;
    public static final int LIST_ITEM_ID_URL = 6;
    public static final int LIST_ITEM_ID_FOOD = 0;
    public static final int LIST_ITEM_ID_EVENT_TYPE = 1;
    public static final int LIST_ITEM_ID_PROGRAM_TYPE = 2;
    public static final int LIST_ITEM_ID_YEAR = 3;
    public static final int LIST_ITEM_ID_MAJOR = 4;
    public static final int LIST_ITEM_ID_GREEK_SOCIETY = 5;
    public static final int LIST_ITEM_ID_GENDER = 6;

    public static final String TAG = "KF";

    Place myLocation;
    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campus_event);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        listview = (ListView)findViewById(R.id.listview);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int dialogId = 0;
                Intent myIntent;
                // Figuring out what dialog to show based on the position clicked
                // (more readable, also could use dialogId = position + 2)
                switch (position) {
                    case LIST_ITEM_ID_TITLE:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_TITLE;
                        break;
                    case LIST_ITEM_ID_LOCATION:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_LOCATION;
                        break;
                    case LIST_ITEM_ID_DESCRIPTION:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_DESCRIPTION;
                        break;
                    case LIST_ITEM_ID_DATE:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_DATE;
                        break;
                    case LIST_ITEM_ID_START:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_START;
                        break;
                    case LIST_ITEM_ID_END:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_END;
                        break;
                    case LIST_ITEM_ID_URL:
                        dialogId = DialogFragment.DIALOG_ID_MANUAL_INPUT_URL;
                        break;
                    default:
                        dialogId = DialogFragment.DIALOG_ID_ERROR;
                }

                displayDialog(dialogId);
            }
        });

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
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_FOOD;
                        break;
                    case LIST_ITEM_ID_EVENT_TYPE:
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_EVENT_TYPE;
                        break;
                    case LIST_ITEM_ID_PROGRAM_TYPE:
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_PROGRAM_TYPE;
                        break;
                    case LIST_ITEM_ID_YEAR:
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_YEAR;
                        break;
                    case LIST_ITEM_ID_MAJOR:
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_MAJOR;
                        break;
                    case LIST_ITEM_ID_GREEK_SOCIETY:
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_GREEK_SOCIETY;
                        break;
                    case LIST_ITEM_ID_GENDER:
                        dialogId2 = DialogFragment.DIALOG_ID_MANUAL_INPUT_GENDER;
                        break;
                    default:
                        dialogId2 = DialogFragment.DIALOG_ID_ERROR;
                }

                displayDialog(dialogId2);
            }
        });

        newEvent = new CampusEvent();
        mEventDbHelper = new CampusEventDbHelper(this);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                myLocation = place;
                LatLng latlng1 = myLocation.getLatLng();
                newEvent.setLatitude(latlng1.latitude);
                newEvent.setLongitude(latlng1.longitude);

            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }


    // "Save" button is clicked
    public void onSaveClicked(View v) {
        new InsertIntoDbTask().execute(newEvent);
        finish();
    }

    // "Cancel" button is clicked
    public void onCancelClicked(View v) {
        // Discard the input and close the activity directly
        Toast.makeText(getApplicationContext(), "Event discarded.",
                Toast.LENGTH_SHORT).show();
        finish();
    }



    // Display dialog based on id. See DialogFragment for details
    public void displayDialog(int id) {
        android.app.DialogFragment fragment = DialogFragment.newInstance(id);
        fragment.show(getFragmentManager(),
                getString(R.string.dialog_fragment_tag_general));
    }


    public void onTitleSet(String Title) {
        newEvent.setTitle(Title);
    }

    public void onLocationSet(String Location) {
        newEvent.setLocation(Location);
    }

    public void onLongitudeSet(Double Longitude) {
        newEvent.setLongitude(Longitude);
    }
    public void onLatitudeSet(Double Latitude) {
        newEvent.setLatitude(Latitude);
    }

    public void onDescriptionSet(String Description) {
        newEvent.setDescription(Description);
    }

    public void onUrlSet(String URL) {
        newEvent.setURL(URL);
    }

    public void onDateSet(int year, int monthOfYear, int dayOfMonth){
        newEvent.setDate(year, monthOfYear, dayOfMonth);
    }

    public void onStartSet(int hourOfDay, int minute) {
        newEvent.setStart(hourOfDay, minute);
    }

    public void onEndSet(int hourOfDay, int minute) {
        newEvent.setEnd(hourOfDay, minute);
    }


    public void onEventTypeSet(int EventType) {
        newEvent.setEventType(EventType);
    }

    public void onProgramTypeSet(int ProgramType) {
        newEvent.setProgramType(ProgramType);
    }

    public void onMajorSet(int Major) {
        newEvent.setMajor(Major);
    }

    public void onGenderSet(int Gender) {
        newEvent.setGender(Gender);
    }

    public void onGreekSocietySet(int GreekSociety) {
        newEvent.setGreekSociety(GreekSociety);
    }

    public void onYearSet(int Year) {
        newEvent.setYear(Year);
    }

    public void onFoodSet(int Food) {
        newEvent.setFood(Food);
    }

    public class InsertIntoDbTask extends AsyncTask<CampusEvent, Void, String> {
        @Override
        protected String doInBackground(CampusEvent... campusEvent) {
            long id = mEventDbHelper.insertEntry(campusEvent[0]);
            fbHelper(id);
            return ""+id;
            // Pop up a toast

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Event #" + result + " saved.", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public void fbHelper(long id){
        EventUploader eu = new EventUploader(this);
        eu.syncBackend(id);

    }
}
