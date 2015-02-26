package com.openweather.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class Clouds {

    @SerializedName("all")
    private double percent;

    public Clouds(){}

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
