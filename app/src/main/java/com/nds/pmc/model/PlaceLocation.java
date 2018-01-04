package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hetal on 12/14/2017.
 */

public class PlaceLocation implements Parcelable{

    public final String latitude;
    public final String longitude;

    public PlaceLocation(String lat,String lon){
        this.latitude = lat;
        this.longitude = lon;
    }

    protected PlaceLocation(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<PlaceLocation> CREATOR = new Creator<PlaceLocation>() {
        @Override
        public PlaceLocation createFromParcel(Parcel in) {
            return new PlaceLocation(in);
        }

        @Override
        public PlaceLocation[] newArray(int size) {
            return new PlaceLocation[size];
        }
    };

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}
