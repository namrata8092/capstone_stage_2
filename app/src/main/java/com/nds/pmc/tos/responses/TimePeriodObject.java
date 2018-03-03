package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Namrata on 2/28/2018.
 */

public class TimePeriodObject {
    @SerializedName("day")
    private int day;
    @SerializedName("time")
    private String time;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriodObject that = (TimePeriodObject) o;

        if (day != that.day) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + time.hashCode();
        return result;
    }


}
