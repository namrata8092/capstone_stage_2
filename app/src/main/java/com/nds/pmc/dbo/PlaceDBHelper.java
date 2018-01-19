package com.nds.pmc.dbo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Namrata on 1/15/2018.
 */

public class PlaceDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "place.db";

    public static final int DATABASE_VERSION = 1;

    public PlaceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE= "CREATE TABLE "+ PlaceContract.PlaceEntry.TABLE_NAME+
                " ("+ PlaceContract.PlaceEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID+" TEXT NOT NULL, "
                + PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_TITLE+" TEXT NOT NULL, "
                + PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ADDRESS+" TEXT NOT NULL,"
                + PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_RATING+" REAL NOT NULL,"
                + PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LAT+" REAL NOT NULL, "
                + PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LON+" REAL NOT NULL "+");";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ PlaceContract.PlaceEntry.TABLE_NAME);
        onCreate(db);
    }
}
