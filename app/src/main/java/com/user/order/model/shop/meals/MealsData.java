package com.user.order.model.shop.meals;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.ImageMeal;
import com.user.order.model.shop.Shop;

import java.io.Serializable;
import java.util.List;

public class MealsData implements Serializable {

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

    @SerializedName("parent_id")
    private String parentId;

    @SerializedName("classification_id")
    private String classificationId;

    @SerializedName("sub_cat_id")
    private String subCatId;

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

    @SerializedName("images")
    private List<ImageMeal> images;

    @SerializedName("shop")
    private Shop shop;

    @SerializedName("extra_items")
    private List<ExtraItem> extraItems;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
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

    public List<ImageMeal> getImages() {
        return images;
    }

    public void setImages(List<ImageMeal> images) {
        this.images = images;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<ExtraItem> getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(List<ExtraItem> extraItems) {
        this.extraItems = extraItems;
    }
}
