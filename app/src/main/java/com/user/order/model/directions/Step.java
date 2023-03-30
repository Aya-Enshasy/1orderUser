
package com.user.order.model.directions;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step implements Serializable
{

    @SerializedName("distance")
    @Expose
    private Distance__1 distance;
    @SerializedName("duration")
    @Expose
    private Duration__1 duration;
    @SerializedName("end_location")
    @Expose
    private EndLocation__1 endLocation;
    @SerializedName("html_instructions")
    @Expose
    private String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    private Polyline polyline;
    @SerializedName("start_location")
    @Expose
    private StartLocation__1 startLocation;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("maneuver")
    @Expose
    private String maneuver;
    private final static long serialVersionUID = -5826476249536137195L;

    public Distance__1 getDistance() {
        return distance;
    }

    public void setDistance(Distance__1 distance) {
        this.distance = distance;
    }

    public Step withDistance(Distance__1 distance) {
        this.distance = distance;
        return this;
    }

    public Duration__1 getDuration() {
        return duration;
    }

    public void setDuration(Duration__1 duration) {
        this.duration = duration;
    }

    public Step withDuration(Duration__1 duration) {
        this.duration = duration;
        return this;
    }

    public EndLocation__1 getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation__1 endLocation) {
        this.endLocation = endLocation;
    }

    public Step withEndLocation(EndLocation__1 endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public Step withHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
        return this;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Step withPolyline(Polyline polyline) {
        this.polyline = polyline;
        return this;
    }

    public StartLocation__1 getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation__1 startLocation) {
        this.startLocation = startLocation;
    }

    public Step withStartLocation(StartLocation__1 startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public Step withTravelMode(String travelMode) {
        this.travelMode = travelMode;
        return this;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    public Step withManeuver(String maneuver) {
        this.maneuver = maneuver;
        return this;
    }

}
