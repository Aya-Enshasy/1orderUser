package com.user.order.model.cart;

import com.google.gson.annotations.SerializedName;

public class CartSend {

    @SerializedName("shop_id")
    private Integer shopId;

    @SerializedName("type_of_receive")
    private String typeOfReceive;

    @SerializedName("delivery_type")
    private String deliveryType;

    @SerializedName("coupon_id")
    private Integer couponId;

    @SerializedName("country_id")
    private Integer countryId;

    @SerializedName("items[0][id]")
    private Integer id;

    @SerializedName("items[0][qty]")
    private Integer qty;

    @SerializedName("items[0][price]")
    private Double price;

    @SerializedName("items[0][extra][0][item_id]")
    private Integer extraItemId;

    @SerializedName("items[0][extra][0][id]")
    private Integer extraId;

    @SerializedName("items[0][extra][0][qty]")
    private Integer extraQty;

    @SerializedName("items[0][extra][0][price]")
    private Double extraPrice;

    @SerializedName("payment_type")
    private String paymentType;

    @SerializedName("delivery_at")
    private String deliveryAt;

    @SerializedName("lat")
    private Double shopLat;

    @SerializedName("lng")
    private Double shopLng;

    @SerializedName("delivery")
    private Integer deliveryCost;

    @SerializedName("sub_total_1")
    private Double subTotal1;

    @SerializedName("discount")
    private Integer discount;

    @SerializedName("sub_total_2")
    private Double subTotal2;

    @SerializedName("total")
    private Double total;

    @SerializedName("notes")
    private String notes;

    @SerializedName("destination_lat")
    private Double destinationLat;

    @SerializedName("destination_lng")
    private Double destinationLng;

    @SerializedName("destination_address")
    private String destinationAddress;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getTypeOfReceive() {
        return typeOfReceive;
    }

    public void setTypeOfReceive(String typeOfReceive) {
        this.typeOfReceive = typeOfReceive;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getExtraId() {
        return extraId;
    }

    public void setExtraId(Integer extraId) {
        this.extraId = extraId;
    }

    public Integer getExtraItemId() {
        return extraItemId;
    }

    public void setExtraItemId(Integer extraItemId) {
        this.extraItemId = extraItemId;
    }

    public Integer getExtraQty() {
        return extraQty;
    }

    public void setExtraQty(Integer extraQty) {
        this.extraQty = extraQty;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryAt() {
        return deliveryAt;
    }

    public void setDeliveryAt(String deliveryAt) {
        this.deliveryAt = deliveryAt;
    }

    public Double getShopLat() {
        return shopLat;
    }

    public void setShopLat(Double shopLat) {
        this.shopLat = shopLat;
    }

    public Double getShopLng() {
        return shopLng;
    }

    public void setShopLng(Double shopLng) {
        this.shopLng = shopLng;
    }

    public Integer getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Double getSubTotal1() {
        return subTotal1;
    }

    public void setSubTotal1(Double subTotal1) {
        this.subTotal1 = subTotal1;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getSubTotal2() {
        return subTotal2;
    }

    public void setSubTotal2(Double subTotal2) {
        this.subTotal2 = subTotal2;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(Double destinationLng) {
        this.destinationLng = destinationLng;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
