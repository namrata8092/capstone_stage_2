package com.nds.pmc.views.activities;

import android.content.Context;
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
import com.nds.pmc.model.PlaceLocation;
import com.nds.pmc.model.PlacesSearchResult;
import com.nds.pmc.tos.requests.RequestWithParameters;
import com.nds.pmc.util.NetworkUtil;
import com.nds.pmc.views.fragments.ErrorFragment;
import com.nds.pmc.views.fragments.SearchResultListFragment;

import java.lang.ref.WeakReference;

/**
 * Created by Namrata on 1/11/2018.
 */

public class CategorySearchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
