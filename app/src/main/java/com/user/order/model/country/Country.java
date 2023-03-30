package com.user.order.model.country;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("currency_code")
    private String currencyCode;

    @SerializedName("phone_code")
    private String phoneCode;

    @SerializedName("phone_length")
    private String phoneLength;

    @SerializedName("name")
    private String name;

    @SerializedName("setting")
    private CountrySetting setting;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneLength() {
        return phoneLength;
    }

    public void setPhoneLength(String phoneLength) {
        this.phoneLength = phoneLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountrySetting getSetting() {
        return setting;
    }

    public void setSetting(CountrySetting setting) {
        this.setting = setting;
    }
}
