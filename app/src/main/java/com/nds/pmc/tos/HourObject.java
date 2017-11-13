package com.nds.pmc.tos;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class HourObject {
    @SerializedName("open_now")
    private boolean openNow;
    @SerializedName("weekday_text")
    private List<String> weekdayText;

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public List<String> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HourObject that = (HourObject) o;

        if (openNow != that.openNow) return false;
        return weekdayText.equals(that.weekdayText);
    }

    @Override
    public int hashCode() {
        int result = (openNow ? 1 : 0);
        result = 31 * result + weekdayText.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HourObject{" +
                "openNow=" + openNow +
                ", weekdayText=" + weekdayText +
                '}';
    }
}
