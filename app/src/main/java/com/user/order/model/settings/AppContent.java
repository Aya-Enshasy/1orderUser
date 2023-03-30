package com.user.order.model.settings;

import com.google.gson.annotations.SerializedName;

public class AppContent {

    @SerializedName("id")
    private Integer id;

    @SerializedName("facebook_link")
    private String facebookLink;

    @SerializedName("instagram_link")
    private String instagramLink;

    @SerializedName("linkedin_link")
    private String linkedinLink;

    @SerializedName("twitter_link")
    private String twitterLink;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("address")
    private String address;

    @SerializedName("email")
    private String email;


    @SerializedName("country_id")
    private String countryId;

    @SerializedName("client_privacy_title")
    private String clientPrivacyTitle;

    @SerializedName("client_privacy_content")
    private String clientPrivacyContent;

    @SerializedName("driver_privacy_title")
    private String driverPrivacyTitle;

    @SerializedName("driver_privacy_content")
    private String driverPrivacyContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getClientPrivacyTitle() {
        return clientPrivacyTitle;
    }

    public void setClientPrivacyTitle(String clientPrivacyTitle) {
        this.clientPrivacyTitle = clientPrivacyTitle;
    }

    public String getClientPrivacyContent() {
        return clientPrivacyContent;
    }

    public void setClientPrivacyContent(String clientPrivacyContent) {
        this.clientPrivacyContent = clientPrivacyContent;
    }

    public String getDriverPrivacyTitle() {
        return driverPrivacyTitle;
    }

    public void setDriverPrivacyTitle(String driverPrivacyTitle) {
        this.driverPrivacyTitle = driverPrivacyTitle;
    }

    public String getDriverPrivacyContent() {
        return driverPrivacyContent;
    }

    public void setDriverPrivacyContent(String driverPrivacyContent) {
        this.driverPrivacyContent = driverPrivacyContent;
    }
}
