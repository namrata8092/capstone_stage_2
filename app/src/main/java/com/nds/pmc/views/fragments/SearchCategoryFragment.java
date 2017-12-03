package com.nds.pmc.views.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.tos.requests.RequestWithParameters;
import com.nds.pmc.views.adapters.CategoryAdapter;

/**
 * Created by Namrata on 11/7/2017.
 */

public class SearchCategoryFragment extends Fragment implements AdapterView.OnItemClickListener{
    private RecyclerView mSearchRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private CategoryAdapter mCategoryAdapter;
    private String[] categoryKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_categories_layout, container, false );
        categoryKey = getContext().getResources().getStringArray(R.array.category_key);
        mSearchRecyclerView = (RecyclerView)rootView.findViewById(R.id.categoryRecyclerView);
        mGridLayoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.no_of_grid_cells));
        mSearchRecyclerView.setLayoutManager(mGridLayoutManager);
        mCategoryAdapter = new CategoryAdapter(getContext(), this);
        mSearchRecyclerView.setAdapter(mCategoryAdapter);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent searchResultActivity = new Intent();
        searchResultActivity.setAction(Constants.ACTION_SEARCH_CATEGORY_RESULT);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_SEARCH_CATEGORY_KEY, categoryKey[position]);
        bundle.putString(Constants.EXTRA_LOCATION_LATITUDE, "40.5743");
        bundle.putString(Constants.EXTRA_LOCATION_LONGITUDE, "74.6099");
        searchResultActivity.putExtras(bundle);
        startActivity(searchResultActivity);
    }
}
