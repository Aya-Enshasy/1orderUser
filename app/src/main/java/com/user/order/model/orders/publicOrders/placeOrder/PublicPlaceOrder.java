package com.user.order.model.orders.publicOrders.placeOrder;

import com.google.gson.annotations.SerializedName;

public class PublicPlaceOrder {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("public_order_id")
    private int publicOrderId;

    @SerializedName("type")
    private String type;

    @SerializedName("order_number")
    private String orderNumber;

    @SerializedName("order_timestamp")
    private int timestamp;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPublicOrderId() {
        return publicOrderId;
    }

    public void setPublicOrderId(int publicOrderId) {
        this.publicOrderId = publicOrderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
