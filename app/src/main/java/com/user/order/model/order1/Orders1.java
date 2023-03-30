
package com.user.order.model.order1;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orders1 implements Serializable
{

    @SerializedName("order")
    @Expose
    private Order order;
    private final static long serialVersionUID = 7416117877535636665L;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Orders1 withOrder(Order order) {
        this.order = order;
        return this;
    }

}
