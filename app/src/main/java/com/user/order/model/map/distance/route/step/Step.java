package com.user.order.model.map.distance.route.step;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.map.distance.Location;
import com.user.order.model.map.distance.route.leg.Distance;
import com.user.order.model.map.distance.route.leg.Duration;

public class Step {

    @SerializedName("distance")
    private Distance distance;

    @SerializedName("duration")
    private Duration duration;

    @SerializedName("end_location")
    private Location endLocation;

    @SerializedName("html_instructions")
    private String htmlInstructions;

    @SerializedName("polyline")
    private Polyline polyline;

    @SerializedName("start_location")
    private Location startLocation;

    @SerializedName("travel_mode")
    private String travelMode;

    @SerializedName("maneuver")
    private String maneuver;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }
}
