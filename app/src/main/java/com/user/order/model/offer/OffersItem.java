
package com.user.order.model.offer;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffersItem implements Serializable
{

    @SerializedName("item")
    @Expose
    private Item item;
    private final static long serialVersionUID = 3690224116677855485L;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OffersItem withItem(Item item) {
        this.item = item;
        return this;
    }

}
