package com.nds.pmc.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.dbo.PlaceContract;
import com.nds.pmc.model.Place;
import com.nds.pmc.views.adapters.SearchResultListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Namrata Shah on 1/15/2018.
 */

public class FavoritePlaceListFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener, AdapterView.OnItemClickListener {

    private static final int FAVORITE_PLACE_LOADER_ID = 20;
    private LoaderManager.LoaderCallbacks<Cursor> mFavoritePlaceFromDBLoaderListener;
    private SharedPreferences mPlacesPreferences;
    private Context mContext;
    private ProgressBar mProgress;
    private List<Place> mFavoritePlaceList;
    private TextView mErrorMsgTV;
    private static final String[] FAV_PLACE_COLUMNS = {
            PlaceContract.PlaceEntry._ID,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_TITLE,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ADDRESS,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LAT,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LON,
            PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_RATING,
            PlaceContract.PlaceEntry.COLUMN_NAME_CATEGORY_ICON
    };
    private SearchResultListAdapter placeListAdapter;
    private RecyclerView searchResultRecyclerView;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (getContext().getString(R.string.preference_place_delete_key).equals(key) && sharedPreferences.getBoolean(key, false)) {
            sharedPreferences.edit().putBoolean(key, false).apply();
            fetchFavoritePlacesFromDB();
        }
    }

    public static FavoritePlaceListFragment newInstance() {
        FavoritePlaceListFragment fragment = new FavoritePlaceListFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mPlacesPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mFavoritePlaceFromDBLoaderListener = new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new AsyncTaskLoader<Cursor>(mContext) {
                    Cursor favoriteMoviesCursor = null;

                    @Override
                    protected void onStartLoading() {
                        displayProgressBar();
                        if (favoriteMoviesCursor != null) {
                            deliverResult(favoriteMoviesCursor);
                        } else {
                            forceLoad();
                        }
                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public Cursor loadInBackground() {
                        try {
                            return mContext.getContentResolver().query(
                                    PlaceContract.PlaceEntry.CONTENT_URI, FAV_PLACE_COLUMNS, null, null, null);
                        } catch (Exception e) {
                            hideProgressBar();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                hideProgressBar();
                mFavoritePlaceList = getDataFromCursor(data);
                if (mFavoritePlaceList == null) {
                    mErrorMsgTV.setText(getResources().getString(R.string.no_favorite_place));
                    mErrorMsgTV.setVisibility(View.VISIBLE);
                    searchResultRecyclerView.setVisibility(View.GONE);
                    if (placeListAdapter != null)
                        placeListAdapter.notifyDataSetChanged();
                    return;
                }
                setFavoritePlacesAdapter();
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mFavoritePlaceList = null;
                if (placeListAdapter != null)
                    placeListAdapter.notifyDataSetChanged();
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mPlacesPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        mPlacesPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchFavoritePlacesFromDB();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite_places, container, false);
        mProgress = (ProgressBar) rootView.findViewById(R.id.progress);
        mErrorMsgTV = (TextView) rootView.findViewById(R.id.noPlace);
        searchResultRecyclerView = (RecyclerView) rootView.findViewById(R.id.searchResultRecyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        searchResultRecyclerView.setLayoutManager(gridLayoutManager);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detailIntent = new Intent();
        detailIntent.setAction(Constants.ACTION_SEARCH_RESULT_DETAIL);
        detailIntent.putExtra(Constants.PLACE_DETAIL_BUNDLE_KEY, mFavoritePlaceList.get(position));
        startActivity(detailIntent);
    }

    private void setFavoritePlacesAdapter() {
        placeListAdapter = new SearchResultListAdapter(
                getContext(), mFavoritePlaceList, this);
        searchResultRecyclerView.setAdapter(placeListAdapter);
    }

    private void fetchFavoritePlacesFromDB() {
        LoaderManager loaderManager = getLoaderManager();
        Loader<Cursor> favoriteMovieLoader = loaderManager.getLoader(FAVORITE_PLACE_LOADER_ID);
        if (favoriteMovieLoader == null) {
            loaderManager.initLoader(FAVORITE_PLACE_LOADER_ID, null, mFavoritePlaceFromDBLoaderListener);
        } else {
            loaderManager.restartLoader(FAVORITE_PLACE_LOADER_ID, null, mFavoritePlaceFromDBLoaderListener);
        }
    }

    private List<Place> getDataFromCursor(Cursor data) {
        List<Place> placeModelList = null;
        int placeIDIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ID);
        int placeTitleIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_TITLE);
        int placeAddressIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_ADDRESS);
        int placeLatIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LAT);
        int placeLonIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_LON);
        int placeRatingIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_PLACE_RATING);
        int categoryIconIndex = data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME_CATEGORY_ICON);

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
                    model.setIconImage(data.getString(categoryIconIndex));
                    placeModelList.add(model);
                }
            } catch (Exception e) {

            } finally {
                data.close();
            }
        }
        return placeModelList;
    }


    private void displayProgressBar() {
        mProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgress.setVisibility(View.GONE);
    }


}
