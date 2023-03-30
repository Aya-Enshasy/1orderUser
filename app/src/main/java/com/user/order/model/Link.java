package com.user.order.model;

import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("url")
    private String url;

    @SerializedName("label")
    private String label;

    @SerializedName("active")
    private boolean active;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
