package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class Place implements Parcelable{
    private final String latitude;
    private final String longitude;
    private final String name;
    private boolean openNow;
    private String id;
    private double rating;
    private List<PhotoModel> photos;
    private String address;
    public Place(String lat, String lon, String name){
        this.latitude = lat;
        this.longitude = lon;
        this.name = name;
    }

    protected Place(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
        name = in.readString();
        openNow = in.readByte() != 0;
        id = in.readString();
        rating = in.readDouble();
        photos = in.createTypedArrayList(PhotoModel.CREATOR);
        address = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<PhotoModel> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoModel> photos) {
        this.photos = photos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(name);
        dest.writeByte((byte) (openNow ? 1 : 0));
        dest.writeString(id);
        dest.writeDouble(rating);
        dest.writeTypedList(photos);
        dest.writeString(address);
    }
}
