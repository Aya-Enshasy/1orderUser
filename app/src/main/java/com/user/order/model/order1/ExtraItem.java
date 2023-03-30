
package com.user.order.model.order1;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtraItem implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("price")
    @Expose
    private String price;
    private final static long serialVersionUID = 7179535539338780211L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExtraItem withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public ExtraItem withItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExtraItem withName(String name) {
        this.name = name;
        return this;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public ExtraItem withQty(String qty) {
        this.qty = qty;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ExtraItem withPrice(String price) {
        this.price = price;
        return this;
    }

}
