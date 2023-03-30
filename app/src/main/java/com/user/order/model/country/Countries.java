package com.user.order.model.country;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Countries {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private List<Country> countries;

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

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
