package com.openweather.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class City {

    @SerializedName("main")
    private TermicInformation termicInformation;

    @SerializedName("sys")
    private Information information;

    @SerializedName("coord")
    private Coordinates coordinates;

    private List<Weather> weather;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;

    private String name;
    private String base;
    private int id;
    private int cod;

    public City() {
    }

    public TermicInformation getTermicInformation() {
        return termicInformation;
    }

    public void setTermicInformation(TermicInformation termicInformation) {
        this.termicInformation = termicInformation;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
