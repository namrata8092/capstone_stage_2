package com.nds.pmc.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nds.pmc.common.Constants;

/**
 * Created by Namrata on 11/20/2017.
 */

public class SearchResultListFragment extends Fragment {

    public static SearchResultListFragment newInstance(String url){
        SearchResultListFragment fragment = new SearchResultListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL_KEY,url);
        fragment.setArguments(bundle);
        return fragment;
    }

}
