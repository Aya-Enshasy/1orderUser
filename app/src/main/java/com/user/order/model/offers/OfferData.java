package com.user.order.model.offers;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.shop.Shop;

public class OfferData {

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("price")
    private String price;

    @SerializedName("qty")
    private String qty;

    @SerializedName("classification_id")
    private String classificationId;

    @SerializedName("sub_cat_id")
    private String subCaId;

    @SerializedName("status")
    private String status;

    @SerializedName("is_active")
    private String isActive;

    @SerializedName("expire_at")
    private String expireAt;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("extra_items_count")
    private String extraItemsCount;

    @SerializedName("offer_orders_count")
    private String offerOrdersCount;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("shop")
    private Shop shop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getSubCaId() {
        return subCaId;
    }

    public void setSubCaId(String subCaId) {
        this.subCaId = subCaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getExtraItemsCount() {
        return extraItemsCount;
    }

    public void setExtraItemsCount(String extraItemsCount) {
        this.extraItemsCount = extraItemsCount;
    }

    public String getOfferOrdersCount() {
        return offerOrdersCount;
    }

    public void setOfferOrdersCount(String offerOrdersCount) {
        this.offerOrdersCount = offerOrdersCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
