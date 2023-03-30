//package com.user.order.model.order;
//
//
//import com.google.gson.annotations.SerializedName;
//import com.user.order.model.city.City;
//
//import java.io.Serializable;
//
//public class Driver implements Serializable {
//
//    @SerializedName("id")
//    private int id;
//
//    @SerializedName("name")
//    private String name;
//
//    @SerializedName("email")
//    private String email;
//
//    @SerializedName("mobile")
//    private String mobile;
//
//    @SerializedName("active")
//    private String active;
//
//    @SerializedName("address")
//    private String address;
//
//    @SerializedName("role")
//    private String role;
//
//    @SerializedName("lat")
//    private String lat;
//
//    @SerializedName("lng")
//    private String lng;
//
//    @SerializedName("delivery_address")
//    private String deliveryAddress;
//
//    @SerializedName("lang")
//    private String lang;
//
//    @SerializedName("complete_reg")
//    private String completeReg;
//
//    @SerializedName("vehicle_plate")
//    private String vehiclePlate;
//
//    @SerializedName("vehicle_type")
//    private String vehicleType;
//
//    @SerializedName("vehicle_pic")
//    private String vehiclePic;
//
//    @SerializedName("is_online")
//    private String isOnline;
//
//    @SerializedName("fcm_token")
//    private String fcmToken;
//
//    @SerializedName("reg_no")
//    private String regNo;
//
//    @SerializedName("is_documented")
//    private String isDocumented;
//
//    @SerializedName("wallet")
//    private String wallet;
//
//    @SerializedName("public_wallet")
//    private String publicWallet;
//
//    @SerializedName("country_id")
//    private String countryId;
//
//    @SerializedName("orders_balance")
//    private String ordersBalance;
//
//    @SerializedName("city_id")
//    private String cityId;
//
//    @SerializedName("avatar_url")
//    private String avatarUrl;
//
//
//    @SerializedName("Insurance_license_url")
//    private String insuranceLicenseUrl;
//
//    @SerializedName("drive_license_url")
//    private String driveLicenseUrl;
//
//    @SerializedName("vehicle_license_url")
//    private String vehicleLicenseUrl;
//
//    @SerializedName("id_pic_url")
//    private String idPicUrl;
//
//    @SerializedName("vehicle_pic_url")
//    private String vehiclePicUrl;
//
//    @SerializedName("rate")
//    private String rate;
//
//    @SerializedName("order_count")
//    private String orderCount;
//
//    @SerializedName("city_name")
//    private String cityNme;
//
//    @SerializedName("client_order_count")
//    private String clientOrderCount;
//
//    @SerializedName("public_wallet_cal")
//    private String publicWalletCal;
//
//    @SerializedName("created_at")
//    private String createdAt;
//
//    @SerializedName("updated_at")
//    private String updated_at;
//
//    @SerializedName("city")
//    private City city;
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getActive() {
//        return active;
//    }
//
//    public void setActive(String active) {
//        this.active = active;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getLat() {
//        return lat;
//    }
//
//    public void setLat(String lat) {
//        this.lat = lat;
//    }
//
//    public String getLng() {
//        return lng;
//    }
//
//    public void setLng(String lng) {
//        this.lng = lng;
//    }
//
//    public String getDeliveryAddress() {
//        return deliveryAddress;
//    }
//
//    public void setDeliveryAddress(String deliveryAddress) {
//        this.deliveryAddress = deliveryAddress;
//    }
//
//    public String getLang() {
//        return lang;
//    }
//
//    public void setLang(String lang) {
//        this.lang = lang;
//    }
//
//    public String getCompleteReg() {
//        return completeReg;
//    }
//
//    public void setCompleteReg(String completeReg) {
//        this.completeReg = completeReg;
//    }
//
//    public String getVehiclePlate() {
//        return vehiclePlate;
//    }
//
//    public void setVehiclePlate(String vehiclePlate) {
//        this.vehiclePlate = vehiclePlate;
//    }
//
//    public String getVehicleType() {
//        return vehicleType;
//    }
//
//    public void setVehicleType(String vehicleType) {
//        this.vehicleType = vehicleType;
//    }
//
//    public String getVehiclePic() {
//        return vehiclePic;
//    }
//
//    public void setVehiclePic(String vehiclePic) {
//        this.vehiclePic = vehiclePic;
//    }
//
//    public String getIsOnline() {
//        return isOnline;
//    }
//
//    public void setIsOnline(String isOnline) {
//        this.isOnline = isOnline;
//    }
//
//    public String getFcmToken() {
//        return fcmToken;
//    }
//
//    public void setFcmToken(String fcmToken) {
//        this.fcmToken = fcmToken;
//    }
//
//    public String getRegNo() {
//        return regNo;
//    }
//
//    public void setRegNo(String regNo) {
//        this.regNo = regNo;
//    }
//
//    public String getIsDocumented() {
//        return isDocumented;
//    }
//
//    public void setIsDocumented(String isDocumented) {
//        this.isDocumented = isDocumented;
//    }
//
//    public String getWallet() {
//        return wallet;
//    }
//
//    public void setWallet(String wallet) {
//        this.wallet = wallet;
//    }
//
//    public String getPublicWallet() {
//        return publicWallet;
//    }
//
//    public void setPublicWallet(String publicWallet) {
//        this.publicWallet = publicWallet;
//    }
//
//    public String getCountryId() {
//        return countryId;
//    }
//
//    public void setCountryId(String countryId) {
//        this.countryId = countryId;
//    }
//
//    public String getOrdersBalance() {
//        return ordersBalance;
//    }
//
//    public void setOrdersBalance(String ordersBalance) {
//        this.ordersBalance = ordersBalance;
//    }
//
//    public String getCityId() {
//        return cityId;
//    }
//
//    public void setCityId(String cityId) {
//        this.cityId = cityId;
//    }
//
//    public String getAvatarUrl() {
//        return avatarUrl;
//    }
//
//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//    }
//
//    public String getInsuranceLicenseUrl() {
//        return insuranceLicenseUrl;
//    }
//
//    public void setInsuranceLicenseUrl(String insuranceLicenseUrl) {
//        this.insuranceLicenseUrl = insuranceLicenseUrl;
//    }
//
//    public String getDriveLicenseUrl() {
//        return driveLicenseUrl;
//    }
//
//    public void setDriveLicenseUrl(String driveLicenseUrl) {
//        this.driveLicenseUrl = driveLicenseUrl;
//    }
//
//    public String getVehicleLicenseUrl() {
//        return vehicleLicenseUrl;
//    }
//
//    public void setVehicleLicenseUrl(String vehicleLicenseUrl) {
//        this.vehicleLicenseUrl = vehicleLicenseUrl;
//    }
//
//    public String getIdPicUrl() {
//        return idPicUrl;
//    }
//
//    public void setIdPicUrl(String idPicUrl) {
//        this.idPicUrl = idPicUrl;
//    }
//
//    public String getVehiclePicUrl() {
//        return vehiclePicUrl;
//    }
//
//    public void setVehiclePicUrl(String vehiclePicUrl) {
//        this.vehiclePicUrl = vehiclePicUrl;
//    }
//
//    public String getRate() {
//        return rate;
//    }
//
//    public void setRate(String rate) {
//        this.rate = rate;
//    }
//
//    public String getOrderCount() {
//        return orderCount;
//    }
//
//    public void setOrderCount(String orderCount) {
//        this.orderCount = orderCount;
//    }
//
//    public String getCityNme() {
//        return cityNme;
//    }
//
//    public void setCityNme(String cityNme) {
//        this.cityNme = cityNme;
//    }
//
//    public String getClientOrderCount() {
//        return clientOrderCount;
//    }
//
//    public void setClientOrderCount(String clientOrderCount) {
//        this.clientOrderCount = clientOrderCount;
//    }
//
//    public String getPublicWalletCal() {
//        return publicWalletCal;
//    }
//
//    public void setPublicWalletCal(String publicWalletCal) {
//        this.publicWalletCal = publicWalletCal;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdated_at() {
//        return updated_at;
//    }
//
//    public void setUpdated_at(String updated_at) {
//        this.updated_at = updated_at;
//    }
//
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//}
//
