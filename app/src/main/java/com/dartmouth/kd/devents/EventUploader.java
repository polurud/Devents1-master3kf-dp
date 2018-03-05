package com.dartmouth.kd.devents;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by kathrynflattum on 2/28/18.
 */

public class EventUploader implements ValueEventListener {
    private static final String TAG = "HistoryUploader";

    private DatabaseReference rootRef, mDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Context mContext;
    private String userID = "userID";
    private String entryID = "entry";
    public static ArrayList<CampusEvent> eventsList;
    public EventUploader(Context context) {
        this.mContext = context.getApplicationContext();
    }


    public void fetchAllBackend(){
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
        Log.d(Globals.TAGG, "Going to add everything back into firebase");

    }


    public void syncBackend(long id) {
        rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
        Log.d(Globals.TAGG, "sync backend is getting called");
        CampusEventDbHelper db = new CampusEventDbHelper(mContext);
        CampusEvent event = db.fetchEventByIndex(id);
        //String key = mDatabase.push().getKey();
        String idString = String.valueOf(id);
        Log.d(TAG, "Id is" + id);
        //Log.d(TAG, "idString is" + idString);
        //mDatabase.setValue(event);
        //rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
        rootRef.child(idString).setValue(event);
        //rootRef.setValue(event);

    }

    public void deleteEntry(long id){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String idString = String.valueOf(id);
        mDatabase.child(idString).setValue(null);
        CampusEventDbHelper db = new CampusEventDbHelper(mContext);
        db.removeEvent(id);

    }

    void removeDataFromDatabase(){
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        root.setValue(null);
    }

    public void onDataChange(DataSnapshot dataSnapshot){

    }

    public void onCancelled (DatabaseError databaseError){}

}
