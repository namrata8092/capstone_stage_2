package com.nds.pmc.services;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;

import com.nds.pmc.R;
import com.nds.pmc.dbo.PlaceContract;
import com.nds.pmc.model.Place;
import com.nds.pmc.providers.PMCWidgetProvider;
import com.nds.pmc.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by Namrata Shah on 2/9/2018.
 */

public class PMCWidgetService extends IntentService {
    private static final String TAG = "TEST"+PMCWidgetService.class.getSimpleName();
    private Context mContext;
    private static final String ACTION_UPDATE_WIDGET = "com.nds.pmc.UPDATE_BAKING_RECIPE";
    private static final String[] FAV_PLACE_COLUMNS = {
            PlaceContract.PlaceEntry._ID,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_TITLE,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ADDRESS,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LAT,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LON,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_RATING
    };

    public static void startPMCWidgetService(Context context) {
        Intent intent = new Intent(context, PMCWidgetService.class);
        intent.setAction(ACTION_UPDATE_WIDGET);
        context.startService(intent);

    }

    public PMCWidgetService() {
        super("PMCWidgetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null && ACTION_UPDATE_WIDGET.equals(intent.getAction())) {
            mContext = getApplicationContext();
            Cursor cursor = mContext.getContentResolver().query(
                    PlaceContract.PlaceEntry.CONTENT_URI, FAV_PLACE_COLUMNS, null, null, null);
            if(cursor!=null && cursor.getCount() > 0){
                ArrayList<Place> places = getDataFromCursor(cursor);
                showWidgetData(places, mContext);
            }
        }
    }

    private ArrayList<Place> getDataFromCursor(Cursor data) {
        ArrayList<Place> placeModelList = null;
        int placeIDIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID);
        int placeTitleIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_TITLE);
        int placeAddressIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ADDRESS);
        int placeLatIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LAT);
        int placeLonIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LON);
        int placeRatingIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_RATING);

        if (data != null && data.getCount() > 0) {
            placeModelList = new ArrayList<>();
            try {
                for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                    Place model = new Place(data.getDouble(placeLatIndex),
                            data.getDouble(placeLonIndex),
                            data.getString(placeTitleIndex));
                    model.setAddress(data.getString(placeAddressIndex));
                    model.setId(data.getString(placeIDIndex));
                    model.setRating(data.getDouble(placeRatingIndex));
                    placeModelList.add(model);
                }
            } catch (Exception e) {

            } finally {
                data.close();
            }
        }
        return placeModelList;
    }

    private void showWidgetData(ArrayList<Place> places, Context mContext) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(mContext, PMCWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.pmc_grid_view);
        for (int widgetId : appWidgetIds) {
            LogUtil.d( TAG," id " + widgetId);
            PMCWidgetProvider.updateWidget(mContext, appWidgetManager, widgetId, places);
        }
    }
}
