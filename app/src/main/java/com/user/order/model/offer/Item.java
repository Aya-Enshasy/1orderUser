
package com.user.order.model.offer;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Serializable
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
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("extra_items_count")
    @Expose
    private String extraItemsCount;
    @SerializedName("offer_orders_count")
    @Expose
    private String offerOrdersCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("shop")
    @Expose
    private Shop shop;
    private final static long serialVersionUID = 6529815036755034890L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item withType(String type) {
        this.type = type;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Item withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item withName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Item withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Item withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Item withQty(String qty) {
        this.qty = qty;
        return this;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public Item withClassificationId(String classificationId) {
        this.classificationId = classificationId;
        return this;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public Item withSubCatId(String subCatId) {
        this.subCatId = subCatId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Item withIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }

    public Item withExpireAt(String expireAt) {
        this.expireAt = expireAt;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Item withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getExtraItemsCount() {
        return extraItemsCount;
    }

    public void setExtraItemsCount(String extraItemsCount) {
        this.extraItemsCount = extraItemsCount;
    }

    public Item withExtraItemsCount(String extraItemsCount) {
        this.extraItemsCount = extraItemsCount;
        return this;
    }

    public String getOfferOrdersCount() {
        return offerOrdersCount;
    }

    public void setOfferOrdersCount(String offerOrdersCount) {
        this.offerOrdersCount = offerOrdersCount;
    }

    public Item withOfferOrdersCount(String offerOrdersCount) {
        this.offerOrdersCount = offerOrdersCount;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Item withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Item withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Item withShop(Shop shop) {
        this.shop = shop;
        return this;
    }

}
