package com.nds.pmc.views.activities;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.nds.pmc.PMCApplication;
import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.common.NetworkRequestManager;
import com.nds.pmc.common.NetworkRequester;
import com.nds.pmc.converter.SearchResponseConverter;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        mCategorySearchResultActivity = CategorySearchResultActivity.this;
        mPMCApplication = (PMCApplication) mCategorySearchResultActivity.getApplication();
        mNetworkRequestManager = mPMCApplication.getNetworkRequestManager();

        setContentView(R.layout.activity_search_category_result);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mFragmentManager = getSupportFragmentManager();
        if (!NetworkUtil.isDataNetworkAvailable(this)) {
            displayErrorFragment(getResources().getString(R.string.network_error));
        } else if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            final RequestWithParameters rm = new RequestWithParameters(
                    bundle.getString(Constants.EXTRA_LOCATION_LATITUDE),
                    bundle.getString(Constants.EXTRA_LOCATION_LATITUDE),
                    bundle.getString(Constants.EXTRA_SEARCH_CATEGORY_KEY),
                    getResources().getString(R.string.API_KEY));
            showProgressBar();
            mNetworkRequestManager.createStringRequest(new WeakReference<NetworkRequester>(searchNetworkRequster), rm.createRequest(),
                    Constants.SEARCH_REQUEST_TAG);
        }
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
            if(response!=null && !TextUtils.isEmpty(response)){
                PlacesSearchResult result = SearchResponseConverter.getSearchResultModel(response);
                SearchResultListFragment searchResultListFragment = SearchResultListFragment.newInstance(result);
                mFragmentManager.beginTransaction().replace(R.id.main_container, searchResultListFragment).commit();
            }
        }
    };
}