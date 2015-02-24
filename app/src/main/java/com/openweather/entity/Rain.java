package com.openweather.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ignacio Rojas González and Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class Rain {

    @SerializedName("3h")
    private double mililitersInThreeHours;

    public Rain() {
    }

    public double getMililitersInThreeHours() {
        return mililitersInThreeHours;
    }

    public void setMililitersInThreeHours(double mililitersInThreeHours) {
        this.mililitersInThreeHours = mililitersInThreeHours;
    }
}
