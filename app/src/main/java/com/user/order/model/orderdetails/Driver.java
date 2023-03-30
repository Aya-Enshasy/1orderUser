
package com.user.order.model.orderdetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Driver implements Serializable
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
    @SerializedName("delivery_address")
    @Expose
    private String deliveryAddress;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("complete_reg")
    @Expose
    private String completeReg;
    @SerializedName("vehicle_plate")
    @Expose
    private String vehiclePlate;
    @SerializedName("vehicle_type")
    @Expose
    private String vehicleType;
    @SerializedName("vehicle_pic")
    @Expose
    private String vehiclePic;
    @SerializedName("is_online")
    @Expose
    private String isOnline;
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
    @SerializedName("Insurance_license_url")
    @Expose
    private String insuranceLicenseUrl;
    @SerializedName("drive_license_url")
    @Expose
    private String driveLicenseUrl;
    @SerializedName("vehicle_license_url")
    @Expose
    private String vehicleLicenseUrl;
    @SerializedName("id_pic_url")
    @Expose
    private String idPicUrl;
    @SerializedName("vehicle_pic_url")
    @Expose
    private String vehiclePicUrl;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("order_count")
    @Expose
    private String orderCount;
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
    private final static long serialVersionUID = -7464605002936620229L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Driver withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Driver withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Driver withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Driver withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Driver withActive(String active) {
        this.active = active;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Driver withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Driver withRole(String role) {
        this.role = role;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Driver withLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Driver withLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Driver withDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Driver withLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getCompleteReg() {
        return completeReg;
    }

    public void setCompleteReg(String completeReg) {
        this.completeReg = completeReg;
    }

    public Driver withCompleteReg(String completeReg) {
        this.completeReg = completeReg;
        return this;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public Driver withVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Driver withVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public String getVehiclePic() {
        return vehiclePic;
    }

    public void setVehiclePic(String vehiclePic) {
        this.vehiclePic = vehiclePic;
    }

    public Driver withVehiclePic(String vehiclePic) {
        this.vehiclePic = vehiclePic;
        return this;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Driver withIsOnline(String isOnline) {
        this.isOnline = isOnline;
        return this;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public Driver withFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Driver withRegNo(String regNo) {
        this.regNo = regNo;
        return this;
    }

    public String getIsDocumented() {
        return isDocumented;
    }

    public void setIsDocumented(String isDocumented) {
        this.isDocumented = isDocumented;
    }

    public Driver withIsDocumented(String isDocumented) {
        this.isDocumented = isDocumented;
        return this;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public Driver withWallet(String wallet) {
        this.wallet = wallet;
        return this;
    }

    public String getPublicWallet() {
        return publicWallet;
    }

    public void setPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
    }

    public Driver withPublicWallet(String publicWallet) {
        this.publicWallet = publicWallet;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Driver withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getOrdersBalance() {
        return ordersBalance;
    }

    public void setOrdersBalance(String ordersBalance) {
        this.ordersBalance = ordersBalance;
    }

    public Driver withOrdersBalance(String ordersBalance) {
        this.ordersBalance = ordersBalance;
        return this;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Driver withCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Driver withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getInsuranceLicenseUrl() {
        return insuranceLicenseUrl;
    }

    public void setInsuranceLicenseUrl(String insuranceLicenseUrl) {
        this.insuranceLicenseUrl = insuranceLicenseUrl;
    }

    public Driver withInsuranceLicenseUrl(String insuranceLicenseUrl) {
        this.insuranceLicenseUrl = insuranceLicenseUrl;
        return this;
    }

    public String getDriveLicenseUrl() {
        return driveLicenseUrl;
    }

    public void setDriveLicenseUrl(String driveLicenseUrl) {
        this.driveLicenseUrl = driveLicenseUrl;
    }

    public Driver withDriveLicenseUrl(String driveLicenseUrl) {
        this.driveLicenseUrl = driveLicenseUrl;
        return this;
    }

    public String getVehicleLicenseUrl() {
        return vehicleLicenseUrl;
    }

    public void setVehicleLicenseUrl(String vehicleLicenseUrl) {
        this.vehicleLicenseUrl = vehicleLicenseUrl;
    }

    public Driver withVehicleLicenseUrl(String vehicleLicenseUrl) {
        this.vehicleLicenseUrl = vehicleLicenseUrl;
        return this;
    }

    public String getIdPicUrl() {
        return idPicUrl;
    }

    public void setIdPicUrl(String idPicUrl) {
        this.idPicUrl = idPicUrl;
    }

    public Driver withIdPicUrl(String idPicUrl) {
        this.idPicUrl = idPicUrl;
        return this;
    }

    public String getVehiclePicUrl() {
        return vehiclePicUrl;
    }

    public void setVehiclePicUrl(String vehiclePicUrl) {
        this.vehiclePicUrl = vehiclePicUrl;
    }

    public Driver withVehiclePicUrl(String vehiclePicUrl) {
        this.vehiclePicUrl = vehiclePicUrl;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Driver withRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public Driver withOrderCount(String orderCount) {
        this.orderCount = orderCount;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Driver withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getClientOrderCount() {
        return clientOrderCount;
    }

    public void setClientOrderCount(String clientOrderCount) {
        this.clientOrderCount = clientOrderCount;
    }

    public Driver withClientOrderCount(String clientOrderCount) {
        this.clientOrderCount = clientOrderCount;
        return this;
    }

    public String getPublicWalletCal() {
        return publicWalletCal;
    }

    public void setPublicWalletCal(String publicWalletCal) {
        this.publicWalletCal = publicWalletCal;
    }

    public Driver withPublicWalletCal(String publicWalletCal) {
        this.publicWalletCal = publicWalletCal;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Driver withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Driver withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Driver withCity(City city) {
        this.city = city;
        return this;
    }

}
