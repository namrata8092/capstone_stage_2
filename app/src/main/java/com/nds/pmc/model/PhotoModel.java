package com.nds.pmc.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Namrata on 11/12/2017.
 */

public class PhotoModel implements Parcelable{

    private final String height;
    private final String width;
    private String mapLink;
    private String imageRaw;

    public PhotoModel(String w, String h){
        this.width = w;
        this.height = h;
    }

    protected PhotoModel(Parcel in) {
        height = in.readString();
        width = in.readString();
        mapLink = in.readString();
        imageRaw = in.readString();
    }

    public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {
        @Override
        public PhotoModel createFromParcel(Parcel in) {
            return new PhotoModel(in);
        }

        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }
    };

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getImageRaw() {
        return imageRaw;
    }

    public void setImageRaw(String imageRaw) {
        this.imageRaw = imageRaw;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(height);
        dest.writeString(width);
        dest.writeString(mapLink);
        dest.writeString(imageRaw);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoModel that = (PhotoModel) o;

        if (!height.equals(that.height)) return false;
        if (!width.equals(that.width)) return false;
        if (!mapLink.equals(that.mapLink)) return false;
        return imageRaw.equals(that.imageRaw);
    }

    @Override
    public int hashCode() {
        int result = height.hashCode();
        result = 31 * result + width.hashCode();
        result = 31 * result + mapLink.hashCode();
        result = 31 * result + imageRaw.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "height='" + height + '\'' +
                ", width='" + width + '\'' +
                ", mapLink='" + mapLink + '\'' +
                ", imageRaw='" + imageRaw + '\'' +
                '}';
    }
}
