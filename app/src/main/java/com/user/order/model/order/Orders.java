package com.user.order.model.order;

import com.google.gson.annotations.SerializedName;

public class Orders {

    @SerializedName("success")
    private boolean success;

    @SerializedName("orders")
    private OrdersData orders;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OrdersData getOrders() {
        return orders;
    }

    public void setOrders(OrdersData orders) {
        this.orders = orders;
    }
}