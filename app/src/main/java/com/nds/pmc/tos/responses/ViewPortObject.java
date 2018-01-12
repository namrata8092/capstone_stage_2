package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Namrata on 1/12/2018.
 */

public class ViewPortObject {

    @SerializedName("northeast")
    private LocationObject northeast;

    @SerializedName("southwest")
    private LocationObject southwest;

    public LocationObject getNortheast() {
        return northeast;
    }

    public void setNortheast(LocationObject northeast) {
        this.northeast = northeast;
    }

    public LocationObject getSouthwest() {
        return southwest;
    }

    public void setSouthwest(LocationObject southwest) {
        this.southwest = southwest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewPortObject that = (ViewPortObject) o;

        if (northeast != null ? !northeast.equals(that.northeast) : that.northeast != null)
            return false;
        return southwest != null ? southwest.equals(that.southwest) : that.southwest == null;
    }

    @Override
    public int hashCode() {
        int result = northeast != null ? northeast.hashCode() : 0;
        result = 31 * result + (southwest != null ? southwest.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ViewPortObject{" +
                "northeast=" + northeast +
                ", southwest=" + southwest +
                '}';
    }
}
