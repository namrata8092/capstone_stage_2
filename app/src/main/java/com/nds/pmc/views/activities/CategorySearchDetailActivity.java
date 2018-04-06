package com.nds.pmc.views.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.Place;
import com.nds.pmc.views.fragments.ErrorFragment;
import com.nds.pmc.views.fragments.SearchResultDetailFragment;

/**
 * Created by Namrata on 1/11/2018.
 */

public class CategorySearchDetailActivity extends AppCompatActivity {
    private Place mPlace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPlace = getIntent().getParcelableExtra(Constants.PLACE_BUNDLE_KEY);
        if(mPlace!=null){
            String title = getString(R.string.search_detail_title, mPlace.getName());
            setTitle(title);
            SearchResultDetailFragment searchResultDetailFragment = SearchResultDetailFragment.newInstance(mPlace);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, searchResultDetailFragment).commit();
        }else{
            setTitle(getString(R.string.app_name));
            ErrorFragment errorFragment = ErrorFragment.newInstance(getResources().getString(R.string.error_detail_result));
            getSupportFragmentManager().beginTransaction().replace(R.id.container, errorFragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
