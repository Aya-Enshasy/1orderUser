package com.user.order.model.categories;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("shops_count")
    public String shopsCount;

    @SerializedName("created_at")
    public String createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShopsCount() {
        return shopsCount;
    }

    public void setShopsCount(String shopsCount) {
        this.shopsCount = shopsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
