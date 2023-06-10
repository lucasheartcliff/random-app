package com.example.randomapp.model;

import java.time.Instant;

public class City {
    private String name;
    private String state;
    private int population;
    private Double perCapitaIncome;
    private Instant foundationDate;
    private boolean hasAirPorts;
    private double extensionArea;
    private WeatherOption weather;
    private boolean isTouristic;
    private short rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Double getPerCapitaIncome() {
        return perCapitaIncome;
    }

    public void setPerCapitaIncome(Double perCapitaIncome) {
        this.perCapitaIncome = perCapitaIncome;
    }

    public Instant getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Instant foundationDate) {
        this.foundationDate = foundationDate;
    }

    public boolean hasAirPorts() {
        return hasAirPorts;
    }

    public void setHasAirPorts(boolean hasAirPorts) {
        this.hasAirPorts = hasAirPorts;
    }

    public Double getExtensionArea() {
        return extensionArea;
    }

    public void setExtensionArea(Double extensionArea) {
        this.extensionArea = extensionArea;
    }

    public WeatherOption getWeather() {
        return weather;
    }

    public void setWeather(WeatherOption weather) {
        this.weather = weather;
    }

    public boolean isTouristic() {
        return isTouristic;
    }

    public void setTouristic(boolean touristic) {
        isTouristic = touristic;
    }

    public short getRate() {
        return rate;
    }

    public void setRate(short rate) {
        this.rate = rate;
    }
}
