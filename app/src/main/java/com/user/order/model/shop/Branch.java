package com.user.order.model.shop;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Branch implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name_ar")
    private String nameAr;

    @SerializedName("name_en")
    private String nameEn;

    @SerializedName("image")
    private Object image;

    @SerializedName("cover_image")
    private Object coverImage;

    @SerializedName("address_ar")
    private String addressAr;

    @SerializedName("address_en")
    private String addressEn;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("start_work_at")
    private Object startWorkAt;

    @SerializedName("end_work_at")
    private Object endWorkAt;

    @SerializedName("content_id")
    private int contentId;

    @SerializedName("content_type")
    private Object contentType;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updatedAt")
    private Date updated_at;

    @SerializedName("deleted_at")
    private Object deletedAt;

    @SerializedName("parent_id")
    private int parentId;

    @SerializedName("reg_no")
    private Object regNo;

    @SerializedName("is_active")
    private int isActive;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("private_wallet")
    private Object privateWallet;

    @SerializedName("wallet")
    private int wallet;

    @SerializedName("country_id")
    private int countryId;

    @SerializedName("app_commission")
    private int appCommission;

    @SerializedName("business_hours")
    private Object businessHours;

    @SerializedName("name")
    private String name;

    @SerializedName("logo_url")
    private Object logoUrl;

    @SerializedName("cover_image_url")
    private Object coverImageUrl;

    @SerializedName("is_in_favorite")
    private int isInFavorite;

    @SerializedName("address")
    private String address;

    @SerializedName("rate")
    private int rate;

    @SerializedName("branch_count")
    private int branchCount;

    @SerializedName("offer_count")
    private int offerCount;

    @SerializedName("category_count")
    private int categoryCount;

    @SerializedName("order_count")
    private int orderCount;

    @SerializedName("meals_count")
    private int mealsCount;

    @SerializedName("store_wallet")
    private int storeWallet;

    @SerializedName("is_open")
    private boolean isOpen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Object coverImage) {
        this.coverImage = coverImage;
    }

    public String getAddressAr() {
        return addressAr;
    }

    public void setAddressAr(String addressAr) {
        this.addressAr = addressAr;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Object getStartWorkAt() {
        return startWorkAt;
    }

    public void setStartWorkAt(Object startWorkAt) {
        this.startWorkAt = startWorkAt;
    }

    public Object getEndWorkAt() {
        return endWorkAt;
    }

    public void setEndWorkAt(Object endWorkAt) {
        this.endWorkAt = endWorkAt;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public Object getContentType() {
        return contentType;
    }

    public void setContentType(Object contentType) {
        this.contentType = contentType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Object getRegNo() {
        return regNo;
    }

    public void setRegNo(Object regNo) {
        this.regNo = regNo;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getPrivateWallet() {
        return privateWallet;
    }

    public void setPrivateWallet(Object privateWallet) {
        this.privateWallet = privateWallet;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getAppCommission() {
        return appCommission;
    }

    public void setAppCommission(int appCommission) {
        this.appCommission = appCommission;
    }

    public Object getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(Object businessHours) {
        this.businessHours = businessHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Object getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(Object coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public int getIsInFavorite() {
        return isInFavorite;
    }

    public void setIsInFavorite(int isInFavorite) {
        this.isInFavorite = isInFavorite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(int branchCount) {
        this.branchCount = branchCount;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getMealsCount() {
        return mealsCount;
    }

    public void setMealsCount(int mealsCount) {
        this.mealsCount = mealsCount;
    }

    public int getStoreWallet() {
        return storeWallet;
    }

    public void setStoreWallet(int storeWallet) {
        this.storeWallet = storeWallet;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
