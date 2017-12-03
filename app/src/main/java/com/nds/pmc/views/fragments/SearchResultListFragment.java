package com.nds.pmc.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.nds.pmc.common.Constants;
import com.nds.pmc.model.PlacesSearchResult;

/**
 * Created by Namrata on 11/20/2017.
 */

public class SearchResultListFragment extends Fragment {
    private PlacesSearchResult mPlaceSearchResult;

    public static SearchResultListFragment newInstance(PlacesSearchResult result){
        SearchResultListFragment fragment = new SearchResultListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SEARCH_BUNDLE_KEY,result);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
