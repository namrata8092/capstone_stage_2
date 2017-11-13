package com.nds.pmc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Namrata on 11/8/2017.
 */

public final class NetworkUtil {
    private NetworkUtil() {
    }

    public static boolean isWifiConnected(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                && (networkInfo.isConnected() || networkInfo.isConnectedOrConnecting())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMobileDataConnected(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                && (networkInfo.isConnected() || networkInfo.isConnectedOrConnecting())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDataNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return isWifiConnected(networkInfo) || isMobileDataConnected(networkInfo);
    }

}