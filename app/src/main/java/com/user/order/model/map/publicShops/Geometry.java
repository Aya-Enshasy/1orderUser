package com.user.order.model.map.publicShops;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geometry implements Serializable {

    @SerializedName("location")
    private Location location;

    @SerializedName("viewport")
    private Viewport viewport;;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
}
