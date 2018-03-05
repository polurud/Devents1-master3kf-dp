package com.dartmouth.kd.devents;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class DialogFragment extends android.app.DialogFragment {

    public static final int DIALOG_ID_ERROR = -1;
    public static final int DIALOG_PHOTO = 1;
    public static final int DIALOG_ID_MANUAL_INPUT_TITLE = 2;
    public static final int DIALOG_ID_MANUAL_INPUT_DATE = 3;
    public static final int DIALOG_ID_MANUAL_INPUT_START = 4;
    public static final int DIALOG_ID_MANUAL_INPUT_END = 5;
    public static final int DIALOG_ID_MANUAL_INPUT_LOCATION = 6;
    public static final int DIALOG_ID_MANUAL_INPUT_DESCRIPTION = 7;
    public static final int DIALOG_ID_MANUAL_INPUT_URL = 8;
    public static final int DIALOG_ID_MANUAL_INPUT_FOOD = 10;
    public static final int DIALOG_ID_MANUAL_INPUT_EVENT_TYPE = 11;
    public static final int DIALOG_ID_MANUAL_INPUT_PROGRAM_TYPE = 12;
    public static final int DIALOG_ID_MANUAL_INPUT_YEAR = 13;
    public static final int DIALOG_ID_MANUAL_INPUT_MAJOR = 14;
    public static final int DIALOG_ID_MANUAL_INPUT_GREEK_SOCIETY = 15;
    public static final int DIALOG_ID_MANUAL_INPUT_GENDER = 16;
    public static final int DIALOG_ID_FILTER_FOOD = 17;
    public static final int DIALOG_ID_FILTER_EVENT_TYPE = 18;
    public static final int DIALOG_ID_FILTER_PROGRAM_TYPE = 19;
    public static final int DIALOG_ID_FILTER_YEAR = 20;
    public static final int DIALOG_ID_FILTER_MAJOR = 21;
    public static final int DIALOG_ID_FILTER_GREEK_SOCIETY = 22;
    public static final int DIALOG_ID_FILTER_GENDER = 23;
    Context mContext;
    Intent myIntent;
    private static final String DIALOG_KEY = "dialog_id";

    public static DialogFragment newInstance(int dialog_id) {
        DialogFragment f1 = new DialogFragment();
        Bundle args = new Bundle();
        args.putInt(DIALOG_KEY, dialog_id);
        f1.setArguments(args);
        return f1;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int dialog_id = getArguments().getInt(DIALOG_KEY);
        final Activity parent = getActivity();
        final EditText textEntryView;
        //final Spinner spin;
        final Calendar now;
        int hour, minute, year, month, day;
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);

        switch (dialog_id) {
            case DIALOG_PHOTO:

                //AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                builder.setTitle(R.string.ui_profile_pic_Gallerychoose);

                builder.setItems(R.array.ui_profile_photo_selection,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {


                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_TITLE:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_title_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_title)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onTitleSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();

            case DIALOG_ID_MANUAL_INPUT_DATE:

                now = Calendar.getInstance();
                year = now.get(Calendar.YEAR);
                month = now.get(Calendar.MONTH);
                day = now.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(parent,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                ((CreateCampusEvent) parent).onDateSet(
                                        year, monthOfYear, dayOfMonth);
                            }
                        }, year, month, day);

            case DIALOG_ID_MANUAL_INPUT_START:
                now = Calendar.getInstance();
                hour = now.get(Calendar.HOUR_OF_DAY);
                minute = now.get(Calendar.MINUTE);

                return new TimePickerDialog(parent,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                ((CreateCampusEvent) parent).onStartSet(
                                        hourOfDay, minute);
                            }
                        }, hour, minute, false);

            case DIALOG_ID_MANUAL_INPUT_END:
                now = Calendar.getInstance();
                hour = now.get(Calendar.HOUR_OF_DAY);
                minute = now.get(Calendar.MINUTE);

                return new TimePickerDialog(parent,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                ((CreateCampusEvent) parent).onEndSet(
                                        hourOfDay, minute);
                            }
                        }, hour, minute, false);


            case DIALOG_ID_MANUAL_INPUT_LOCATION:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_location_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_location)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onLocationSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();


            case DIALOG_ID_MANUAL_INPUT_DESCRIPTION:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_description_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_description)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onDescriptionSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();

            case DIALOG_ID_MANUAL_INPUT_URL:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_url_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_url)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onUrlSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();

            //NEED TO DO THIS FOR ALL FILTERS
            case DIALOG_ID_MANUAL_INPUT_MAJOR:

                builder.setTitle("Choose Applicable Major");

                        DialogInterface.OnClickListener dlistener = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {

                                ((CreateCampusEvent) parent).onMajorSet(item);
                                Log.d(Globals.TAGG, "Major int is " +item);
                            }
                        };
                builder.setItems(R.array.e_majors, dlistener);
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_YEAR:

                //AlertDialog.Builder builder3 = new AlertDialog.Builder(parent);
                builder.setTitle("Choose Applicable Year");

                DialogInterface.OnClickListener dlistener2 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                ((CreateCampusEvent) parent).onYearSet(item);
                            }
                        };
                builder.setItems(R.array.e_class_year, dlistener2);
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_EVENT_TYPE:

                //AlertDialog.Builder builder4 = new AlertDialog.Builder(parent);
                builder.setTitle("Choose Applicable Event Type");

                        DialogInterface.OnClickListener dlistener3 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {

                                ((CreateCampusEvent) parent).onEventTypeSet(item);
                            }
                        };
                builder.setItems(R.array.event_types1, dlistener3);
                return builder.create();


            case DIALOG_ID_MANUAL_INPUT_PROGRAM_TYPE:

                //AlertDialog.Builder builder5 = new AlertDialog.Builder(parent);
                builder.setTitle("Choose Applicable Program Type");

                DialogInterface.OnClickListener dlistener4 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {

                                ((CreateCampusEvent) parent).onProgramTypeSet(item);

                            }
                        };
                builder.setItems(R.array.e_program, dlistener4);
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_GREEK_SOCIETY:

                //AlertDialog.Builder builder6 = new AlertDialog.Builder(parent);
                builder.setTitle("Choose Applicable Greek Affliation");


                DialogInterface.OnClickListener dlistener5 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                ((CreateCampusEvent) parent).onGreekSocietySet(item);

                            }
                        };
                builder.setItems(R.array.e_greek, dlistener5);
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_GENDER:

                builder.setTitle("Choose Applicable Gender");
                DialogInterface.OnClickListener dlistener6 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {

                                ((CreateCampusEvent) parent).onGenderSet(item);
                            }
                        };
                builder.setItems(R.array.e_gender, dlistener6);
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_FOOD:

                //AlertDialog.Builder builder5 = new AlertDialog.Builder(parent);
                builder.setTitle("Will there be food?");

                DialogInterface.OnClickListener dlistener7 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        ((CreateCampusEvent) parent).onFoodSet(item);

                    }
                };
                builder.setItems(R.array.food, dlistener7);
                return builder.create();

            case DIALOG_ID_FILTER_FOOD:

                builder.setTitle("Does there need to be food?");
                DialogInterface.OnClickListener dlistener8 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onFoodSet(item);
                    }
                };
                builder.setItems(R.array.e_food, dlistener8);
                return builder.create();

            case DIALOG_ID_FILTER_EVENT_TYPE:

                builder.setTitle("Filter By Event Type");
                DialogInterface.OnClickListener dlistener9 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onEventTypeSet(item);
                    }
                };
                builder.setItems(R.array.event_types, dlistener9);
                return builder.create();

            case DIALOG_ID_FILTER_PROGRAM_TYPE:

                builder.setTitle("Filter By Program Type");
                DialogInterface.OnClickListener dlistener10 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onProgramTypeSet(item);
                    }
                };
                builder.setItems(R.array.e_program, dlistener10);
                return builder.create();

            case DIALOG_ID_FILTER_YEAR:

                builder.setTitle("Filter By Class Year");
                DialogInterface.OnClickListener dlistener11 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onYearSet(item);
                    }
                };
                builder.setItems(R.array.e_class_year, dlistener11);
                return builder.create();

            case DIALOG_ID_FILTER_MAJOR:

                builder.setTitle("Filter By Major Type");
                DialogInterface.OnClickListener dlistener13 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onMajorSet(item);
                    }
                };
                builder.setItems(R.array.e_majors, dlistener13);
                return builder.create();

            case DIALOG_ID_FILTER_GREEK_SOCIETY:

                builder.setTitle("Filter By Society");
                DialogInterface.OnClickListener dlistener14 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onGreekSocietySet(item);
                    }
                };
                builder.setItems(R.array.e_greek, dlistener14);
                return builder.create();

            case DIALOG_ID_FILTER_GENDER:

                builder.setTitle("Filter By Gender");
                DialogInterface.OnClickListener dlistener15 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        ((FilterWindow) parent).onGenderSet(item);
                    }
                };
                builder.setItems(R.array.e_gender, dlistener15);
                return builder.create();

            default:
                return null;
        }
    }

}


