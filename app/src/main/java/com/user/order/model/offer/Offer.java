
package com.user.order.model.offer;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("classification_id")
    @Expose
    private String classificationId;
    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("expire_at")
    @Expose
    private String expireAt;
    private final static long serialVersionUID = 3136942995173861195L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Offer withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Offer withType(String type) {
        this.type = type;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Offer withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Offer withName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Offer withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Offer withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Offer withQty(String qty) {
        this.qty = qty;
        return this;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public Offer withClassificationId(String classificationId) {
        this.classificationId = classificationId;
        return this;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public Offer withSubCatId(String subCatId) {
        this.subCatId = subCatId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Offer withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Offer withIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }

    public Offer withExpireAt(String expireAt) {
        this.expireAt = expireAt;
        return this;
    }

}
