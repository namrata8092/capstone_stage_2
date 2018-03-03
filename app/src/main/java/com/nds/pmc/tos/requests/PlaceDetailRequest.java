package com.nds.pmc.tos.requests;

import android.net.Uri;

import com.nds.pmc.common.Constants;

/**
 * Created by Namrata on 2/27/2018.
 */

public class PlaceDetailRequest {

    private final String placeID;
    private final String apiKey;

    public PlaceDetailRequest(String placeid, String key){
        this.placeID = placeid;
        this.apiKey = key;
    }

    public String createRequest(){
        Uri.Builder uri = Uri.parse(Constants.DETAIL_BASE_URL).buildUpon();
        uri.appendQueryParameter(Constants.PLACE_ID_KEY, placeID)
                .appendQueryParameter(Constants.API_KEY_KEY, apiKey).build();
        return  uri.toString();
    }
}
