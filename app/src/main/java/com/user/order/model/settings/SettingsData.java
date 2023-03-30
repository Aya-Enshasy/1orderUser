package com.user.order.model.settings;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.country.Country;

public class SettingsData {

    @SerializedName("country")
    private Country country;

    @SerializedName("appContent")
    public AppContent appContent;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AppContent getAppContent() {
        return appContent;
    }

    public void setAppContent(AppContent appContent) {
        this.appContent = appContent;
    }
}
