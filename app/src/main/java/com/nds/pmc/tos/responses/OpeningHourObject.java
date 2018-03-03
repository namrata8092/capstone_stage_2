package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class OpeningHourObject {
    @SerializedName("open_now")
    private boolean openNow;
    @SerializedName("weekday_text")
    private List<String> weekdayText;
    @SerializedName("periods")
    private List<PeriodObject> periods;

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

    public List<PeriodObject> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodObject> periods) {
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpeningHourObject that = (OpeningHourObject) o;

        if (openNow != that.openNow) return false;
        if (!weekdayText.equals(that.weekdayText)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = (openNow ? 1 : 0);
        result = 31 * result + weekdayText.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OpeningHourObject{" +
                "openNow=" + openNow +
                ", weekdayText=" + weekdayText +
                ", periods=" + periods +
                '}';
    }
}
