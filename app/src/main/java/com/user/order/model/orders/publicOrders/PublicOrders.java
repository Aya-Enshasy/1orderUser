package com.user.order.model.orders.publicOrders;

import com.google.gson.annotations.SerializedName;

public class PublicOrders {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private PublicOrdersData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public PublicOrdersData getData() {
        return data;
    }

    public void setData(PublicOrdersData data) {
        this.data = data;
    }
}
