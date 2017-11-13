package com.nds.pmc.tos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Namrata on 11/12/2017.
 */

public class LocationObject {
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationObject that = (LocationObject) o;

        if (Double.compare(that.lat, lat) != 0) return false;
        return Double.compare(that.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LocationObject{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
