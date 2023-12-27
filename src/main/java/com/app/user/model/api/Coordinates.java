package com.app.user.model.api;

public class Coordinates {
    private double lat;
    private double lng;

    // Constructors, getters, and setters

    public Coordinates() {
        // Default constructor
    }

    public Coordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // Getter and setter methods for each field

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
