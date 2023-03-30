package com.user.order.model.map.publicShops;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublicShops {

    @SerializedName("html_attributions")
    private List<Object> htmlAttributions;

    @SerializedName("next_page_token")
    private String nextPageToken;

    @SerializedName("results")
    private List<PublicShopData> results;

    @SerializedName("status")
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<PublicShopData> getResults() {
        return results;
    }

    public void setResults(List<PublicShopData> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
