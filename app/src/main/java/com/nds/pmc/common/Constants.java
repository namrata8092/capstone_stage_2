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
    public static final String ACTION_LAUNCH_PMC_APP ="com.nds.pmc.action.launch_pmc";
    public static final String ACTION_SEARCH_CATEGORY_RESULT ="com.nds.pmc.action.search_category";
    public static final String ACTION_SEARCH_RESULT_DETAIL ="com.nds.pmc.action.search_result_details";
    public static final String EXTRA_SEARCH_CATEGORY_KEY = "search_category_key";
    public static final String EXTRA_SEARCH_CATEGORY_NAME = "search_category_name";
    public static final String SEARCH_RESULT_BUNDLE_KEY = "search_result_bundle";
    public static final String PLACE_DETAIL_BUNDLE_KEY = "place_detail_bundle";
    public static final String ERROR_MSG_KEY = "error_key";
    public static final String SEARCH_REQUEST_TAG = "search_request_tag";
    public static final int REQ_PERMISSION = 1000;
    public static final String KEY_LATITUDE="latitude";
    public static final String KEY_LONGITUDE="longitude";
    public static final int UPDATE_INTERVAL = 1000;
    public static final int FASTEST_INTERVAL = 900;
    public static final int REMOVED_FROM_FAVORITE = 0;
    public static final int ADDED_TO_FAVORITE = 1;
}