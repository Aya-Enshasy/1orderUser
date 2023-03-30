package com.user.order.model.city;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cities {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    public List<City> cities;

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
