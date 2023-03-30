package com.user.order.model.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartOrderSend {

    @SerializedName("id")
    private int id;

    @SerializedName("qty")
    private int qty;

    @SerializedName("price")
    private double price;

//    @SerializedName("extra")
//    private List<OrderExtraSend> extras;

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

//    public List<OrderExtraSend> getExtras() {
//        return extras;
//    }
//
//    public void setExtras(List<OrderExtraSend> extras) {
//        this.extras = extras;
//    }
}
