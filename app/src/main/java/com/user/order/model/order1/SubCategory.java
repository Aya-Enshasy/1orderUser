
package com.user.order.model.order1;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("shops_count")
    @Expose
    private String shopsCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    private final static long serialVersionUID = 1704978985108953301L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubCategory withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubCategory withName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SubCategory withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getShopsCount() {
        return shopsCount;
    }

    public void setShopsCount(String shopsCount) {
        this.shopsCount = shopsCount;
    }

    public SubCategory withShopsCount(String shopsCount) {
        this.shopsCount = shopsCount;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public SubCategory withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

}
