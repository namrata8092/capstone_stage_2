package com.nds.pmc.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.PlaceLocation;
import com.nds.pmc.views.adapters.CategoryAdapter;

/**
 * Created by Namrata on 11/7/2017.
 */

public class SearchCategoryFragment extends Fragment implements AdapterView.OnItemClickListener{
    private String[] categoryKey;
    private PlaceLocation placeLocation;

    public static SearchCategoryFragment newInstance(PlaceLocation location){
        SearchCategoryFragment fragment = new SearchCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.LOCATION_KEY, location);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null && savedInstanceState.containsKey(Constants.LOCATION_KEY)){
            placeLocation = savedInstanceState.getParcelable(Constants.LOCATION_KEY);
        }else{
            placeLocation = getArguments().getParcelable(Constants.LOCATION_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_categories, container, false );

        categoryKey = getContext().getResources().getStringArray(R.array.category_key);

        RecyclerView searchRecyclerView = (RecyclerView)rootView.findViewById(R.id.categoryRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.no_of_grid_cells));
        searchRecyclerView.setLayoutManager(gridLayoutManager);

        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), this);
        searchRecyclerView.setAdapter(categoryAdapter);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent searchResultActivity = new Intent();
        searchResultActivity.setAction(Constants.ACTION_SEARCH_CATEGORY_RESULT);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_SEARCH_CATEGORY_KEY, categoryKey[position]);
        bundle.putParcelable(Constants.LOCATION_KEY, placeLocation);
        searchResultActivity.putExtras(bundle);
        startActivity(searchResultActivity);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.LOCATION_KEY, placeLocation);
        super.onSaveInstanceState(outState);
    }
}
