package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Namrata on 11/12/2017.
 */

public class GeometryObject {
    @SerializedName("location")
    private LocationObject location;
    @SerializedName("viewport")
    private ViewPortObject viewPortObject;

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public ViewPortObject getViewPortObject() {
        return viewPortObject;
    }

    public void setViewPortObject(ViewPortObject viewPortObject) {
        this.viewPortObject = viewPortObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeometryObject that = (GeometryObject) o;

        if (location != null ? !location.equals(that.location) : that.location != null)
            return false;
        return viewPortObject != null ? viewPortObject.equals(that.viewPortObject) : that.viewPortObject == null;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (viewPortObject != null ? viewPortObject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GeometryObject{" +
                "location=" + location +
                ", viewPortObject=" + viewPortObject +
                '}';
    }
}
