package com.nds.pmc.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.nds.pmc.common.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

    public static String getAddress(double lat, double lng, Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            return add;
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

}
