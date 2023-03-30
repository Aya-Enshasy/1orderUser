package com.user.order.model.country;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountrySetting implements Serializable {

    @SerializedName("tax")
    private String tax;

    @SerializedName("delivery_cost")
    private String deliveryCost;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;

    @SerializedName("driver_wallet_limit")
    private String driverWalletLimit;

    @SerializedName("shop_wallet_limit")
    private String shopWalletLimit;

    @SerializedName("app_delivery_commission")
    private String appDeliveryCommission;

    @SerializedName("app_commission_public_delivery")
    private String appCommissionPublicDelivery;

    @SerializedName("public_delivery_cost_km")
    private String publicDeliveryCostKm;

    @SerializedName("shop_distance_limit_km")
    private String shopDistanceLimitKm;

    @SerializedName("driver_distance_limit_km")
    private String driverDistanceLimitKm;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("client_hand_payment_limit")
    private String clientHandPaymentLimit;

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverWalletLimit() {
        return driverWalletLimit;
    }

    public void setDriverWalletLimit(String driverWalletLimit) {
        this.driverWalletLimit = driverWalletLimit;
    }

    public String getShopWalletLimit() {
        return shopWalletLimit;
    }

    public void setShopWalletLimit(String shopWalletLimit) {
        this.shopWalletLimit = shopWalletLimit;
    }

    public String getAppDeliveryCommission() {
        return appDeliveryCommission;
    }

    public void setAppDeliveryCommission(String appDeliveryCommission) {
        this.appDeliveryCommission = appDeliveryCommission;
    }

    public String getAppCommissionPublicDelivery() {
        return appCommissionPublicDelivery;
    }

    public void setAppCommissionPublicDelivery(String appCommissionPublicDelivery) {
        this.appCommissionPublicDelivery = appCommissionPublicDelivery;
    }

    public String getPublicDeliveryCostKm() {
        return publicDeliveryCostKm;
    }

    public void setPublicDeliveryCostKm(String publicDeliveryCostKm) {
        this.publicDeliveryCostKm = publicDeliveryCostKm;
    }

    public String getShopDistanceLimitKm() {
        return shopDistanceLimitKm;
    }

    public void setShopDistanceLimitKm(String shopDistanceLimitKm) {
        this.shopDistanceLimitKm = shopDistanceLimitKm;
    }

    public String getDriverDistanceLimitKm() {
        return driverDistanceLimitKm;
    }

    public void setDriverDistanceLimitKm(String driverDistanceLimitKm) {
        this.driverDistanceLimitKm = driverDistanceLimitKm;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getClientHandPaymentLimit() {
        return clientHandPaymentLimit;
    }

    public void setClientHandPaymentLimit(String clientHandPaymentLimit) {
        this.clientHandPaymentLimit = clientHandPaymentLimit;
    }
}
