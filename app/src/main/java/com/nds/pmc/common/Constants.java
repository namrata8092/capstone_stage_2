package com.nds.pmc.common;

/**
 * Created by Namrata on 11/9/2017.
 */

public final class Constants {
    private Constants(){}
    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    public static final String LOCATION_KEY = "location";
    public static final String TYPES_KEY = "types";
    public static final String RADIUS_KEY = "radius";
    public static final String RANKBY_KEY = "rankby";
    public static final String API_KEY_KEY = "key";
    public static final String RADIUS_VALUE = "30000";
    public static final String RANKBY_VALUE = "prominence";
    public static final String ACTION_SEARCH_CATEGORY_RESULT ="com.nds.pmc.action.search_category";
    public static final String EXTRA_SEARCH_CATEGORY_KEY = "search_category_key";
    public static final String SEARCH_BUNDLE_KEY = "search_bundle";
    public static final String ERROR_MSG_KEY = "error_key";
    public static final String SEARCH_REQUEST_TAG = "search_request_tag";
    public static final int REQ_PERMISSION = 1000;
    public static final String KEY_LATITUDE="latitude";
    public static final String KEY_LONGITUDE="longitude";
    public static final int UPDATE_INTERVAL = 1000;
    public static final int FASTEST_INTERVAL = 900;
}