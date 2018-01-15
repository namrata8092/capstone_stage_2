package com.nds.pmc.dbo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Namrata on 1/15/2018.
 */


public class PlaceContentProvider extends ContentProvider {

    private PlaceDBHelper mPlaceDBHelper;
    private static final int FAV_PLACE_TASK = 100;
    private static final int FAV_SELECTED_PLACE_TASK = 101;
    private static UriMatcher sUriMatcher = buildUriMatcher();


    private static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PlaceContract.AUTHORITY, PlaceContract.FAVORITE_PATH, FAV_PLACE_TASK);
        uriMatcher.addURI(PlaceContract.AUTHORITY, PlaceContract.FAVORITE_PATH+"/#", FAV_SELECTED_PLACE_TASK);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mPlaceDBHelper = new PlaceDBHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase movieDb = mPlaceDBHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor queriedCursor;
        switch (match){
            case FAV_PLACE_TASK:
                queriedCursor = movieDb.query(PlaceContract.PlaceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new SQLiteException("Query failed "+uri);
        }
        queriedCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return queriedCursor;
    }

    /**
     * returns best matching uri
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        final int uriMatching = sUriMatcher.match(uri);
        switch (uriMatching){
            case FAV_PLACE_TASK:
                return PlaceContract.PlaceEntry.CONTENT_TYPE_URI;
            case FAV_SELECTED_PLACE_TASK:
                return PlaceContract.PlaceEntry.CONTENT_ITEM_TYPE_URI;
        }
        throw new UnsupportedOperationException("Unknown uri "+uri);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase movieDB = mPlaceDBHelper.getWritableDatabase();
        final int uriMatch = sUriMatcher.match(uri);
        Uri returnUri;
        switch (uriMatch){
            case FAV_PLACE_TASK:
                long insertionId = movieDB.insert(PlaceContract.PlaceEntry.TABLE_NAME, null, values);
                if(insertionId > 0){
                    returnUri = PlaceContract.PlaceEntry.buildSelectedPlaceUri(insertionId);
                }else{
                    throw new SQLiteException("Insertion failed "+uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase movieDB = mPlaceDBHelper.getWritableDatabase();
        final int uriMatch = sUriMatcher.match(uri);
        int rowDeleted = 0;
        switch (uriMatch) {
            case FAV_PLACE_TASK:
                rowDeleted = movieDB.delete(PlaceContract.PlaceEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
        if (rowDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase movieDB = mPlaceDBHelper.getWritableDatabase();
        final int uriMatch = sUriMatcher.match(uri);
        int rowUpdated = 0;
        switch (uriMatch) {
            case FAV_PLACE_TASK:
                rowUpdated = movieDB.update(PlaceContract.PlaceEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
        if (rowUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
