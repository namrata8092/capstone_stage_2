package com.nds.pmc.views.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.PlaceLocation;
import com.nds.pmc.views.fragments.FavoritePlaceListFragment;
import com.nds.pmc.views.fragments.SearchCategoryFragment;

/**
 * Created by Namrata on 10/17/2017.
 */

public class LauncherActivity extends AppCompatActivity {

    private static final String TAG = LauncherActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] navigationList;
    private PlaceLocation mLocation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        navigationList = getResources().getStringArray(R.array.navigation_option);
        
        mDrawerLayout = (DrawerLayout)findViewById(R.id.pmcDrawer);
        mDrawerList = (ListView)findViewById(R.id.drawerList);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                replaceContainerAsPerNavOption(navigationList[position]);
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mLocation = getIntent().getParcelableExtra(Constants.LOCATION_KEY);
        displayCategories(mLocation);        
    }

    private void displayCategories(PlaceLocation location) {
        SearchCategoryFragment searchCategoryFragment = SearchCategoryFragment.newInstance(location);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchCategoryFragment).commit();
    }

    private void replaceContainerAsPerNavOption(String option) {
        if(option.contains("Categories")){
            displayCategories(mLocation);
        }else if(option.contains("Favorite")){
            displayFavoritePlaces();
        }else if(option.contains("Exit")){
            finish();
        }
    }

    private void displayFavoritePlaces() {
        FavoritePlaceListFragment favoritePlaceListFragment = FavoritePlaceListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, favoritePlaceListFragment).commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

}
