package com.nds.pmc.dbo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Namrata on 1/15/2018.
 */


public final class PlaceContract {
    private PlaceContract() {
    }

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.nds.pmc.pmcprovider";
    public static final Uri BASE_URI = Uri.parse(SCHEME + AUTHORITY);
    public static final String FAVORITE_PATH="favoritePlace";

    public static class PlaceEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(FAVORITE_PATH).build();

        public static final String CONTENT_TYPE_URI = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + FAVORITE_PATH;

        public static final String CONTENT_ITEM_TYPE_URI = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + FAVORITE_PATH;

        public static final String TABLE_NAME = "favoritePlaces";

        public static final String COLUMN_NAME_PLACE_ID = "placeId";
        public static final String COLUMN_NAME_PLACE_TITLE = "title";
        public static final String COLUMN_NAME_PLACE_ADDRESS = "placeAddress";
        public static final String COLUMN_NAME_PLACE_RATING = "placeRating";
        public static final String COLUMN_NAME_PLACE_LAT = "placeLatitude";
        public static final String COLUMN_NAME_PLACE_LON = "placeLongitude";

        public static Uri buildSelectedPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
