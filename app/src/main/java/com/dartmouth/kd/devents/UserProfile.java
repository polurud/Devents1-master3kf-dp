package com.dartmouth.kd.devents;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class UserProfile extends Activity {

    public static final int REQUEST_CODE_TAKE_FROM_CAMERA = 0;
    public static final int CODE_GALLERY = 1;

    private static final String URI_INSTANCE_STATE_KEY = "saved_uri";
    private static final String URI_INSTANCE_STATE_KEY_TEMP = "saved_uri_temp";
    private static final String CAMERA_CLICKED_KEY = "clicked";

    private ImageView mImageView;
    private Uri mImageCaptureUri, mTempUri;
    private Boolean stateChanged = false, cameraClicked = false,clickedbyCam=false;
    private Button Button_change, Button_cancel, Button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button_save = (Button) findViewById(R.id.bSave);
        Button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
                Toast.makeText(getApplicationContext(),
                        getString(R.string.ui_profile_toast_save_text),
                        Toast.LENGTH_SHORT).show();
                // Close the activity
                finish();
            }
        });
        Button_cancel = (Button) findViewById(R.id.bCancel);
        Button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });
        loadProfile();

    }

    public void onCancelClick(){
        finish();
    }


    public void loadProfile() {
        String key, str;
        int val;
        boolean bol;



        key = getString(R.string.preference_name);
        SharedPreferences prefs = getSharedPreferences(key, MODE_PRIVATE);

        key = getString(R.string.ui_profile_name);
        str = prefs.getString(key, "");
        ((EditText) findViewById(R.id.userName)).setText(str);


        key = getString(R.string.ui_profile_gender);
        val = prefs.getInt(key, -1);

        if (val >= 0) {
            RadioButton radioBtn = (RadioButton) ((RadioGroup) findViewById(R.id.radiogroup_Gender))
                    .getChildAt(val);
            radioBtn.setChecked(true);
        }

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        key = spinner.getSelectedItem().toString();
        val = spinner.getSelectedItemPosition();
        if (val>=1) {

            Spinner spinner_s  = (Spinner) ((Spinner) findViewById(R.id.spinner)).getChildAt(val);
            spinner_s.setActivated(true);
        }


    }

    public void saveProfile() {
        String key, str2;
        int val2;
        boolean bol;

        key = getString(R.string.preference_name);
        SharedPreferences prefs = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Write screen contents into corresponding editor fields.
        key = getString(R.string.enter_user_name1);
        str2 = ((EditText) findViewById(R.id.userName)).getText().toString();
        editor.putString(key, str2);


        key = getString(R.string.ui_profile_gender);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup_Gender);
        val2 = radioGroup.indexOfChild(findViewById(radioGroup
                .getCheckedRadioButtonId()));
        editor.putInt(key, val2);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        val2 = spinner.getSelectedItemPosition();
        key = spinner.getSelectedItem().toString();
        editor.putInt(key, val2);


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        key = spinner2.getSelectedItem().toString();
        val2 = spinner.getSelectedItemPosition();
        editor.putInt(key, val2);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        key = spinner3.getSelectedItem().toString();
        val2 = spinner.getSelectedItemPosition();
        editor.putInt(key, val2);

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        key = spinner4.getSelectedItem().toString();
        val2 = spinner.getSelectedItemPosition();
        editor.putInt(key, val2);

        CheckBox checkBox6 = (CheckBox)  findViewById(R.id.checkBox6);
        bol = checkBox6.isChecked();
        if (bol == true)
            key = checkBox6.toString();
        editor.putString(key, key);

        CheckBox checkBox2 = (CheckBox)  findViewById(R.id.checkBox2);
        bol = checkBox2.isChecked();
        if (bol == true)
            key = checkBox2.toString();
        editor.putString(key, key);


        CheckBox checkBox5 = (CheckBox)  findViewById(R.id.checkBox5);
        bol = checkBox5.isChecked();
        if (bol == true)
            key = checkBox5.toString();
        editor.putString(key, key);


        CheckBox checkBox7 = (CheckBox)  findViewById(R.id.checkBox7);
        bol = checkBox7.isChecked();
        if (bol == true)
            key = checkBox7.toString();
        editor.putString(key, key);


        CheckBox checkBox3 = (CheckBox)  findViewById(R.id.checkBox3);
        bol = checkBox3.isChecked();
        if (bol == true)
            key = checkBox3.toString();
        editor.putString(key, key);

        editor.apply();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

