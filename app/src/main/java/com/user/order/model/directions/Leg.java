
package com.user.order.model.directions;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leg implements Serializable
{

    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("duration")
    @Expose
    private Duration duration;
    @SerializedName("end_address")
    @Expose
    private String endAddress;
    @SerializedName("end_location")
    @Expose
    private EndLocation endLocation;
    @SerializedName("start_address")
    @Expose
    private String startAddress;
    @SerializedName("start_location")
    @Expose
    private StartLocation startLocation;
    @SerializedName("steps")
    @Expose
    private List<Step> steps;
    @SerializedName("traffic_speed_entry")
    @Expose
    private List<Object> trafficSpeedEntry;
    @SerializedName("via_waypoint")
    @Expose
    private List<Object> viaWaypoint;
    private final static long serialVersionUID = -8010751620229932496L;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Leg withDistance(Distance distance) {
        this.distance = distance;
        return this;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Leg withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Leg withEndAddress(String endAddress) {
        this.endAddress = endAddress;
        return this;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public Leg withEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public Leg withStartAddress(String startAddress) {
        this.startAddress = startAddress;
        return this;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public Leg withStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Leg withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public List<Object> getTrafficSpeedEntry() {
        return trafficSpeedEntry;
    }

    public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
    }

    public Leg withTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
        return this;
    }

    public List<Object> getViaWaypoint() {
        return viaWaypoint;
    }

    public void setViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }

    public Leg withViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
        return this;
    }

}
