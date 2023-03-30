
package com.user.order.model.cancel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelReasons implements Serializable
{

    @SerializedName("cancelOrderReasonsList")
    @Expose
    private List<CancelOrderReasons> cancelOrderReasonsList;
    private final static long serialVersionUID = 4687807613747596444L;

    public List<CancelOrderReasons> getCancelOrderReasonsList() {
        return cancelOrderReasonsList;
    }

    public void setCancelOrderReasonsList(List<CancelOrderReasons> cancelOrderReasonsList) {
        this.cancelOrderReasonsList = cancelOrderReasonsList;
    }

    public CancelReasons withCancelOrderReasonsList(List<CancelOrderReasons> cancelOrderReasonsList) {
        this.cancelOrderReasonsList = cancelOrderReasonsList;
        return this;
    }

}
