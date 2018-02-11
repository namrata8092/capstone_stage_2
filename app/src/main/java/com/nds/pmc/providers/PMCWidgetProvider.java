package com.nds.pmc.providers;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.nds.pmc.R;
import com.nds.pmc.model.Place;
import com.nds.pmc.services.PlaceRemoteViewService;
import com.nds.pmc.util.LogUtil;
import com.nds.pmc.views.activities.CategorySearchDetailActivity;

import java.util.ArrayList;

import static com.nds.pmc.services.PMCWidgetService.startPMCWidgetService;

/**
 * Created by Namrata Shah on 2/9/2018.
 */

public class PMCWidgetProvider extends AppWidgetProvider {

    private static final String TAG=PMCWidgetProvider.class.getSimpleName();
    private static ArrayList<Place> mPlaces;
    private static final String SELECTED_PLACE_BUNDLE_KEY = "selectedPlace";
    private static final String PLACE_INDEX_KEY="placeIndex";
    private static final String PLACE_BUNDLE="placeBundle";
    private static final String PLACE_LIST="placeList";
    private static int PLACE_INDEX = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        LogUtil.d(TAG,"onUpdate called "+ PLACE_INDEX);
        startPMCWidgetService(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void updateWidget(Context context, AppWidgetManager appWidgetManager,
                                    int appWidgetIds, ArrayList<Place> mPlaces) {
        LogUtil.d(TAG,"updateWidget called "+ PLACE_INDEX);
        RemoteViews views = getPlacesGridView(context, appWidgetIds, mPlaces);
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }

    private static RemoteViews getPlacesGridView(Context context, int appWidgetIds,
                                                 ArrayList<Place> places) {
        LogUtil.d(TAG,"getPlacesGridView");
        mPlaces = places;
        Place selectedPlace = mPlaces.get(PLACE_INDEX);
        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget_grid_cell);
        view.setTextViewText(R.id.placeName, selectedPlace.getName());
//        view.setImageViewResource(R.id.categoryType, R.drawable.airport);

        Intent intent = new Intent(context, PlaceRemoteViewService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PLACE_LIST, mPlaces);
        intent.putExtra(PLACE_INDEX_KEY, PLACE_INDEX);
        intent.putExtra(PLACE_BUNDLE, bundle);
        view.setRemoteAdapter( R.id.cellContainer, intent);

        Intent appIntent = new Intent(context, CategorySearchDetailActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setPendingIntentTemplate(R.id.cellContainer, appPendingIntent);

        return view;
    }



    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {
        startPMCWidgetService(context);
        LogUtil.d(TAG,"onAppWidgetOptionsChanged called");
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d(TAG,"onReceive called "+ PLACE_INDEX);
        super.onReceive(context, intent);
    }
}
