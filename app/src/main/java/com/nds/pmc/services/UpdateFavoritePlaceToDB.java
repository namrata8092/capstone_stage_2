package com.nds.pmc.services;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.nds.pmc.common.Constants;
import com.nds.pmc.dbo.PlaceContract;
import com.nds.pmc.model.Place;

/**
 * Created by Namrata on 1/15/2018.
 */
public class UpdateFavoritePlaceToDB extends AsyncTask {

    private Context mContext;
    private Place mPlaceModel;
    private FavoritePlaceUpdateListener updateListener;

    public UpdateFavoritePlaceToDB(Context cnt, Place model, FavoritePlaceUpdateListener listener){
        this.mContext = cnt;
        this.mPlaceModel = model;
        this.updateListener = listener;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        AddOrRemoveFavoritePlace();
        return null;
    }

    private void AddOrRemoveFavoritePlace() {
        Cursor placeCursor = mContext.getContentResolver().query(
                PlaceContract.PlaceEntry.CONTENT_URI,
                new String[]{PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID},
                PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID + " = ?",
                new String[]{mPlaceModel.getId()},
                null);

        if (placeCursor.moveToFirst()) {
            int rowDeleted = mContext.getContentResolver().delete(PlaceContract.PlaceEntry.CONTENT_URI,
                    PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID + " = ?",
                    new String[]{mPlaceModel.getId()});

            if (rowDeleted > 0) {
                updateListener.onSuccess(Constants.REMOVED_FROM_FAVORITE);
            } else {
                updateListener.onFailure();
            }

        } else {
            ContentValues values = new ContentValues();

            values.put(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID, mPlaceModel.getId());
            values.put(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_TITLE, mPlaceModel.getName());
            values.put(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ADDRESS, mPlaceModel.getAddress());
            values.put(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LAT, mPlaceModel.getLatitude());
            values.put(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LON, mPlaceModel.getLongitude());
            values.put(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_RATING, mPlaceModel.getRating());

            Uri insertedUri = mContext.getContentResolver().insert(PlaceContract.PlaceEntry.CONTENT_URI,values);

            long movieRowId = ContentUris.parseId(insertedUri);

            if (movieRowId > 0) {
                updateListener.onSuccess(Constants.ADDED_TO_FAVORITE);
            } else {
                updateListener.onFailure();
            }
        }
        placeCursor.close();
    }
}
