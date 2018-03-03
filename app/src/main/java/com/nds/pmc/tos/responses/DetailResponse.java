package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 2/28/2018.
 */

public class DetailResponse {
    @SerializedName("html_attributions")
    private List<String> htmlAttributions;
    @SerializedName("result")
    private DetailedSearchResult result;
    @SerializedName("status")
    private String status;

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public DetailedSearchResult getResult() {
        return result;
    }

    public void setResult(DetailedSearchResult result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetailResponse that = (DetailResponse) o;

        if (!htmlAttributions.equals(that.htmlAttributions)) return false;
        if (!result.equals(that.result)) return false;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        int result1 = htmlAttributions.hashCode();
        result1 = 31 * result1 + result.hashCode();
        result1 = 31 * result1 + status.hashCode();
        return result1;
    }

    @Override
    public String toString() {
        return "DetailResponse{" +
                "htmlAttributions=" + htmlAttributions +
                ", result=" + result +
                ", status='" + status + '\'' +
                '}';
    }
}
