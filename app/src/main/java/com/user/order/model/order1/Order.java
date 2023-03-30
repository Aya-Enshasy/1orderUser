
package com.user.order.model.order1;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.user.order.model.orderdetails.Driver;

public class Order implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("isRated")
    @Expose
    private Boolean isRated;
    @SerializedName("invoice_date")
    @Expose
    private String invoiceDate;
    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;
    @SerializedName("reference_number")
    @Expose
    private String referenceNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("paid_status")
    @Expose
    private String paidStatus;
    @SerializedName("type_of_receive")
    @Expose
    private String typeOfReceive;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sub_total_1")
    @Expose
    private String subTotal1;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("sub_total_2")
    @Expose
    private String subTotal2;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("delivery")
    @Expose
    private String delivery;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("receive")
    @Expose
    private String receive;
    @SerializedName("delivered_date")
    @Expose
    private String deliveredDate;
    @SerializedName("coupon_id")
    @Expose
    private String couponId;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("payment_transaction_id")
    @Expose
    private String paymentTransactionId;
    @SerializedName("shop_id")
    @Expose
    private String shopId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("destination_lat")
    @Expose
    private String destinationLat;
    @SerializedName("destination_lng")
    @Expose
    private String destinationLng;
    @SerializedName("destination_address")
    @Expose
    private String destinationAddress;
    @SerializedName("store_lat")
    @Expose
    private String storeLat;
    @SerializedName("store_lng")
    @Expose
    private String storeLng;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
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
    @SerializedName("shop_revenue")
    @Expose
    private String shopRevenue;
    @SerializedName("app_shop_commission")
    @Expose
    private String appShopCommission;
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("sent_to_delivery_boys")
    @Expose
    private String sentToDeliveryBoys;
    @SerializedName("wallet_calc_with_delivery")
    @Expose
    private String walletCalcWithDelivery;
    @SerializedName("is_suspended")
    @Expose
    private String isSuspended;
    @SerializedName("wallet_calc_with_store")
    @Expose
    private String walletCalcWithStore;
    @SerializedName("wallet_calc_with_store_branch")
    @Expose
    private String walletCalcWithStoreBranch;
    @SerializedName("app_commission")
    @Expose
    private String appCommission;
    @SerializedName("order_store_wallet")
    @Expose
    private String orderStoreWallet;
    @SerializedName("accounted_with_shop")
    @Expose
    private String accountedWithShop;
    @SerializedName("accounted_with_app")
    @Expose
    private String accountedWithApp;
    @SerializedName("cancel_reason_id")
    @Expose
    private String cancelReasonId;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("cancel_reason_manual")
    @Expose
    private String cancelReasonManual;
    @SerializedName("card_brand")
    @Expose
    private String cardBrand;
    @SerializedName("delivery_type")
    @Expose
    private String deliveryType;
    @SerializedName("delivery_at")
    @Expose
    private String deliveryAt;
    @SerializedName("item_count")
    @Expose
    private String itemCount;
    @SerializedName("created_timestamp")
    @Expose
    private String createdTimestamp;
    @SerializedName("status_translation")
    @Expose
    private String statusTranslation;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("driver")
    @Expose
    private Driver driver;
    @SerializedName("store")
    @Expose
    private Store store;
    @SerializedName("items")
    @Expose
    private List<Item> items;
    private final static long serialVersionUID = -1363071526961664897L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Order withOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public Boolean getIsRated() {
        return isRated;
    }

    public void setIsRated(Boolean isRated) {
        this.isRated = isRated;
    }

    public Order withIsRated(Boolean isRated) {
        this.isRated = isRated;
        return this;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Order withInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Order withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Order withReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public Order withPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
        return this;
    }

    public String getTypeOfReceive() {
        return typeOfReceive;
    }

    public void setTypeOfReceive(String typeOfReceive) {
        this.typeOfReceive = typeOfReceive;
    }

    public Order withTypeOfReceive(String typeOfReceive) {
        this.typeOfReceive = typeOfReceive;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Order withType(String type) {
        this.type = type;
        return this;
    }

    public String getSubTotal1() {
        return subTotal1;
    }

    public void setSubTotal1(String subTotal1) {
        this.subTotal1 = subTotal1;
    }

    public Order withSubTotal1(String subTotal1) {
        this.subTotal1 = subTotal1;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Order withDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getSubTotal2() {
        return subTotal2;
    }

    public void setSubTotal2(String subTotal2) {
        this.subTotal2 = subTotal2;
    }

    public Order withSubTotal2(String subTotal2) {
        this.subTotal2 = subTotal2;
        return this;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Order withTax(String tax) {
        this.tax = tax;
        return this;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Order withDelivery(String delivery) {
        this.delivery = delivery;
        return this;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Order withTotal(String total) {
        this.total = total;
        return this;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public Order withReceive(String receive) {
        this.receive = receive;
        return this;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Order withDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
        return this;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Order withCouponId(String couponId) {
        this.couponId = couponId;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Order withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Order withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Order withDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Order withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Order withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    public Order withPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
        return this;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Order withShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Order withPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Order withDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
        return this;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

    public Order withDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
        return this;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Order withDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
        return this;
    }

    public String getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(String storeLat) {
        this.storeLat = storeLat;
    }

    public Order withStoreLat(String storeLat) {
        this.storeLat = storeLat;
        return this;
    }

    public String getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(String storeLng) {
        this.storeLng = storeLng;
    }

    public Order withStoreLng(String storeLng) {
        this.storeLng = storeLng;
        return this;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Order withStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public String getAppRevenue() {
        return appRevenue;
    }

    public void setAppRevenue(String appRevenue) {
        this.appRevenue = appRevenue;
    }

    public Order withAppRevenue(String appRevenue) {
        this.appRevenue = appRevenue;
        return this;
    }

    public String getDriverRevenue() {
        return driverRevenue;
    }

    public void setDriverRevenue(String driverRevenue) {
        this.driverRevenue = driverRevenue;
    }

    public Order withDriverRevenue(String driverRevenue) {
        this.driverRevenue = driverRevenue;
        return this;
    }

    public String getAppDeliveryCommission() {
        return appDeliveryCommission;
    }

    public void setAppDeliveryCommission(String appDeliveryCommission) {
        this.appDeliveryCommission = appDeliveryCommission;
    }

    public Order withAppDeliveryCommission(String appDeliveryCommission) {
        this.appDeliveryCommission = appDeliveryCommission;
        return this;
    }

    public String getAppTax() {
        return appTax;
    }

    public void setAppTax(String appTax) {
        this.appTax = appTax;
    }

    public Order withAppTax(String appTax) {
        this.appTax = appTax;
        return this;
    }

    public String getShopRevenue() {
        return shopRevenue;
    }

    public void setShopRevenue(String shopRevenue) {
        this.shopRevenue = shopRevenue;
    }

    public Order withShopRevenue(String shopRevenue) {
        this.shopRevenue = shopRevenue;
        return this;
    }

    public String getAppShopCommission() {
        return appShopCommission;
    }

    public void setAppShopCommission(String appShopCommission) {
        this.appShopCommission = appShopCommission;
    }

    public Order withAppShopCommission(String appShopCommission) {
        this.appShopCommission = appShopCommission;
        return this;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Order withOfferId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getSentToDeliveryBoys() {
        return sentToDeliveryBoys;
    }

    public void setSentToDeliveryBoys(String sentToDeliveryBoys) {
        this.sentToDeliveryBoys = sentToDeliveryBoys;
    }

    public Order withSentToDeliveryBoys(String sentToDeliveryBoys) {
        this.sentToDeliveryBoys = sentToDeliveryBoys;
        return this;
    }

    public String getWalletCalcWithDelivery() {
        return walletCalcWithDelivery;
    }

    public void setWalletCalcWithDelivery(String walletCalcWithDelivery) {
        this.walletCalcWithDelivery = walletCalcWithDelivery;
    }

    public Order withWalletCalcWithDelivery(String walletCalcWithDelivery) {
        this.walletCalcWithDelivery = walletCalcWithDelivery;
        return this;
    }

    public String getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(String isSuspended) {
        this.isSuspended = isSuspended;
    }

    public Order withIsSuspended(String isSuspended) {
        this.isSuspended = isSuspended;
        return this;
    }

    public String getWalletCalcWithStore() {
        return walletCalcWithStore;
    }

    public void setWalletCalcWithStore(String walletCalcWithStore) {
        this.walletCalcWithStore = walletCalcWithStore;
    }

    public Order withWalletCalcWithStore(String walletCalcWithStore) {
        this.walletCalcWithStore = walletCalcWithStore;
        return this;
    }

    public String getWalletCalcWithStoreBranch() {
        return walletCalcWithStoreBranch;
    }

    public void setWalletCalcWithStoreBranch(String walletCalcWithStoreBranch) {
        this.walletCalcWithStoreBranch = walletCalcWithStoreBranch;
    }

    public Order withWalletCalcWithStoreBranch(String walletCalcWithStoreBranch) {
        this.walletCalcWithStoreBranch = walletCalcWithStoreBranch;
        return this;
    }

    public String getAppCommission() {
        return appCommission;
    }

    public void setAppCommission(String appCommission) {
        this.appCommission = appCommission;
    }

    public Order withAppCommission(String appCommission) {
        this.appCommission = appCommission;
        return this;
    }

    public String getOrderStoreWallet() {
        return orderStoreWallet;
    }

    public void setOrderStoreWallet(String orderStoreWallet) {
        this.orderStoreWallet = orderStoreWallet;
    }

    public Order withOrderStoreWallet(String orderStoreWallet) {
        this.orderStoreWallet = orderStoreWallet;
        return this;
    }

    public String getAccountedWithShop() {
        return accountedWithShop;
    }

    public void setAccountedWithShop(String accountedWithShop) {
        this.accountedWithShop = accountedWithShop;
    }

    public Order withAccountedWithShop(String accountedWithShop) {
        this.accountedWithShop = accountedWithShop;
        return this;
    }

    public String getAccountedWithApp() {
        return accountedWithApp;
    }

    public void setAccountedWithApp(String accountedWithApp) {
        this.accountedWithApp = accountedWithApp;
    }

    public Order withAccountedWithApp(String accountedWithApp) {
        this.accountedWithApp = accountedWithApp;
        return this;
    }

    public String getCancelReasonId() {
        return cancelReasonId;
    }

    public void setCancelReasonId(String cancelReasonId) {
        this.cancelReasonId = cancelReasonId;
    }

    public Order withCancelReasonId(String cancelReasonId) {
        this.cancelReasonId = cancelReasonId;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Order withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Order withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getCancelReasonManual() {
        return cancelReasonManual;
    }

    public void setCancelReasonManual(String cancelReasonManual) {
        this.cancelReasonManual = cancelReasonManual;
    }

    public Order withCancelReasonManual(String cancelReasonManual) {
        this.cancelReasonManual = cancelReasonManual;
        return this;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public Order withCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
        return this;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Order withDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public String getDeliveryAt() {
        return deliveryAt;
    }

    public void setDeliveryAt(String deliveryAt) {
        this.deliveryAt = deliveryAt;
    }

    public Order withDeliveryAt(String deliveryAt) {
        this.deliveryAt = deliveryAt;
        return this;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public Order withItemCount(String itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Order withCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    public String getStatusTranslation() {
        return statusTranslation;
    }

    public void setStatusTranslation(String statusTranslation) {
        this.statusTranslation = statusTranslation;
    }

    public Order withStatusTranslation(String statusTranslation) {
        this.statusTranslation = statusTranslation;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Order withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order withUser(User user) {
        this.user = user;
        return this;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }



    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Order withStore(Store store) {
        this.store = store;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Order withItems(List<Item> items) {
        this.items = items;
        return this;
    }

}
