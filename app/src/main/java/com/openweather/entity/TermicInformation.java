package com.openweather.entity;

/**
 * Created by Ignacio Rojas González and Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class TermicInformation {

    private double temp;
    private double humidity;
    private double pressure;
    private double temp_min;
    private double temp_max;

    public TermicInformation(){

    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
