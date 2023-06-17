package com.example.randomapp.model;

import android.annotation.SuppressLint;

import com.google.gson.Gson;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class City implements Serializable {
    private String name;
    private String state;
    private int population;
    private Double perCapitaIncome;
    private Instant foundationDate;
    private boolean hasAirPorts;
    private double extensionArea;
    private WeatherOption weather;
    private boolean isTouristic;
    private float rate;

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

    public double getExtensionArea() {
        return extensionArea;
    }

    public void setExtensionArea(double extensionArea) {
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean validate(){
        if (name == null || name.isEmpty()) {
            return false;
        }

        if (state == null || state.isEmpty()) {
            return false;
        }

        if (population <= 0) {
            return false;
        }

        if (perCapitaIncome == null || perCapitaIncome <= 0) {
            return false;
        }

        if (extensionArea <= 0) {
            return false;
        }

        if (weather == null) {
            return false;
        }

        if (foundationDate == null) {
            return false;
        }

        return true;
    }

    @SuppressLint("NewApi")
    public static Instant parseDateString(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
