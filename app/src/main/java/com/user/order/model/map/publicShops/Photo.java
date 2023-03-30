package com.user.order.model.map.publicShops;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Photo implements Serializable {

    @SerializedName("height")
    private int height;

    @SerializedName("html_attributions")
    private List<String> htmlAttributions;

    @SerializedName("photo_reference")
    private String photoReference;

    @SerializedName("width")
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
