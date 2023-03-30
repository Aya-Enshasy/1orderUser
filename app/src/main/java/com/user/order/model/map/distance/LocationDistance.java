package com.user.order.model.map.distance;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.map.distance.route.Route;

import java.util.List;

public class LocationDistance {

    @SerializedName("geocoded_waypoints")
    private List<GeocodedWaypoint> geocodedWaypoints;

    @SerializedName("routes")
    private List<Route> routes;

    @SerializedName("status")
    private String status;


    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
