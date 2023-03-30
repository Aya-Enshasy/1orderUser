package com.user.order.model.cart;

import com.google.gson.annotations.SerializedName;

public class OrderExtraSend {

    @SerializedName("id")
    private int id;

    @SerializedName("qty")
    private int qty;

    @SerializedName("price")
    private double price;

    @SerializedName("item_id")
    private int itemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
