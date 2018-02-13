package com.nds.pmc.views.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.nds.pmc.model.PlacesSearchResult;
import com.nds.pmc.util.DeviceUtil;
import com.nds.pmc.views.adapters.SearchResultListAdapter;

/**
 * Created by Namrata on 11/20/2017.
 */

public class SearchResultListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private PlacesSearchResult mPlaceSearchResult;

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
        }else{
            mPlaceSearchResult = getArguments().getParcelable(Constants.SEARCH_RESULT_BUNDLE_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_result, container, false );

        RecyclerView searchResultRecyclerView = (RecyclerView)rootView.findViewById(R.id.searchResultRecyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        searchResultRecyclerView.setLayoutManager(gridLayoutManager);

        SearchResultListAdapter searchResultListAdapter = new SearchResultListAdapter(
                getContext(), mPlaceSearchResult.getPlaces(), this);
        searchResultRecyclerView.setAdapter(searchResultListAdapter);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.SEARCH_RESULT_BUNDLE_KEY, mPlaceSearchResult);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(!DeviceUtil.isTwoPanelLayout()){
            Intent detailIntent = new Intent();
            detailIntent.setAction(Constants.ACTION_SEARCH_RESULT_DETAIL);
            detailIntent.putExtra(Constants.PLACE_DETAIL_BUNDLE_KEY, mPlaceSearchResult.getPlaces().get(position));
            startActivity(detailIntent);
        }else{

        }

    }
}
