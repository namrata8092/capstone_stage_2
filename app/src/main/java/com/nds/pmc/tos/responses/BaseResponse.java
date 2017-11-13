package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;
import com.nds.pmc.tos.SearchResult;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class BaseResponse {
    @SerializedName("next_page_token")
    private String nextPageToken;
    @SerializedName("status")
    private String status;
    @SerializedName("html_attributions")
    private List<String> htmlAttributions;
    @SerializedName("results")
    private List<SearchResult> results;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseResponse that = (BaseResponse) o;

        if (!nextPageToken.equals(that.nextPageToken)) return false;
        if (!status.equals(that.status)) return false;
        if (!htmlAttributions.equals(that.htmlAttributions)) return false;
        return results.equals(that.results);
    }

    @Override
    public int hashCode() {
        int result = nextPageToken.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + htmlAttributions.hashCode();
        result = 31 * result + results.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "nextPageToken='" + nextPageToken + '\'' +
                ", status='" + status + '\'' +
                ", htmlAttributions=" + htmlAttributions +
                ", results=" + results +
                '}';
    }
}
