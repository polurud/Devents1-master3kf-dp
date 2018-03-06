//package com.dartmouth.kd.devents;
//
//import android.app.ListFragment;
//import android.app.LoaderManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.Loader;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.Map;
//
///**
// * Created by HP on 3/5/2018.
// */
//
//public class MyDevents extends ListFragment  {
//
//
//    public static ArrayList<CampusEvent>  entryList = new ArrayList<CampusEvent>();
//
//
//    //My Devent
//    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
//    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
//    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//    String mUserId = mFirebaseUser.getUid();
//    private Context mContext;
//    MyDEventDbHelper myDEventDbHelper;
//
//
//
//    // retrieve records from the database and display them in the list view
//    public void updateMyDevents(Context context) {
//
//            setUpDB(context);
//    }
//
//
//    private void setUpDB(Context context) {
//        rootRef.child("users").child(mUserId).child("events")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
//                            Map<String,Object> singleRun =  (Map<String, Object>) eventSnapshot.getValue();
//
//                            CampusEvent event = new CampusEvent();
//                            event.setTitle(singleRun.get("title").toString());
//                            event.setLocation(singleRun.get("location").toString());
//                            event.setDate(0, 0, 0);
//                            event.setEnd(singleRun.get("mEnd").toString());
//                            event.setStart(singleRun.get("mStart").toString());
//                            //event.setmDate(singleRun.get("Date").toString());
//                            //event.setEnd(singleRun.get("End").toString());
//                            //event.setStart(singleRun.get("Start").toString());
//                            event.setURL(singleRun.get("url").toString());
//                            event.setDescription(singleRun.get("description").toString());
//                            double lat = 43.7022;
//                            double longi = 72.2896;
//                            event.setLongitude(longi);
//                            event.setLatitude(lat);
//                            event.setGreekSociety(0);
//                            event.setMajor(0);
//                            event.setGender(0);
//                            event.setYear(0);
//                            event.setProgramType(0);
//                            event.setEventType(0);
//                            event.setFood(2);
//
//                            myDEventDbHelper = new MyDEventDbHelper(mcontext);
//
//                            new InsertIntoDbTask().execute(event);
//
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//
//
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//       return inflater.inflate(R.layout.fragment_my_devents, container, false);
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Re-query in case the data base has changed.
//        updateMyDevents(mcontext);
//
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Intent intent = new Intent(); // The intent to launch the activity after
//        // click.
//        Bundle extras = new Bundle(); // The extra information needed pass
//        // through to next activity.
//
//        // get the Event corresponding to user's selection
////        CampusEvent event = mAdapter.getItem(position);
////
////        // Write row id into extras.
////        extras.putLong(Globals.KEY_ROWID, event.getmId());
////        // Passing information for display in the DisaplayEntryActivity.
////        extras.putString(Globals.KEY_TITLE,event.getTitle());
////        extras.putString(Globals.KEY_DATE,
////                Utils.parseDate(event.getDateTimeInMillis(), mContext));
////        extras.putString(Globals.KEY_START,
////                Utils.parseStart(event.getDateTimeInMillis(), mContext));
////        extras.putString(Globals.KEY_END,
////                Utils.parseEnd(event.getDateTimeInMillis(), mContext));
////        extras.putString(Globals.KEY_LOCATION,event.getLocation());
////        extras.putString(Globals.KEY_DESCRIPTION,event.getDescription());
////        extras.putDouble(Globals.KEY_LATITUDE, event.getLatitude());
////        extras.putDouble(Globals.KEY_LONGITUDE, event.getLongitude());
////
////        extras.putInt(Globals.KEY_FOOD, event.getFood());
////        extras.putInt(Globals.KEY_MAJOR,event.getMajor());
////        extras.putInt(Globals.KEY_EVENT_TYPE,event.getEventType());
////        extras.putInt(Globals.KEY_PROGRAM_TYPE,event.getProgramType());
////        extras.putInt(Globals.KEY_YEAR,event.getYear());
////        extras.putInt(Globals.KEY_GREEK_SOCIETY,event.getGreekSociety());
////        extras.putInt(Globals.KEY_GENDER,event.getGender());
//
//    }
//
//
//
//
//
//
//
//    public static class InsertIntoDbTask extends AsyncTask<CampusEvent, Void, String> {
//        @Override
//        protected String doInBackground(CampusEvent... campusEvents) {
//            MyDEventDbHelper myDEventDbHelper = new MyDEventDbHelper(mContext);
//            long id = myDEventDbHelper.insertEntry(campusEvents[0]);
//
//            return ""+id;
//            // Pop up a toast
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//        }
//
//    }
//
//}
//
//
//
//
//
