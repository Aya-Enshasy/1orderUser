package com.user.order.model;

import com.google.gson.annotations.SerializedName;

public class DeliveryCost {

    @SerializedName("status")
    private boolean status;

    @SerializedName("delivery_cost")
    private double deliveryCost;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
