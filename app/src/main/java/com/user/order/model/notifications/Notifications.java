package com.user.order.model.notifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notifications {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;


    @SerializedName("data")
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
