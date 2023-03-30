package com.user.order.model.shop.meals;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meals implements Serializable {

    @SerializedName("items")
    private MealsItems items;

    public MealsItems getItems() {
        return items;
    }

    public void setItems(MealsItems items) {
        this.items = items;
    }
}
