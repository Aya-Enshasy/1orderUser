package com.user.order.model.orders.publicOrders;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.ImageData;
import com.user.order.model.orderdetails.Driver;

import java.io.Serializable;
import java.util.List;

public class OrderData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("store_name")
    private String storeName;

    @SerializedName("invoice_number")
    private String invoiceNumber;

    @SerializedName("purchase_invoice_value")
    private String purchaseInvoiceValue;

    @SerializedName("delivery_cost")
    private String deliveryCost;

    @SerializedName("tax")
    private String tax;

    @SerializedName("total")
    private String total;

    @SerializedName("store_lat")
    private String storeLat;

    @SerializedName("store_lng")
    private String storeLng;

    @SerializedName("store_address")
    private String storeAddress;

    @SerializedName("destination_lat")
    private String destinationLat;

    @SerializedName("destination_lng")
    private String destinationLng;

    @SerializedName("destination_address")
    private String destinationAddress;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("driver_id")
    private String driverId;

    @SerializedName("note")
    private String note;

    @SerializedName("cancel_reason")
    private String cancelReason;

    @SerializedName("cancel_reasons_id")
    private String cancelReasonsId;

    @SerializedName("order_pic")
    private String orderPic;

    @SerializedName("client_deliverd")
    private String clientDeliverd;

    @SerializedName("app_revenue")
    private String appRevenue;

    @SerializedName("driver_revenue")
    private String driverRevenue;

    @SerializedName("app_delivery_commission")
    private String appDeliveryCommission;

    @SerializedName("app_tax")
    private String appTax;

    @SerializedName("status")
    private String status;

    @SerializedName("client_paid_invoice")
    private String clientPaidInvoice;

    @SerializedName("accounted_with_driver")
    private String accountedWithDriver;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("transaction_id")
    private String transactionId;

    @SerializedName("payment_type")
    private String paymentType;

    @SerializedName("order_pic_url")
    private String orderPicUrl;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_timestamp")
    private String createdTimestamp;

    @SerializedName("status_translation")
    private String statusTranslation;

//    @SerializedName("attachments")
//    private List<Attachment> attachments;

    @SerializedName("attachments")
    private List<ImageData> imageData;

    public List<ImageData> getImageData() {
        return imageData;
    }

    public void setImageData(List<ImageData> imageData) {
        this.imageData = imageData;
    }

    @SerializedName("driver")
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPurchaseInvoiceValue() {
        return purchaseInvoiceValue;
    }

    public void setPurchaseInvoiceValue(String purchaseInvoiceValue) {
        this.purchaseInvoiceValue = purchaseInvoiceValue;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(String storeLat) {
        this.storeLat = storeLat;
    }

    public String getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(String storeLng) {
        this.storeLng = storeLng;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelReasonsId() {
        return cancelReasonsId;
    }

    public void setCancelReasonsId(String cancelReasonsId) {
        this.cancelReasonsId = cancelReasonsId;
    }

    public String getOrderPic() {
        return orderPic;
    }

    public void setOrderPic(String orderPic) {
        this.orderPic = orderPic;
    }

    public String getClientDeliverd() {
        return clientDeliverd;
    }

    public void setClientDeliverd(String clientDeliverd) {
        this.clientDeliverd = clientDeliverd;
    }

    public String getAppRevenue() {
        return appRevenue;
    }

    public void setAppRevenue(String appRevenue) {
        this.appRevenue = appRevenue;
    }

    public String getDriverRevenue() {
        return driverRevenue;
    }

    public void setDriverRevenue(String driverRevenue) {
        this.driverRevenue = driverRevenue;
    }

    public String getAppDeliveryCommission() {
        return appDeliveryCommission;
    }

    public void setAppDeliveryCommission(String appDeliveryCommission) {
        this.appDeliveryCommission = appDeliveryCommission;
    }

    public String getAppTax() {
        return appTax;
    }

    public void setAppTax(String appTax) {
        this.appTax = appTax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientPaidInvoice() {
        return clientPaidInvoice;
    }

    public void setClientPaidInvoice(String clientPaidInvoice) {
        this.clientPaidInvoice = clientPaidInvoice;
    }

    public String getAccountedWithDriver() {
        return accountedWithDriver;
    }

    public void setAccountedWithDriver(String accountedWithDriver) {
        this.accountedWithDriver = accountedWithDriver;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderPicUrl() {
        return orderPicUrl;
    }

    public void setOrderPicUrl(String orderPicUrl) {
        this.orderPicUrl = orderPicUrl;
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

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getStatusTranslation() {
        return statusTranslation;
    }

    public void setStatusTranslation(String statusTranslation) {
        this.statusTranslation = statusTranslation;
    }
//
//    public List<Attachment> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(List<Attachment> attachments) {
//        this.attachments = attachments;
//    }
}
