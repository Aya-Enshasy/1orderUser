
package com.user.order.model.orderdetails;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;
    @SerializedName("purchase_invoice_value")
    @Expose
    private String purchaseInvoiceValue;
    @SerializedName("delivery_cost")
    @Expose
    private String deliveryCost;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("store_lat")
    @Expose
    private String storeLat;
    @SerializedName("store_lng")
    @Expose
    private String storeLng;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
    @SerializedName("destination_lat")
    @Expose
    private String destinationLat;
    @SerializedName("destination_lng")
    @Expose
    private String destinationLng;
    @SerializedName("destination_address")
    @Expose
    private String destinationAddress;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("cancel_reason")
    @Expose
    private String cancelReason;
    @SerializedName("cancel_reasons_id")
    @Expose
    private String cancelReasonsId;
    @SerializedName("order_pic")
    @Expose
    private String orderPic;
    @SerializedName("client_deliverd")
    @Expose
    private String clientDeliverd;
    @SerializedName("app_revenue")
    @Expose
    private String appRevenue;
    @SerializedName("driver_revenue")
    @Expose
    private String driverRevenue;
    @SerializedName("app_delivery_commission")
    @Expose
    private String appDeliveryCommission;
    @SerializedName("app_tax")
    @Expose
    private String appTax;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("client_paid_invoice")
    @Expose
    private String clientPaidInvoice;
    @SerializedName("accounted_with_driver")
    @Expose
    private String accountedWithDriver;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("order_pic_url")
    @Expose
    private String orderPicUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_timestamp")
    @Expose
    private String createdTimestamp;
    @SerializedName("status_translation")
    @Expose
    private String statusTranslation;
    @SerializedName("driver")
    @Expose
    private Driver driver;
    @SerializedName("attachments")
    @Expose
    private List<com.user.order.model.orders.publicOrders.Attachment> attachments;
    private final static long serialVersionUID = 2647593032742081L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Data withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Data withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Data withStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Data withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public String getPurchaseInvoiceValue() {
        return purchaseInvoiceValue;
    }

    public void setPurchaseInvoiceValue(String purchaseInvoiceValue) {
        this.purchaseInvoiceValue = purchaseInvoiceValue;
    }

    public Data withPurchaseInvoiceValue(String purchaseInvoiceValue) {
        this.purchaseInvoiceValue = purchaseInvoiceValue;
        return this;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Data withDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
        return this;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Data withTax(String tax) {
        this.tax = tax;
        return this;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Data withTotal(String total) {
        this.total = total;
        return this;
    }

    public String getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(String storeLat) {
        this.storeLat = storeLat;
    }

    public Data withStoreLat(String storeLat) {
        this.storeLat = storeLat;
        return this;
    }

    public String getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(String storeLng) {
        this.storeLng = storeLng;
    }

    public Data withStoreLng(String storeLng) {
        this.storeLng = storeLng;
        return this;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Data withStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Data withDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
        return this;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

    public Data withDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
        return this;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Data withDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Data withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Data withDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Data withNote(String note) {
        this.note = note;
        return this;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Data withCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
        return this;
    }

    public String getCancelReasonsId() {
        return cancelReasonsId;
    }

    public void setCancelReasonsId(String cancelReasonsId) {
        this.cancelReasonsId = cancelReasonsId;
    }

    public Data withCancelReasonsId(String cancelReasonsId) {
        this.cancelReasonsId = cancelReasonsId;
        return this;
    }

    public String getOrderPic() {
        return orderPic;
    }

    public void setOrderPic(String orderPic) {
        this.orderPic = orderPic;
    }

    public Data withOrderPic(String orderPic) {
        this.orderPic = orderPic;
        return this;
    }

    public String getClientDeliverd() {
        return clientDeliverd;
    }

    public void setClientDeliverd(String clientDeliverd) {
        this.clientDeliverd = clientDeliverd;
    }

    public Data withClientDeliverd(String clientDeliverd) {
        this.clientDeliverd = clientDeliverd;
        return this;
    }

    public String getAppRevenue() {
        return appRevenue;
    }

    public void setAppRevenue(String appRevenue) {
        this.appRevenue = appRevenue;
    }

    public Data withAppRevenue(String appRevenue) {
        this.appRevenue = appRevenue;
        return this;
    }

    public String getDriverRevenue() {
        return driverRevenue;
    }

    public void setDriverRevenue(String driverRevenue) {
        this.driverRevenue = driverRevenue;
    }

    public Data withDriverRevenue(String driverRevenue) {
        this.driverRevenue = driverRevenue;
        return this;
    }

    public String getAppDeliveryCommission() {
        return appDeliveryCommission;
    }

    public void setAppDeliveryCommission(String appDeliveryCommission) {
        this.appDeliveryCommission = appDeliveryCommission;
    }

    public Data withAppDeliveryCommission(String appDeliveryCommission) {
        this.appDeliveryCommission = appDeliveryCommission;
        return this;
    }

    public String getAppTax() {
        return appTax;
    }

    public void setAppTax(String appTax) {
        this.appTax = appTax;
    }

    public Data withAppTax(String appTax) {
        this.appTax = appTax;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getClientPaidInvoice() {
        return clientPaidInvoice;
    }

    public void setClientPaidInvoice(String clientPaidInvoice) {
        this.clientPaidInvoice = clientPaidInvoice;
    }

    public Data withClientPaidInvoice(String clientPaidInvoice) {
        this.clientPaidInvoice = clientPaidInvoice;
        return this;
    }

    public String getAccountedWithDriver() {
        return accountedWithDriver;
    }

    public void setAccountedWithDriver(String accountedWithDriver) {
        this.accountedWithDriver = accountedWithDriver;
    }

    public Data withAccountedWithDriver(String accountedWithDriver) {
        this.accountedWithDriver = accountedWithDriver;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Data withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Data withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Data withPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getOrderPicUrl() {
        return orderPicUrl;
    }

    public void setOrderPicUrl(String orderPicUrl) {
        this.orderPicUrl = orderPicUrl;
    }

    public Data withOrderPicUrl(String orderPicUrl) {
        this.orderPicUrl = orderPicUrl;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Data withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Data withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Data withCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    public String getStatusTranslation() {
        return statusTranslation;
    }

    public void setStatusTranslation(String statusTranslation) {
        this.statusTranslation = statusTranslation;
    }

    public Data withStatusTranslation(String statusTranslation) {
        this.statusTranslation = statusTranslation;
        return this;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Data withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public List<com.user.order.model.orders.publicOrders.Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<com.user.order.model.orders.publicOrders.Attachment> attachments) {
        this.attachments = attachments;
    }

    public Data withAttachments(List<com.user.order.model.orders.publicOrders.Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

}
