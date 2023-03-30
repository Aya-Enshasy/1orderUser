
package com.user.order.model.directions;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyline implements Serializable
{

    @SerializedName("points")
    @Expose
    private String points;
    private final static long serialVersionUID = -7751117171804728796L;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Polyline withPoints(String points) {
        this.points = points;
        return this;
    }

}
