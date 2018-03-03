package com.nds.pmc.views.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.Place;
import com.nds.pmc.model.PlacesSearchResult;
import com.nds.pmc.util.DeviceUtil;
import com.nds.pmc.views.adapters.SearchResultListAdapter;

import java.util.List;

/**
 * Created by Namrata on 11/20/2017.
 */

public class SearchResultListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private PlacesSearchResult mPlaceSearchResult;
    private List<Place> mPlaces;
    private int mSelectedPlaceIndex = 0;
    private Handler mHandler;

    public static SearchResultListFragment newInstance(PlacesSearchResult result){
        SearchResultListFragment fragment = new SearchResultListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SEARCH_RESULT_BUNDLE_KEY,result);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null && savedInstanceState.containsKey(Constants.SEARCH_RESULT_BUNDLE_KEY)){
            mPlaceSearchResult = savedInstanceState.getParcelable(Constants.SEARCH_RESULT_BUNDLE_KEY);
        }else if(getArguments()!=null){
            mPlaceSearchResult = getArguments().getParcelable(Constants.SEARCH_RESULT_BUNDLE_KEY);
        }
        if(savedInstanceState!=null && savedInstanceState.containsKey(Constants.SELECTED_PLACE_INDEX_KEY)){
            mSelectedPlaceIndex = savedInstanceState.getInt(Constants.SELECTED_PLACE_INDEX_KEY);
        }
        if(mPlaceSearchResult!=null){
            mPlaces = mPlaceSearchResult.getPlaces();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == Constants.DISPLAY_PLACE_DETAIL_MSG && !mPlaces.isEmpty()) {
                        displayDetailFragment(mPlaces.get(mSelectedPlaceIndex));
                    }
                }
            };
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_result, container, false );
        checkTwoPanelLayout(rootView);
        RecyclerView searchResultRecyclerView = (RecyclerView)rootView.findViewById(R.id.searchResultRecyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        searchResultRecyclerView.setLayoutManager(gridLayoutManager);

        SearchResultListAdapter searchResultListAdapter = new SearchResultListAdapter(
                getContext(), mPlaces, this);
        searchResultRecyclerView.setAdapter(searchResultListAdapter);

        if(DeviceUtil.isTwoPanelLayout() && mPlaces != null && mPlaces.size() > 0){
            mHandler.sendEmptyMessage(Constants.DISPLAY_PLACE_DETAIL_MSG);
        }
        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            DeviceUtil.setTwoPanelLayout(false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.SEARCH_RESULT_BUNDLE_KEY, mPlaceSearchResult);
        outState.putInt(Constants.SELECTED_PLACE_INDEX_KEY, mSelectedPlaceIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mPlaceSearchResult!=null && mPlaces.size() > 0 && !mPlaces.isEmpty()) {
            Place place = mPlaces.get(position);
            mSelectedPlaceIndex = position;
            displayPlaceDetails(place);
        }
    }

    private void displayPlaceDetails(Place place) {
        if(!DeviceUtil.isTwoPanelLayout()){
            displayDetailActivity(place);
        }else{
            displayDetailFragment(place);
        }
    }

    private void checkTwoPanelLayout(View rootView) {
        if (rootView.findViewById(R.id.detail_container) != null) {
            DeviceUtil.setTwoPanelLayout(true);
        }
    }

    private void displayDetailFragment(Place place) {
        SearchResultDetailFragment searchResultDetailFragment = SearchResultDetailFragment.newInstance(place);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, searchResultDetailFragment).commit();
    }

    private void displayDetailActivity(Place place) {
        Intent detailIntent = new Intent();
        detailIntent.setAction(Constants.ACTION_SEARCH_RESULT_DETAIL);
        detailIntent.putExtra(Constants.PLACE_BUNDLE_KEY, place);
        startActivity(detailIntent);
    }
}
