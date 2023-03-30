
package com.user.order.model.rating;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.user.order.model.city.City;

public class User implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("fcm_token")
    @Expose
    private String fcmToken;
    @SerializedName("reg_no")
    @Expose
    private String regNo;
    @SerializedName("is_documented")
    @Expose
    private String isDocumented;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("public_wallet")
    @Expose
    private String publicWallet;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("orders_balance")
    @Expose
    private String ordersBalance;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("client_order_count")
    @Expose
    private String clientOrderCount;
    @SerializedName("public_wallet_cal")
    @Expose
    private String publicWalletCal;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("city")
    @Expose
    private City city;
    private final static long serialVersionUID = 1339479333065249526L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public User withActive(String active) {
        this.active = active;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User withRole(String role) {
        this.role = role;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public User withLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public User withLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User withLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public User withFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public User withRegNo(String regNo) {
        this.regNo = regNo;
        return this;
    }

    public String getIsDocumented() {
        return isDocumented;
    }

    public void setIsDocumented(String isDocumented) {
        this.isDocumented = isDocumented;
    }

    public User withIsDocumented(String isDocumented) {
        this.isDocumented = isDocumented;
        return this;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public User withWallet(String wallet) {
        this.wallet = wallet;
        return this;
    }

    public String getPublicWallet() {
        return publicWallet;
    }

    public void setPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
    }

    public User withPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public User withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getOrdersBalance() {
        return ordersBalance;
    }

    public void setOrdersBalance(String ordersBalance) {
        this.ordersBalance = ordersBalance;
    }

    public User withOrdersBalance(String ordersBalance) {
        this.ordersBalance = ordersBalance;
        return this;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public User withCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public User withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public User withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getClientOrderCount() {
        return clientOrderCount;
    }

    public void setClientOrderCount(String clientOrderCount) {
        this.clientOrderCount = clientOrderCount;
    }

    public User withClientOrderCount(String clientOrderCount) {
        this.clientOrderCount = clientOrderCount;
        return this;
    }

    public String getPublicWalletCal() {
        return publicWalletCal;
    }

    public void setPublicWalletCal(String publicWalletCal) {
        this.publicWalletCal = publicWalletCal;
    }

    public User withPublicWalletCal(String publicWalletCal) {
        this.publicWalletCal = publicWalletCal;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User withCity(City city) {
        this.city = city;
        return this;
    }

}
