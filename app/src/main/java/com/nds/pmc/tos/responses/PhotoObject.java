package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class PhotoObject {
    @SerializedName("height")
    private double height;
    @SerializedName("width")
    private double width;
    @SerializedName("photo_reference")
    private String photoReference;
    @SerializedName("html_attributions")
    private List<String> htmlAttributions;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoObject that = (PhotoObject) o;

        if (Double.compare(that.height, height) != 0) return false;
        if (Double.compare(that.width, width) != 0) return false;
        if (!photoReference.equals(that.photoReference)) return false;
        return htmlAttributions.equals(that.htmlAttributions);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(height);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + photoReference.hashCode();
        result = 31 * result + htmlAttributions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PhotoObject{" +
                "height=" + height +
                ", width=" + width +
                ", photoReference='" + photoReference + '\'' +
                ", htmlAttributions=" + htmlAttributions +
                '}';
    }
}
