package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Namrata on 2/28/2018.
 */

public class PlaceDetails implements Parcelable{
    private final String placeId;
    private final String placeName;
    private final String placeAddress;
    private String photoRawData;
    private String photoUrl;
    private boolean open;
    private String weeklyTiming;
    private String phoneNumber;
    private String webSiteUrl;
    private List<ReviewDetails> reviewDetailList;
    private List<String> photoList;
    private double rating;

    public PlaceDetails(String id, String name, String address) {
        this.placeId = id;
        this.placeName = name;
        this.placeAddress = address;
    }

    protected PlaceDetails(Parcel in) {
        placeId = in.readString();
        placeName = in.readString();
        placeAddress = in.readString();
        photoRawData = in.readString();
        photoUrl = in.readString();
        open = in.readByte() != 0;
        weeklyTiming = in.readString();
        phoneNumber = in.readString();
        webSiteUrl = in.readString();
        reviewDetailList = in.createTypedArrayList(ReviewDetails.CREATOR);
        photoList = in.createStringArrayList();
        rating = in.readDouble();
    }

    public static final Creator<PlaceDetails> CREATOR = new Creator<PlaceDetails>() {
        @Override
        public PlaceDetails createFromParcel(Parcel in) {
            return new PlaceDetails(in);
        }

        @Override
        public PlaceDetails[] newArray(int size) {
            return new PlaceDetails[size];
        }
    };

    public String getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public String getPhotoRawData() {
        return photoRawData;
    }

    public void setPhotoRawData(String photoRawData) {
        this.photoRawData = photoRawData;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getWeeklyTiming() {
        return weeklyTiming;
    }

    public void setWeeklyTiming(String weeklyTiming) {
        this.weeklyTiming = weeklyTiming;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public List<ReviewDetails> getReviewDetailList() {
        return reviewDetailList;
    }

    public void setReviewDetailList(List<ReviewDetails> reviewDetailList) {
        this.reviewDetailList = reviewDetailList;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeId);
        dest.writeString(placeName);
        dest.writeString(placeAddress);
        dest.writeString(photoRawData);
        dest.writeString(photoUrl);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(weeklyTiming);
        dest.writeString(phoneNumber);
        dest.writeString(webSiteUrl);
        dest.writeTypedList(reviewDetailList);
        dest.writeStringList(photoList);
        dest.writeDouble(rating);
    }

}
