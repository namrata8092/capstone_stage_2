package com.nds.pmc.views.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.PlaceLocation;
import com.nds.pmc.util.DeviceUtil;
import com.nds.pmc.util.LogUtil;
import com.nds.pmc.util.NetworkUtil;
import com.nds.pmc.views.fragments.ErrorFragment;

/**
 * Created by Namrata on 1/15/2018.
 */

public class SplashActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private static Location lastLocation;
    private LocationRequest locationRequest;
    private GoogleApiClient mGoogleApiClient;
    private FusedLocationProviderClient mFusedLocationClient;

    private SharedPreferences mSharedPreferences;
    private FragmentManager mFragmentManager;

    private FrameLayout mContainer;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContainer = (FrameLayout)findViewById(R.id.mainContainer);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        mFragmentManager = getSupportFragmentManager();

        if (!NetworkUtil.isDataNetworkAvailable(this)) {
            displayErrorMsg(getResources().getString(R.string.network_error));
        }else{
            createGoogleApi();
            if (DeviceUtil.checkLocationPermissionAvailable(getApplicationContext())) {
                LogUtil.d(TAG, "checkPermission");
                if (DeviceUtil.isLocationEnabled(this)) {
                    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                    mFusedLocationClient.getLastLocation()
                            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        saveLocationOnDevice(location);
                                        displaySearchCategory(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                                    }
                                }
                            });
                } else {
                    displaySearchCategoryWithPreviouslySavedLocation();
                }
            } else {
                LogUtil.d(TAG, "askPermission");
                DeviceUtil.requestLocationPermission(SplashActivity.this);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mGoogleApiClient!=null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient!=null){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.REQ_LOCATION_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogUtil.d(TAG, "PERMISSION_GRANTED");
                    getLastKnownLocation();
                } else {
                    LogUtil.d(TAG, "permissionsDenied");
                    permissionsDenied();
                }
                break;
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLastKnownLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLastKnownLocation() {
        if (DeviceUtil.checkLocationPermissionAvailable(getApplicationContext())) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (lastLocation != null) {
                saveLocationOnDevice(lastLocation);
            } else {
                displaySearchCategoryWithPreviouslySavedLocation();
            }
            startLocationUpdates();
        } else DeviceUtil.requestLocationPermission(SplashActivity.this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    private void displaySearchCategoryWithPreviouslySavedLocation() {
        String oldLatitude = mSharedPreferences.getString(Constants.KEY_LATITUDE, null);
        String oldLongitude = mSharedPreferences.getString(Constants.KEY_LONGITUDE, null);
        if (oldLatitude != null && oldLongitude != null) {
            displaySearchCategory(oldLatitude, oldLongitude);
        } else {
            displayErrorMsg(getResources().getString(R.string.error_location_off_no_saved_location));
        }
    }

    private void displaySearchCategory(String lat, String lon) {
        mContainer.setVisibility(View.VISIBLE);
        PlaceLocation location = new PlaceLocation(lat, lon);
        Intent launchAppIntent = new Intent();
        launchAppIntent.setAction(Constants.ACTION_LAUNCH_PMC_APP);
        launchAppIntent.putExtra(Constants.LOCATION_KEY, location);
        startActivity(launchAppIntent);
        finish();
    }

    private void displayErrorMsg(String errorMsg) {
        mContainer.setVisibility(View.VISIBLE);
        ErrorFragment errorFragment = ErrorFragment.newInstance(errorMsg);
        if (!isFinishing()) {
            mFragmentManager.beginTransaction().replace(R.id.mainContainer, errorFragment).commit();
        }
    }

    private void permissionsDenied() {
        displayErrorMsg(getResources().getString(R.string.error_location_permission_denied));
    }

    private void createGoogleApi() {
        LogUtil.d(TAG, "createGoogleApi");
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void saveLocationOnDevice(Location location) {
        LogUtil.d(TAG, "saveLocationOnDevice");
        mSharedPreferences.edit().putString(Constants.KEY_LATITUDE, Double.toString(location.getLatitude())).apply();
        mSharedPreferences.edit().putString(Constants.KEY_LONGITUDE, Double.toString(location.getLongitude())).apply();
    }



    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        LogUtil.d(TAG, "startLocationUpdates");
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(Constants.UPDATE_INTERVAL)
                .setFastestInterval(Constants.FASTEST_INTERVAL);

        if (DeviceUtil.checkLocationPermissionAvailable(getApplicationContext())){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
            displaySearchCategoryWithPreviouslySavedLocation();
        }
    }


}
