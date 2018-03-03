package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Namrata on 2/28/2018.
 */

public class PeriodObject {
    @SerializedName("open")
    private OpenCloseObject openObject;
    @SerializedName("close")
    private OpenCloseObject closeObject;

    public OpenCloseObject getOpenObject() {
        return openObject;
    }

    public void setOpenObject(OpenCloseObject openObject) {
        this.openObject = openObject;
    }

    public OpenCloseObject getCloseObject() {
        return closeObject;
    }

    public void setCloseObject(OpenCloseObject closeObject) {
        this.closeObject = closeObject;
    }

    @Override
    public String toString() {
        return "PeriodObject{" +
                "openObject=" + openObject +
                ", closeObject=" + closeObject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodObject that = (PeriodObject) o;

        if (!openObject.equals(that.openObject)) return false;
        return closeObject.equals(that.closeObject);
    }

    @Override
    public int hashCode() {
        int result = openObject.hashCode();
        result = 31 * result + closeObject.hashCode();
        return result;
    }
}
