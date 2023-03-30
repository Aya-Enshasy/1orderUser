package com.user.order.model;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.city.City;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("active")
    private String active;

    @SerializedName("address")
    private String address;

    @SerializedName("role")
    private String role;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("lang")
    private String lang;

    @SerializedName("fcm_token")
    private String fcmToken;

    @SerializedName("reg_no")
    private String regNo;

    @SerializedName("is_documented")
    private String isDocumented;

    @SerializedName("wallet")
    private String wallet;

    @SerializedName("public_wallet")
    private String publicWallet;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("orders_balance")
    private String ordersBalance;

    @SerializedName("city_id")
    private String cityId;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("client_order_count")
    private String clientOrderCount;

    @SerializedName("public_wallet_cal")
    private String publicWalletCal;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("deleted_at")
    private String deletedAt;

    @SerializedName("favorite_restaurants")
    private List<Integer> favoritesResturants;

    @SerializedName("city")
    private City city;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getIsDocumented() {
        return isDocumented;
    }

    public void setIsDocumented(String isDocumented) {
        this.isDocumented = isDocumented;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getPublicWallet() {
        return publicWallet;
    }

    public void setPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getOrdersBalance() {
        return ordersBalance;
    }

    public void setOrdersBalance(String ordersBalance) {
        this.ordersBalance = ordersBalance;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClientOrderCount() {
        return clientOrderCount;
    }

    public void setClientOrderCount(String clientOrderCount) {
        this.clientOrderCount = clientOrderCount;
    }

    public String getPublicWalletCal() {
        return publicWalletCal;
    }

    public void setPublicWalletCal(String publicWalletCal) {
        this.publicWalletCal = publicWalletCal;
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

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Integer> getFavoritesResturants() {
        return favoritesResturants;
    }

    public void setFavoritesResturants(List<Integer> favoritesResturants) {
        this.favoritesResturants = favoritesResturants;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
