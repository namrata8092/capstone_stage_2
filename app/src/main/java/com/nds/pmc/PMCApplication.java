package com.nds.pmc;

import android.app.Application;

import com.nds.pmc.common.NetworkRequestManager;

/**
 * Created by Namrata on 11/3/2017.
 */

public class PMCApplication extends Application {
    private NetworkRequestManager networkRequestManager;

    @Override
    public void onCreate() {
        super.onCreate();
        networkRequestManager = NetworkRequestManager.getInstance(PMCApplication.this);
        networkRequestManager.initializeNetworkManager();
    }

    public synchronized NetworkRequestManager getNetworkRequestManager(){
        return networkRequestManager;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if(networkRequestManager!=null)
            networkRequestManager.terminateNetworkManager();
    }
}
