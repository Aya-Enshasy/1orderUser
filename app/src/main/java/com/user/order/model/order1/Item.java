
package com.user.order.model.order1;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("extra_item_count")
    @Expose
    private String extraItemCount;
    @SerializedName("extra_items")
    @Expose
    private List<ExtraItem> extraItems;
    private final static long serialVersionUID = -8144386087442220678L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Item withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Item withItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item withName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Item withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Item withQty(String qty) {
        this.qty = qty;
        return this;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Item withTotal(String total) {
        this.total = total;
        return this;
    }

    public String getExtraItemCount() {
        return extraItemCount;
    }

    public void setExtraItemCount(String extraItemCount) {
        this.extraItemCount = extraItemCount;
    }

    public Item withExtraItemCount(String extraItemCount) {
        this.extraItemCount = extraItemCount;
        return this;
    }

    public List<ExtraItem> getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(List<ExtraItem> extraItems) {
        this.extraItems = extraItems;
    }

    public Item withExtraItems(List<ExtraItem> extraItems) {
        this.extraItems = extraItems;
        return this;
    }

}
