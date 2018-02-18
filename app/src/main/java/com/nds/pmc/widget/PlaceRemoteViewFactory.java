package com.nds.pmc.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nds.pmc.R;
import com.nds.pmc.model.Place;

import java.util.List;

/**
 * Created by Namrata Shah on 2/6/2018.
 */

public class PlaceRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private static final String PLACE_BUNDLE="placeBundle";
    private static final String PLACE_LIST="placeList";
    private static final String SELECTED_PLACE_BUNDLE_KEY = "selectedPlace";
    private static final String PLACE_INDEX_KEY="placeIndex";
    private int mSelectedPlaceIndex = 0;
    private List<Place> mPlaces;
    private Place mSelectedPlace;


    public PlaceRemoteViewFactory(Context context, Intent intent){
        this.mContext = context;
        Bundle bundle = intent.getBundleExtra(PLACE_BUNDLE);
        mPlaces = bundle.getParcelableArrayList(PLACE_LIST);
        mSelectedPlaceIndex = intent.getIntExtra(PLACE_INDEX_KEY, 0);
        mSelectedPlace = mPlaces.get(mSelectedPlaceIndex);
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(mPlaces == null)
            return 0;
        else
            return mPlaces.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        mSelectedPlace = mPlaces.get(position);
        RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.widget_grid_cell);
        view.setTextViewText(R.id.placeName, mSelectedPlace.getName());
//        view.setImageViewResource(R.id.categoryType, mSelectedPlace.getIconImage());
        Bundle bundle = new Bundle();
        bundle.putParcelable(SELECTED_PLACE_BUNDLE_KEY, mSelectedPlace);
        Intent recipeDetailIntent = new Intent();
        recipeDetailIntent.putExtra(PLACE_BUNDLE, bundle);
        view.setOnClickFillInIntent(R.id.cellContainer,recipeDetailIntent);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(mContext.getPackageName(), R.layout.widget_loading);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
