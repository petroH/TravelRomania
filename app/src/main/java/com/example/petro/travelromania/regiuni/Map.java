package com.example.petro.travelromania.regiuni;

/**
 * Created by Petro on 07-Feb-17.
 */

public class Map {
    private String name;
    private double longitude;
    private double latitude;

    public Map( String name, double longitude, double latitude ) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
