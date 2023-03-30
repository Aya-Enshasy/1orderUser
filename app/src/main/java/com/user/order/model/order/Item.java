package com.user.order.model.order;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("item_id")
    private String itemId;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("qty")
    private String qty;

    @SerializedName("total")
    private String total;

    @SerializedName("extra_item_count")
    private String extraItemCount;

    @SerializedName("extra_items")
    private List<Object> extraItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getExtraItemCount() {
        return extraItemCount;
    }

    public void setExtraItemCount(String extraItemCount) {
        this.extraItemCount = extraItemCount;
    }

    public List<Object> getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(List<Object> extraItems) {
        this.extraItems = extraItems;
    }
}
