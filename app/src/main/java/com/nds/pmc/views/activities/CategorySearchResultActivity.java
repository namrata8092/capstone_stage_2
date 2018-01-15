package com.nds.pmc.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nds.pmc.PMCApplication;
import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.common.NetworkRequestManager;
import com.nds.pmc.common.NetworkRequester;
import com.nds.pmc.converter.SearchResponseConverter;
import com.nds.pmc.model.PlaceLocation;
import com.nds.pmc.model.PlacesSearchResult;
import com.nds.pmc.tos.requests.RequestWithParameters;
import com.nds.pmc.util.NetworkUtil;
import com.nds.pmc.views.fragments.ErrorFragment;
import com.nds.pmc.views.fragments.SearchResultListFragment;

import java.lang.ref.WeakReference;

/**
 * Created by Namrata on 11/22/2017.
 */

public class CategorySearchResultActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private FragmentManager mFragmentManager;
    private Context mContext;
    private CategorySearchResultActivity mCategorySearchResultActivity;
    private PMCApplication mPMCApplication;
    private NetworkRequestManager mNetworkRequestManager;
    private PlaceLocation location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        mCategorySearchResultActivity = CategorySearchResultActivity.this;
        mPMCApplication = (PMCApplication) mCategorySearchResultActivity.getApplication();
        mNetworkRequestManager = mPMCApplication.getNetworkRequestManager();

        setContentView(R.layout.activity_search_category_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mFragmentManager = getSupportFragmentManager();
        if (!NetworkUtil.isDataNetworkAvailable(this)) {
            displayErrorFragment(getResources().getString(R.string.network_error));
        } else if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String title = getString(R.string.search_title, bundle.getString(Constants.EXTRA_SEARCH_CATEGORY_NAME));
            setTitle(title);
            location = bundle.getParcelable(Constants.LOCATION_KEY);
            final RequestWithParameters rm = new RequestWithParameters(
                    location.getLatitude(),
                    location.getLongitude(),
                    bundle.getString(Constants.EXTRA_SEARCH_CATEGORY_KEY),
                    getResources().getString(R.string.PLACES_API_KEY));
            showProgressBar();
            mNetworkRequestManager.createStringRequest(new WeakReference<NetworkRequester>(searchNetworkRequster), rm.createRequest(),
                    Constants.SEARCH_REQUEST_TAG);
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

    private void displayErrorFragment(String errorMsg) {
        ErrorFragment errorFragment = ErrorFragment.newInstance(errorMsg);
        mFragmentManager.beginTransaction().replace(R.id.main_container, errorFragment).commit();
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private NetworkRequester searchNetworkRequster = new NetworkRequester() {
        @Override
        public void onFailure(Throwable error) {
            hideProgressBar();
            displayErrorFragment(getResources().getString(R.string.network_error));
        }

        @Override
        public void onSuccess(String response) {
            hideProgressBar();
            if (response != null && !TextUtils.isEmpty(response)) {
                PlacesSearchResult result = SearchResponseConverter.getSearchResultModel(response);
                SearchResultListFragment searchResultListFragment = SearchResultListFragment.newInstance(result);
                mFragmentManager.beginTransaction().replace(R.id.main_container, searchResultListFragment).commit();
            }
        }
    };
}
