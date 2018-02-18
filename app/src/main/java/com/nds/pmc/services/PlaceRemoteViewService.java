package com.nds.pmc.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.nds.pmc.widget.PlaceRemoteViewFactory;

/**
 * Created by Namrata Shah on 2/6/2018.
 */

public class PlaceRemoteViewService extends RemoteViewsService {
    private static final String TAG = PlaceRemoteViewService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new PlaceRemoteViewFactory(getApplicationContext(), intent);
    }
}
