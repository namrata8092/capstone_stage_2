package com.nds.pmc.tos.requests;

import android.net.Uri;

import com.nds.pmc.common.Constants;

/**
 * Created by Namrata on 11/9/2017.
 */

public class RequestWithParameters {
    private final String latitude;
    private final String longitude;
    private final String placeType;
    private final String apiKey;

    public RequestWithParameters(String lat, String lon, String types, String key){
        this.latitude = lat;
        this.longitude = lon;
        this.placeType = types;
        this.apiKey = key;
    }

    public String createRequest(){
        StringBuilder locationPath = new StringBuilder();
        locationPath.append(latitude).append(",").append(longitude);
        Uri.Builder uri = Uri.parse(Constants.BASE_URL).buildUpon();
                uri.appendQueryParameter(Constants.LOCATION_KEY, locationPath.toString())
                .appendQueryParameter(Constants.TYPES_KEY, placeType)
                .appendQueryParameter(Constants.RADIUS_KEY, Constants.RADIUS_VALUE)
                .appendQueryParameter(Constants.RANKBY_KEY, Constants.RANKBY_VALUE)
                .appendQueryParameter(Constants.API_KEY_KEY, apiKey).build();
        return  uri.toString();
    }
}
