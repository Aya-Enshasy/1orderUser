package com.user.order.model.map.distance.route.step;

import com.google.gson.annotations.SerializedName;

public class Polyline {

    @SerializedName("points")
    private String points;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
