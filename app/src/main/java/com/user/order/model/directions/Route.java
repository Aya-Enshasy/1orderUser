
package com.user.order.model.directions;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route implements Serializable
{

    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("copyrights")
    @Expose
    private String copyrights;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs;
    @SerializedName("overview_polyline")
    @Expose
    private OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("warnings")
    @Expose
    private List<Object> warnings;
    @SerializedName("waypoint_order")
    @Expose
    private List<Object> waypointOrder;
    private final static long serialVersionUID = 6473615131104518840L;

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Route withBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public Route withCopyrights(String copyrights) {
        this.copyrights = copyrights;
        return this;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Route withLegs(List<Leg> legs) {
        this.legs = legs;
        return this;
    }

    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public Route withOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Route withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public Route withWarnings(List<Object> warnings) {
        this.warnings = warnings;
        return this;
    }

    public List<Object> getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public Route withWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
        return this;
    }

}
