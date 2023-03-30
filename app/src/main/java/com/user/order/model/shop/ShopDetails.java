package com.user.order.model.shop;

import com.google.gson.annotations.SerializedName;

public class ShopDetails {

    @SerializedName("shop")
    private Shop shop;

    @SerializedName("today")
    private String today;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }
}
