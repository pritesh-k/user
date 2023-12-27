package com.app.user.model.api;

public class AddressInfo {
    private String address;
    private String city;
    private Coordinates coordinates;
    private String postalCode;
    private String state;

    // Constructors, getters, and setters

    public AddressInfo() {
        // Default constructor
    }

    public AddressInfo(String address, String city, Coordinates coordinates, String postalCode, String state) {
        this.address = address;
        this.city = city;
        this.coordinates = coordinates;
        this.postalCode = postalCode;
        this.state = state;
    }

    // Getter and setter methods for each field

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
