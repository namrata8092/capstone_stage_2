package com.nds.pmc.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.nds.pmc.common.Constants;

/**
 * Created by Namarata on 12/14/2017.
 */

public final class DeviceUtil {
    private DeviceUtil(){}
    public static boolean checkLocationPermissionAvailable(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    public static void requestLocationPermission(Activity context) {
        ActivityCompat.requestPermissions( context,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                Constants.REQ_PERMISSION
        );
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), "location_mode");
            } catch (Settings.SettingNotFoundException var3) {
                var3.printStackTrace();
            }
            return locationMode != 0;
        } else {
            String locationProviders = Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed");
            return !TextUtils.isEmpty(locationProviders);
        }
    }

}
