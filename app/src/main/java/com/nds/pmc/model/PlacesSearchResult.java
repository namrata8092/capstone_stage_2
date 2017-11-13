package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class PlacesSearchResult implements Parcelable {

    private final String nextToken;
    private final List<Place> places;

    public PlacesSearchResult(String token, List<Place> placesList){
        this.nextToken = token;
        this.places = placesList;
    }

    protected PlacesSearchResult(Parcel in) {
        nextToken = in.readString();
        places = in.createTypedArrayList(Place.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nextToken);
        dest.writeTypedList(places);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlacesSearchResult> CREATOR = new Creator<PlacesSearchResult>() {
        @Override
        public PlacesSearchResult createFromParcel(Parcel in) {
            return new PlacesSearchResult(in);
        }

        @Override
        public PlacesSearchResult[] newArray(int size) {
            return new PlacesSearchResult[size];
        }
    };

    public String getNextToken() {
        return nextToken;
    }

    public List<Place> getPlaces() {
        return places;
    }


}
