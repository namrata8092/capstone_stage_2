package com.nds.pmc.providers;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
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
    private static int PLACE_INDEX = 0;
    private static Context mContext;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        LogUtil.d(TAG,"onUpdate called "+ PLACE_INDEX);
        mContext = context;
        startPMCWidgetService(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void updateWidget(Context context, AppWidgetManager appWidgetManager,
                                    int appWidgetIds, ArrayList<Place> mPlaces) {
        LogUtil.d(TAG,"updateWidget called "+ PLACE_INDEX);
        mContext = context;
        RemoteViews views = getPlacesGridView(context, appWidgetIds, mPlaces);
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }

    private static RemoteViews getPlacesGridView(Context context, int appWidgetIds,
                                                 ArrayList<Place> places) {
        LogUtil.d(TAG,"getPlacesGridView");

        mPlaces = places;
        final Place selectedPlace = mPlaces.get(PLACE_INDEX);
        selectedPlace.setWidgetEntry(true);
        final RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget_grid_cell);
        view.setTextViewText(R.id.placeName, selectedPlace.getName());
        final AppWidgetTarget appWidgetTarget = new AppWidgetTarget( context, view, R.id.categoryType, appWidgetIds);


        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Glide.with(mContext.getApplicationContext()).load(selectedPlace.getIconImage())
                        .asBitmap()
                        .into(appWidgetTarget);
                pushWidgetUpdate(mContext, view);
            }
        };
        mainHandler.post(myRunnable);


        Intent intent = new Intent(context, PlaceRemoteViewService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
        intent.putExtra(Constants.PLACE_BUNDLE_KEY,selectedPlace);
        view.setRemoteAdapter( R.id.cellContainer, intent);

        Intent appIntent = new Intent(context, CategorySearchDetailActivity.class);
        appIntent.setAction(Constants.ACTION_SEARCH_RESULT_DETAIL);
        appIntent.putExtra(Constants.PLACE_BUNDLE_KEY,selectedPlace);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.cellContainer, appPendingIntent);

        return view;
    }

    public static void pushWidgetUpdate(Context context, RemoteViews rv) {
        ComponentName myWidget = new ComponentName(context, PMCWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, rv);
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
