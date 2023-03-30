
package com.user.order.model.offer;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("start_work_at")
    @Expose
    private String startWorkAt;
    @SerializedName("end_work_at")
    @Expose
    private String endWorkAt;
    @SerializedName("content_id")
    @Expose
    private String contentId;
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("reg_no")
    @Expose
    private String regNo;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("public_wallet")
    @Expose
    private String publicWallet;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("app_commission")
    @Expose
    private String appCommission;
    @SerializedName("business_hours")
    @Expose
    private String businessHours;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("cover_image_url")
    @Expose
    private String coverImageUrl;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("branch_count")
    @Expose
    private String branchCount;
    @SerializedName("offer_count")
    @Expose
    private String offerCount;
    @SerializedName("category_count")
    @Expose
    private String categoryCount;
    @SerializedName("order_count")
    @Expose
    private String orderCount;
    @SerializedName("meals_count")
    @Expose
    private String mealsCount;
    @SerializedName("store_wallet")
    @Expose
    private String storeWallet;
    @SerializedName("is_open")
    @Expose
    private String isOpen;
    @SerializedName("is_favorite")
    @Expose
    private String isFavorite;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers;
    @SerializedName("sub_category")
    @Expose
    private List<SubCategory> subCategory;
    @SerializedName("branch")
    @Expose
    private List<Object> branch;
    private final static long serialVersionUID = 8365554896715415018L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shop withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop withName(String name) {
        this.name = name;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Shop withLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Shop withLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getStartWorkAt() {
        return startWorkAt;
    }

    public void setStartWorkAt(String startWorkAt) {
        this.startWorkAt = startWorkAt;
    }

    public Shop withStartWorkAt(String startWorkAt) {
        this.startWorkAt = startWorkAt;
        return this;
    }

    public String getEndWorkAt() {
        return endWorkAt;
    }

    public void setEndWorkAt(String endWorkAt) {
        this.endWorkAt = endWorkAt;
    }

    public Shop withEndWorkAt(String endWorkAt) {
        this.endWorkAt = endWorkAt;
        return this;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Shop withContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Shop withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Shop withRegNo(String regNo) {
        this.regNo = regNo;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Shop withIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Shop withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Shop withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPublicWallet() {
        return publicWallet;
    }

    public void setPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
    }

    public Shop withPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
        return this;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public Shop withWallet(String wallet) {
        this.wallet = wallet;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Shop withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getAppCommission() {
        return appCommission;
    }

    public void setAppCommission(String appCommission) {
        this.appCommission = appCommission;
    }

    public Shop withAppCommission(String appCommission) {
        this.appCommission = appCommission;
        return this;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public Shop withBusinessHours(String businessHours) {
        this.businessHours = businessHours;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Shop withLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Shop withCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Shop withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Shop withRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(String branchCount) {
        this.branchCount = branchCount;
    }

    public Shop withBranchCount(String branchCount) {
        this.branchCount = branchCount;
        return this;
    }

    public String getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(String offerCount) {
        this.offerCount = offerCount;
    }

    public Shop withOfferCount(String offerCount) {
        this.offerCount = offerCount;
        return this;
    }

    public String getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(String categoryCount) {
        this.categoryCount = categoryCount;
    }

    public Shop withCategoryCount(String categoryCount) {
        this.categoryCount = categoryCount;
        return this;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public Shop withOrderCount(String orderCount) {
        this.orderCount = orderCount;
        return this;
    }

    public String getMealsCount() {
        return mealsCount;
    }

    public void setMealsCount(String mealsCount) {
        this.mealsCount = mealsCount;
    }

    public Shop withMealsCount(String mealsCount) {
        this.mealsCount = mealsCount;
        return this;
    }

    public String getStoreWallet() {
        return storeWallet;
    }

    public void setStoreWallet(String storeWallet) {
        this.storeWallet = storeWallet;
    }

    public Shop withStoreWallet(String storeWallet) {
        this.storeWallet = storeWallet;
        return this;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public Shop withIsOpen(String isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Shop withIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Shop withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Shop withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Shop withOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public List<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public Shop withSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public List<Object> getBranch() {
        return branch;
    }

    public void setBranch(List<Object> branch) {
        this.branch = branch;
    }

    public Shop withBranch(List<Object> branch) {
        this.branch = branch;
        return this;
    }

}
