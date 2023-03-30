package com.user.order.model.order;


import com.google.gson.annotations.SerializedName;
import com.user.order.model.User;
import com.user.order.model.orderdetails.Driver;
import com.user.order.model.shop.Shop;

import java.io.Serializable;
import java.util.List;

public class OneOrderData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("order_type")
    private String orderType;

    @SerializedName("isRated")
    private boolean isRated;

    @SerializedName("invoice_date")
    private String invoiceDate;

    @SerializedName("invoice_number")
    private String invoiceNumber;

    @SerializedName("reference_number")
    private String referenceNumber;

    @SerializedName("status")
    private String status;

    @SerializedName("paid_status")
    private String paidStatus;

    @SerializedName("type_of_receive")
    private String typeOfReceive;

    @SerializedName("type")
    private String type;

    @SerializedName("sub_total_1")
    private String subTotal1;

    @SerializedName("discount")
    private String discount;

    @SerializedName("sub_total_2")
    private String subTotal2;

    @SerializedName("tax")
    private String tax;

    @SerializedName("delivery")
    private String delivery;

    @SerializedName("total")
    private String total;

    @SerializedName("receive")
    private String receive;

    @SerializedName("delivered_date")
    private String deliveredDate;

    @SerializedName("coupon_id")
    private String couponId;

    @SerializedName("notes")
    private String notes;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("driver_id")
    private String driverId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("payment_transaction_id")
    private String paymentTransactionId;

    @SerializedName("shop_id")
    private String shopId;

    @SerializedName("payment_type")
    private String paymentType;

    @SerializedName("destination_lat")
    private String destinationLat;

    @SerializedName("destination_lng")
    private String destinationLng;

    @SerializedName("destination_address")
    private String destinationAddress;

    @SerializedName("store_lat")
    private String storeLat;

    @SerializedName("store_lng")
    private String storeLng;

    @SerializedName("store_address")
    private String storeAddress;

    @SerializedName("app_revenue")
    private String appRevenue;

    @SerializedName("driver_revenue")
    private String driverRevenue;

    @SerializedName("app_delivery_commission")
    private String appDeliveryCommission;

    @SerializedName("app_tax")
    private String appTax;

    @SerializedName("shop_revenue")
    private String shopRevenue;

    @SerializedName("app_shop_commission")
    private String appShopCommission;

    @SerializedName("offer_id")
    private String offerId;

    @SerializedName("sent_to_delivery_boys")
    private String sentToDeliveryBoys;

    @SerializedName("wallet_calc_with_delivery")
    private String walletCalcWithDelivery;

    @SerializedName("is_suspended")
    private String isSuspended;

    @SerializedName("wallet_calc_with_store")
    private String walletCalcWithStore;

    @SerializedName("wallet_calc_with_store_branch")
    private String walletCalcWithStoreBranch;

    @SerializedName("app_commission")
    private String appCommission;

    @SerializedName("order_store_wallet")
    private String orderStoreWallet;

    @SerializedName("accounted_with_shop")
    private String accountedWithShop;

    @SerializedName("accounted_with_app")
    private String accountedWithApp;

    @SerializedName("cancel_reason_id")
    private String cancelReasonId;

    @SerializedName("transaction_id")
    private String transactionId;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("cancel_reason_manual")
    private String cancelReasonManual;

    @SerializedName("card_brand")
    private String cardBrand;

    @SerializedName("delivery_type")
    private String deliveryType;

    @SerializedName("delivery_at")
    private String deliveryAt;

    @SerializedName("item_count")
    private String itemCount;

    @SerializedName("created_timestamp")
    private String createdTimestamp;

    @SerializedName("status_translation")
    private String statusTranslation;

    @SerializedName("user")
    private User user;

    @SerializedName("driver")
    private Driver driver;

    @SerializedName("store")
    private Shop shop;

    @SerializedName("items")
    private List<com.user.order.model.order1.Item> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getTypeOfReceive() {
        return typeOfReceive;
    }

    public void setTypeOfReceive(String typeOfReceive) {
        this.typeOfReceive = typeOfReceive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubTotal1() {
        return subTotal1;
    }

    public void setSubTotal1(String subTotal1) {
        this.subTotal1 = subTotal1;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSubTotal2() {
        return subTotal2;
    }

    public void setSubTotal2(String subTotal2) {
        this.subTotal2 = subTotal2;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
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

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public String getShopRevenue() {
        return shopRevenue;
    }

    public void setShopRevenue(String shopRevenue) {
        this.shopRevenue = shopRevenue;
    }

    public String getAppShopCommission() {
        return appShopCommission;
    }

    public void setAppShopCommission(String appShopCommission) {
        this.appShopCommission = appShopCommission;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getSentToDeliveryBoys() {
        return sentToDeliveryBoys;
    }

    public void setSentToDeliveryBoys(String sentToDeliveryBoys) {
        this.sentToDeliveryBoys = sentToDeliveryBoys;
    }

    public String getWalletCalcWithDelivery() {
        return walletCalcWithDelivery;
    }

    public void setWalletCalcWithDelivery(String walletCalcWithDelivery) {
        this.walletCalcWithDelivery = walletCalcWithDelivery;
    }

    public String getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(String isSuspended) {
        this.isSuspended = isSuspended;
    }

    public String getWalletCalcWithStore() {
        return walletCalcWithStore;
    }

    public void setWalletCalcWithStore(String walletCalcWithStore) {
        this.walletCalcWithStore = walletCalcWithStore;
    }

    public String getWalletCalcWithStoreBranch() {
        return walletCalcWithStoreBranch;
    }

    public void setWalletCalcWithStoreBranch(String walletCalcWithStoreBranch) {
        this.walletCalcWithStoreBranch = walletCalcWithStoreBranch;
    }

    public String getAppCommission() {
        return appCommission;
    }

    public void setAppCommission(String appCommission) {
        this.appCommission = appCommission;
    }

    public String getOrderStoreWallet() {
        return orderStoreWallet;
    }

    public void setOrderStoreWallet(String orderStoreWallet) {
        this.orderStoreWallet = orderStoreWallet;
    }

    public String getAccountedWithShop() {
        return accountedWithShop;
    }

    public void setAccountedWithShop(String accountedWithShop) {
        this.accountedWithShop = accountedWithShop;
    }

    public String getAccountedWithApp() {
        return accountedWithApp;
    }

    public void setAccountedWithApp(String accountedWithApp) {
        this.accountedWithApp = accountedWithApp;
    }

    public String getCancelReasonId() {
        return cancelReasonId;
    }

    public void setCancelReasonId(String cancelReasonId) {
        this.cancelReasonId = cancelReasonId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCancelReasonManual() {
        return cancelReasonManual;
    }

    public void setCancelReasonManual(String cancelReasonManual) {
        this.cancelReasonManual = cancelReasonManual;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryAt() {
        return deliveryAt;
    }

    public void setDeliveryAt(String deliveryAt) {
        this.deliveryAt = deliveryAt;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<com.user.order.model.order1.Item> getItems() {
        return items;
    }

    public void setItems(List<com.user.order.model.order1.Item> items) {
        this.items = items;
    }
}
