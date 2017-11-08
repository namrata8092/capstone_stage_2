package com.nds.pmc.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.nds.pmc.R;
import com.nds.pmc.views.adapters.CategoryAdapter;
import com.nds.pmc.views.fragments.SearchCategoryFragment;

/**
 * Created by Namrata on 10/17/2017.
 */

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        FragmentManager fragmentManager = getSupportFragmentManager();
        SearchCategoryFragment searchCategoryFragment = new SearchCategoryFragment();
        fragmentManager.beginTransaction().replace(R.id.container, searchCategoryFragment).commit();
    }


}
