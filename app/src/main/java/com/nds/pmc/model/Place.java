package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class Place implements Parcelable{
    private final Double latitude;
    private final Double longitude;
    private final String name;
    private boolean openNow;
    private String id;
    private double rating;
    private List<PhotoModel> photos;
    private String address;
    private boolean openNowDetails;
    private boolean openingHours;
    private String openAt;
    private String closeAt;

    public Place(Double lat, Double lon, String name){
        this.latitude = lat;
        this.longitude = lon;
        this.name = name;
    }

    protected Place(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        name = in.readString();
        openNow = in.readByte() != 0;
        id = in.readString();
        rating = in.readDouble();
        photos = in.createTypedArrayList(PhotoModel.CREATOR);
        address = in.readString();
        openNowDetails = in.readByte() != 0;
        openingHours = in.readByte() != 0;
        openAt = in.readString();
        closeAt = in.readString();
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
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

    public boolean isOpenNowDetails() {
        return openNowDetails;
    }

    public void setOpenNowDetails(boolean openNowDetails) {
        this.openNowDetails = openNowDetails;
    }

    public boolean isOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(boolean openingHours) {
        this.openingHours = openingHours;
    }

    public String getOpenAt() {
        return openAt;
    }

    public void setOpenAt(String openAt) {
        this.openAt = openAt;
    }

    public String getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(String closeAt) {
        this.closeAt = closeAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(name);
        dest.writeByte((byte) (openNow ? 1 : 0));
        dest.writeString(id);
        dest.writeDouble(rating);
        dest.writeTypedList(photos);
        dest.writeString(address);
        dest.writeByte((byte) (openNowDetails ? 1 : 0));
        dest.writeByte((byte) (openingHours ? 1 : 0));
        dest.writeString(openAt);
        dest.writeString(closeAt);
    }
}
