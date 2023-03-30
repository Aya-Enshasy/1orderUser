
package com.user.order.model.delivary;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delivary implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("delivery_cost")
    @Expose
    private Double deliveryCost;
    private final static long serialVersionUID = -2209354080814378939L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }



}
