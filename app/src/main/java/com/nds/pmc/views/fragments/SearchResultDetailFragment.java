package com.nds.pmc.views.fragments;

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
import com.nds.pmc.model.Place;
import com.nds.pmc.model.PlacesSearchResult;
import com.nds.pmc.views.adapters.SearchResultListAdapter;

/**
 * Created by Namrata on 1/11/2018.
 */

public class SearchResultDetailFragment extends Fragment{
    private Place mPlace;

    public static SearchResultDetailFragment newInstance(Place place){
        SearchResultDetailFragment fragment = new SearchResultDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY,place);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null && savedInstanceState.containsKey(Constants.PLACE_DETAIL_BUNDLE_KEY)){
            mPlace = savedInstanceState.getParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY);
        }else{
            mPlace = getArguments().getParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_detail, container, false );

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY, mPlace);
        super.onSaveInstanceState(outState);
    }
}
