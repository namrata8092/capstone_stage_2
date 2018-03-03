package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Namrata on 3/1/2018.
 */

public class ReviewDetails implements Parcelable{

    private final String reviewerName;
    private final double rating;
    private final String reviewText;
    private String reviewerPhotoRawData;
    private String relativeTime;

    public ReviewDetails(String name, double rating, String review){
        this.reviewerName = name;
        this.rating = rating;
        this.reviewText = review;
    }


    protected ReviewDetails(Parcel in) {
        reviewerName = in.readString();
        rating = in.readDouble();
        reviewText = in.readString();
        reviewerPhotoRawData = in.readString();
        relativeTime = in.readString();
    }

    public static final Creator<ReviewDetails> CREATOR = new Creator<ReviewDetails>() {
        @Override
        public ReviewDetails createFromParcel(Parcel in) {
            return new ReviewDetails(in);
        }

        @Override
        public ReviewDetails[] newArray(int size) {
            return new ReviewDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reviewerName);
        dest.writeDouble(rating);
        dest.writeString(reviewText);
        dest.writeString(reviewerPhotoRawData);
        dest.writeString(relativeTime);
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public double getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getReviewerPhotoRawData() {
        return reviewerPhotoRawData;
    }

    public void setReviewerPhotoRawData(String reviewerPhotoRawData) {
        this.reviewerPhotoRawData = reviewerPhotoRawData;
    }

    public String getRelativeTime() {
        return relativeTime;
    }

    public void setRelativeTime(String relativeTime) {
        this.relativeTime = relativeTime;
    }
}
