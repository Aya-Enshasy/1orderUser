package com.user.order.model.settings;

import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private SettingsData data;

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

    public SettingsData getData() {
        return data;
    }

    public void setData(SettingsData data) {
        this.data = data;
    }
}
