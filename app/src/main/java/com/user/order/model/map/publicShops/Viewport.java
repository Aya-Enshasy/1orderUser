package com.user.order.model.map.publicShops;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Viewport implements Serializable {

    @SerializedName("northeast")
    private Location northeast;

    @SerializedName("southwest")
    private Location southwest;

    public Location getNortheast() {
        return northeast;
    }

    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    public Location getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }
}
