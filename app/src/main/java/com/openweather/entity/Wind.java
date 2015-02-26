package com.openweather.entity;

/**
 * Created by Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class Wind {

    private double speed;
    private double gust;
    private double deg;

    public Wind() {
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
