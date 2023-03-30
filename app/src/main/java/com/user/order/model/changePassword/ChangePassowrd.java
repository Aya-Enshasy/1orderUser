package com.user.order.model.changePassword;

import com.google.gson.annotations.SerializedName;

public class ChangePassowrd {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private ChangePassowrdData data;

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

    public ChangePassowrdData getData() {
        return data;
    }

    public void setData(ChangePassowrdData data) {
        this.data = data;
    }
}
