
package com.user.order.model.directions;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bounds implements Serializable
{

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;
    private final static long serialVersionUID = 3249920788651921699L;

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Bounds withNortheast(Northeast northeast) {
        this.northeast = northeast;
        return this;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    public Bounds withSouthwest(Southwest southwest) {
        this.southwest = southwest;
        return this;
    }

}
