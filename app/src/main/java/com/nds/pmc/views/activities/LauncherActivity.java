package com.nds.pmc.views.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.PlaceLocation;
import com.nds.pmc.views.fragments.SearchCategoryFragment;

/**
 * Created by Namrata on 10/17/2017.
 */

public class LauncherActivity extends AppCompatActivity{

    private static final String TAG = LauncherActivity.class.getSimpleName();

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        PlaceLocation location = getIntent().getParcelableExtra(Constants.LOCATION_KEY);
        SearchCategoryFragment searchCategoryFragment = SearchCategoryFragment.newInstance(location);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchCategoryFragment).commit();
    }
}
