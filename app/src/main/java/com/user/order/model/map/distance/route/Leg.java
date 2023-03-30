package com.user.order.model.map.distance.route;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.map.distance.Location;
import com.user.order.model.map.distance.route.leg.Distance;
import com.user.order.model.map.distance.route.leg.Duration;
import com.user.order.model.map.distance.route.step.Step;

import java.util.List;

public class Leg {

    @SerializedName("distance")
    private Distance distance;

    @SerializedName("duration")
    private Duration duration;

    @SerializedName("end_address")
    private String endAddress;

    @SerializedName("end_location")
    private Location endLocation;

    @SerializedName("start_address")
    private String startAddress;

    @SerializedName("start_location")
    private Location startLocation;

    @SerializedName("steps")
    private List<Step> steps;

    @SerializedName("traffic_speed_entry")
    private List<Object> trafficSpeedEntry;

    @SerializedName("via_waypoint")
    private List<Object> viaWaypoint;

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

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Object> getTrafficSpeedEntry() {
        return trafficSpeedEntry;
    }

    public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
    }

    public List<Object> getViaWaypoint() {
        return viaWaypoint;
    }

    public void setViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }
}
