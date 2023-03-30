package com.user.order.model.notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationData {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("public_order_id")
    @Expose
    private String publicOrderId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("destination_address")
    @Expose
    private String destinationAddress;
    @SerializedName("destination_lng")
    @Expose
    private String destinationLng;
    @SerializedName("destination_lat")
    @Expose
    private String destinationLat;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_no")
    @Expose
    private String orderNo;
    @SerializedName("pickup_address_ar")
    @Expose
    private String pickupAddressAr;
    @SerializedName("pickup_address_en")
    @Expose
    private String pickupAddressEn;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("type_of_receive")
    @Expose
    private String typeOfReceive;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("invoice_no")
    @Expose
    private String invoiceNo;
    @SerializedName("status_translation")
    @Expose
    private String statusTranslation;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicOrderId() {
        return publicOrderId;
    }

    public void setPublicOrderId(String publicOrderId) {
        this.publicOrderId = publicOrderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPickupAddressAr() {
        return pickupAddressAr;
    }

    public void setPickupAddressAr(String pickupAddressAr) {
        this.pickupAddressAr = pickupAddressAr;
    }

    public String getPickupAddressEn() {
        return pickupAddressEn;
    }

    public void setPickupAddressEn(String pickupAddressEn) {
        this.pickupAddressEn = pickupAddressEn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTypeOfReceive() {
        return typeOfReceive;
    }

    public void setTypeOfReceive(String typeOfReceive) {
        this.typeOfReceive = typeOfReceive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getStatusTranslation() {
        return statusTranslation;
    }

    public void setStatusTranslation(String statusTranslation) {
        this.statusTranslation = statusTranslation;
    }
}