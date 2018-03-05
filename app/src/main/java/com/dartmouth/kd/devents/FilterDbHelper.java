package com.dartmouth.kd.devents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kathrynflattum on 3/3/18.
 */

public class FilterDbHelper extends SQLiteOpenHelper {
    // Database name string
    public static final String DATABASE_NAME = "FiltersDB";
    // Table name string. (Only one table)
    private static final String TABLE_FILTERS = "FILTERS";

    // Version code
    private static final int DATABASE_VERSION = 1;

    // Table schema, column names
    public static final String KEY_ROWID = "_id";
    public static final String KEY_FOOD = "event_food";
    public static final String KEY_MAJOR = "event_major";
    public static final String KEY_EVENT_TYPE = "e_event_type";
    public static final String KEY_PROGRAM_TYPE = "event_program_type";
    public static final String KEY_YEAR = "event_year";
    public static final String KEY_GREEK_SOCIETY = "event_greek_society";
    public static final String KEY_GENDER = "event_gender";

    // SQL query to create the table for the first time
    // Data types are defined below
    private static final String CREATE_TABLE_FILTERS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_FILTERS
            + "("
            + KEY_ROWID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_FOOD
            + " INT, "
            + KEY_EVENT_TYPE
            + " INT, "
            + KEY_PROGRAM_TYPE
            + " INT, "
            + KEY_YEAR
            + " INT, "
            + KEY_MAJOR
            + " INT, "
            + KEY_GREEK_SOCIETY
            + " INT, "
            + KEY_GENDER
            + " INT "
            + ");";

    private static final String[] mColumnList = new String[]{KEY_ROWID,
            KEY_FOOD, KEY_EVENT_TYPE,
            KEY_PROGRAM_TYPE, KEY_YEAR, KEY_MAJOR, KEY_GREEK_SOCIETY, KEY_GENDER};

    public FilterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FILTERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    // Insert a item given each column value
    public long insertFilter(Filters filter) {

        ContentValues value = new ContentValues();
        value.put(KEY_FOOD, filter.getfFood());
        value.put(KEY_EVENT_TYPE, filter.getfEventType());
        value.put(KEY_PROGRAM_TYPE, filter.getfProgramType());
        value.put(KEY_YEAR, filter.getfYear());
        value.put(KEY_MAJOR, filter.getfMajor());
        value.put(KEY_GENDER, filter.getfGender());
        value.put(KEY_GREEK_SOCIETY, filter.getfGreekSociety());
        SQLiteDatabase dbObj = getWritableDatabase();
        long id = dbObj.insert(TABLE_FILTERS, null, value);
        dbObj.close();
        return id;
    }

    // Remove a entry by giving its index
    public void removeFilter(long rowIndex) {
        SQLiteDatabase dbObj = getWritableDatabase();
        dbObj.delete(TABLE_FILTERS, KEY_ROWID + "=" + rowIndex, null);
        dbObj.close();
    }

    // Query a specific entry by its index. Return a cursor having each column
    // value
    public Filters fetchFilterByIndex(long rowId) throws SQLException {
        SQLiteDatabase dbObj = getReadableDatabase();
        Filters filter = null;

        Cursor cursor = dbObj.query(true, TABLE_FILTERS, mColumnList,
                KEY_ROWID + "=" + rowId, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            filter = cursorToEvent(cursor);
        }

        cursor.close();
        dbObj.close();

        return filter;
    }

    // Query the entire table, return all rows
    public Filters getLastUsedFilter() {
        SQLiteDatabase dbObj = getReadableDatabase();
        ArrayList<Filters> filtersList = new ArrayList<Filters>();
        if (filtersList == null){
            return null;
        }else {
            Cursor cursor = dbObj.query(TABLE_FILTERS, mColumnList, null,
                    null, null, null, null);
            Filters filter = null;
            while (cursor.moveToNext()) {
                filter = cursorToEvent(cursor);
            }

            cursor.close();
            dbObj.close();

            return filter;
        }
    }


    // Query the entire table, return all rows
    public ArrayList<Filters> fetchFilters() {
        SQLiteDatabase dbObj = getReadableDatabase();
        ArrayList<Filters> filtersList = new ArrayList<Filters>();

        Cursor cursor = dbObj.query(TABLE_FILTERS, mColumnList, null,
                null, null, null, null);

        while (cursor.moveToNext()) {
            Filters filter = cursorToEvent(cursor);
            filtersList.add(filter);
        }

        cursor.close();
        dbObj.close();

        return filtersList;
    }

    private Filters cursorToEvent(Cursor cursor) {
        Filters filter = new Filters();
        filter.setfId(cursor.getLong(cursor.getColumnIndex(KEY_ROWID)));
        filter.setfFood(cursor.getInt(cursor.getColumnIndex(KEY_FOOD)));
        filter.setfMajor(cursor.getInt(cursor.getColumnIndex(KEY_MAJOR)));
        filter.setfEventType(cursor.getInt(cursor.getColumnIndex(KEY_EVENT_TYPE)));
        filter.setfProgramType(cursor.getInt(cursor.getColumnIndex(KEY_PROGRAM_TYPE)));
        filter.setfYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
        filter.setfGreekSociety(cursor.getInt(cursor.getColumnIndex(KEY_GREEK_SOCIETY)));
        filter.setfGender(cursor.getInt(cursor.getColumnIndex(KEY_GENDER)));
        return filter;
    }


    public void deleteAllFilters() {
        SQLiteDatabase dbObj = getWritableDatabase();
        dbObj.delete(FilterDbHelper.TABLE_FILTERS, null, null);
    }

    public ArrayList<CampusEvent> eventListFilter (ArrayList<CampusEvent> campusEvents, Filters filter) {
        ArrayList<CampusEvent> newList = new ArrayList<CampusEvent>();
        //if all the filters are zero, return original list
        Log.d(Globals.TAGG, "Showing what the filters are");

        if (filter == null) {
            Log.d(Globals.TAGG, "All filters are null");
            return campusEvents;
        } else {
            Log.d(Globals.TAGG, "Food filter value" + filter.getfFood());
            if (filter.getfFood() == 0 && filter.getfEventType() == 0 && filter.getfProgramType() == 0 && filter.getfGender() == 0 && filter.getfGreekSociety() == 0 && filter.getfMajor() == 0 && filter.getfYear() == 0) {
                return campusEvents;
            }
            if (filter.getfFood() != 0) {
                for (CampusEvent event : campusEvents) {
                    int scaleval = filter.getfFood() - 1;
                    if (event.getFood() == scaleval) {
                        newList.add(event);
                    }
                }
            }
            if (filter.getfEventType() != 0) {
                for (CampusEvent event : campusEvents) {
                    int scaleval = filter.getfEventType() - 1;
                    if (event.getEventType() == scaleval) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfProgramType() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getProgramType() == filter.getfProgramType()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfYear() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getYear() == filter.getfYear()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfMajor() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getMajor() == filter.getfMajor()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfGender() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getGender() == filter.getfGender()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfGreekSociety() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getGreekSociety() == filter.getfGreekSociety()) {
                        newList.add(event);
                    }
                }
            }

            return newList;
        }
    }

}
