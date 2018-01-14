package com.nds.pmc.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.Place;
import com.nds.pmc.views.fragments.ErrorFragment;
import com.nds.pmc.views.fragments.SearchResultDetailFragment;
import com.nds.pmc.views.fragments.SearchResultListFragment;

/**
 * Created by Namrata on 1/11/2018.
 */

public class CategorySearchDetailActivity extends AppCompatActivity {
    private Place mPlace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        mPlace = getIntent().getParcelableExtra(Constants.PLACE_DETAIL_BUNDLE_KEY);
        if(mPlace!=null){
            SearchResultDetailFragment searchResultDetailFragment = SearchResultDetailFragment.newInstance(mPlace);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, searchResultDetailFragment).commit();
        }else{
            ErrorFragment errorFragment = ErrorFragment.newInstance(getResources().getString(R.string.error_detail_result));
            getSupportFragmentManager().beginTransaction().replace(R.id.container, errorFragment).commit();
        }
    }


}
