package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 2/28/2018.
 */

public class AddressComponent {
    @SerializedName("long_name")
    private String longName;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("types")
    private List<String> types;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                "longName='" + longName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", types=" + types +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressComponent that = (AddressComponent) o;

        if (!longName.equals(that.longName)) return false;
        if (!shortName.equals(that.shortName)) return false;
        return types.equals(that.types);
    }

    @Override
    public int hashCode() {
        int result = longName.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + types.hashCode();
        return result;
    }
}
