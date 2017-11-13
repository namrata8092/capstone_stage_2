package com.nds.pmc.tos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class GeometryObject {
    @SerializedName("location")
    private LocationObject location;
    @SerializedName("viewport")
    private List<LocationObject> locationObjects;

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public List<LocationObject> getLocationObjects() {
        return locationObjects;
    }

    public void setLocationObjects(List<LocationObject> locationObjects) {
        this.locationObjects = locationObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeometryObject that = (GeometryObject) o;

        if (!location.equals(that.location)) return false;
        return locationObjects.equals(that.locationObjects);
    }

    @Override
    public int hashCode() {
        int result = location.hashCode();
        result = 31 * result + locationObjects.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GeometryObject{" +
                "location=" + location +
                ", locationObjects=" + locationObjects +
                '}';
    }
}
