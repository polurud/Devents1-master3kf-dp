package com.dartmouth.kd.devents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {

    private CampusEventDbHelper mEventDbHelper;
    private FilterDbHelper mFilterDbHelper;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;
    private ArrayAdapter<String> adapter;
    public static ArrayList<CampusEvent> eventlist;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkPermissions();
        //hi
        mcontext = this;
        mEventDbHelper = new CampusEventDbHelper(mcontext);
        mEventDbHelper.deleteAllEvents();
        mFilterDbHelper = new FilterDbHelper(mcontext);
        mFilterDbHelper.deleteAllFilters();

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("masterSheet");
        Load_Urlevents  jsoupAsyncTask = new Load_Urlevents(this);
        jsoupAsyncTask.execute();
        Intent intent = new Intent(this, FunctionActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               //eventlist = new ArrayList<>();
            for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                Map<String,Object> singleRun =  (Map<String, Object>) eventSnapshot.getValue();

                CampusEvent event = new CampusEvent();
                event.setTitle(singleRun.get("title").toString());
                event.setLocation(singleRun.get("location").toString());
                event.setDate(0, 0, 0);
                event.setEnd(singleRun.get("mEnd").toString());
                event.setStart(singleRun.get("mStart").toString());
                //event.setmDate(singleRun.get("Date").toString());
                //event.setEnd(singleRun.get("End").toString());
                //event.setStart(singleRun.get("Start").toString());
                event.setURL(singleRun.get("url").toString());
                event.setDescription(singleRun.get("description").toString());
                double lat = 43.7022;
                double longi = 72.2896;
                event.setLongitude(longi);
                event.setLatitude(lat);
                event.setGreekSociety(0);
                event.setMajor(0);
                event.setGender(0);
                event.setYear(0);
                event.setProgramType(0);
                event.setEventType(0);
                event.setFood(2);
                mEventDbHelper = new CampusEventDbHelper(mcontext);

                new InsertIntoDbTask().execute(event);
                //eventlist.add(event);
            }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    //called when login button is pressed
    public void login_button(View view)
    {
        Intent intent = new Intent(this, LogInActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
    //called when signup button is pressed
    public void signup_button(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void checkPermissions() {
        if (Build.VERSION.SDK_INT < 23)
            return;

        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED) {


        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) || shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("This permission is important for the app.")
                            .setTitle("Important permission required");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, 0);
                            }

                        }
                    });
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }else{
                    //Never ask again and handle your app without permission.
                }
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            Utils.showActivity(this, LogInActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }



    public class InsertIntoDbTask extends AsyncTask<CampusEvent, Void, String> {
        @Override
        protected String doInBackground(CampusEvent... campusEvents) {
            long id = mEventDbHelper.insertEntry(campusEvents[0]);

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



