package com.user.order.model.shop;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.categories.CategoryData;
import com.user.order.model.offers.Offer;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("start_work_at")
    private String startWorkAt;

    @SerializedName("end_work_at")
    private String endWorkAt;

    @SerializedName("content_id")
    private String contentId;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("reg_no")
    private String regNo;

    @SerializedName("is_active")
    private String isActive;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("private_wallet")
    private String privateWallet;

    @SerializedName("wallet")
    private String wallet;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("app_commission")
    private String appCommission;

    @SerializedName("business_hours")
    private String businessHours;

    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("cover_image_url")
    private String coverImageUrl;

    @SerializedName("address")
    private String address;

    @SerializedName("rate")
    private String rate;

    @SerializedName("branch_count")
    private String branchCount;

    @SerializedName("offer_count")
    private String offerCount;

    @SerializedName("category_count")
    private String categoryCount;

    @SerializedName("order_count")
    private String orderCount;

    @SerializedName("meals_count")
    private String mealsCount;

    @SerializedName("store_wallet")
    private String storeWallet;

    @SerializedName("is_open")
    private String isOpen;

    @SerializedName("is_favorite")
    private String isFavorite;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("offers")
    private List<Offer> offers;

    @SerializedName("sub_category")
    private List<CategoryData> sub_category;

    @SerializedName("branch")
    private List<Branch> branch;

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getStartWorkAt() {
        return startWorkAt;
    }

    public void setStartWorkAt(String startWorkAt) {
        this.startWorkAt = startWorkAt;
    }

    public String getEndWorkAt() {
        return endWorkAt;
    }

    public void setEndWorkAt(String endWorkAt) {
        this.endWorkAt = endWorkAt;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPrivateWallet() {
        return privateWallet;
    }

    public void setPrivateWallet(String privateWallet) {
        this.privateWallet = privateWallet;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getAppCommission() {
        return appCommission;
    }

    public void setAppCommission(String appCommission) {
        this.appCommission = appCommission;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(String branchCount) {
        this.branchCount = branchCount;
    }

    public String getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(String offerCount) {
        this.offerCount = offerCount;
    }

    public String getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(String categoryCount) {
        this.categoryCount = categoryCount;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getMealsCount() {
        return mealsCount;
    }

    public void setMealsCount(String mealsCount) {
        this.mealsCount = mealsCount;
    }

    public String getStoreWallet() {
        return storeWallet;
    }

    public void setStoreWallet(String storeWallet) {
        this.storeWallet = storeWallet;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<CategoryData> getSub_category() {
        return sub_category;
    }

    public void setSub_category(List<CategoryData> sub_category) {
        this.sub_category = sub_category;
    }

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }
}
