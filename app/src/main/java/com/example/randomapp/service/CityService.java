package com.example.randomapp.service;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.example.randomapp.model.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class CityService {
    private static final String OBJECT_KEY = "cities";
    private static final String DRAFT_OBJECT_KEY = "draft";
    private  static final Gson gson = new Gson();

    private final SharedPreferences sh;

    public CityService(SharedPreferences sh) {
        this.sh = sh;
    }


    public List<City> getAll(){
        String storedList = sh.getString(OBJECT_KEY, "[]");
        Type cityListType = new TypeToken<List<City>>(){}.getType();
        return gson.fromJson(storedList,cityListType);
    }

    public void create(City city){
        List<City> cities = getAll();
        cities.add(city);
        persist(cities);
    }

    public void update(int idx, City city){
        List<City> cities = getAll();
        cities.set(idx, city);
        persist(cities);
    }
    private void persist(List<City> cities){
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(OBJECT_KEY,gson.toJson(cities));
        editor.apply();
    }

    public void saveDraft(City city){
        saveDraft(null,city);
    }

    public void saveDraft(Integer idx, City city){
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(DRAFT_OBJECT_KEY+"_city",gson.toJson(city));
        editor.putString(DRAFT_OBJECT_KEY+"_idx",gson.toJson(idx));
        editor.apply();
    }

    public City getCityFromDraft(){
        String storedDraft = sh.getString(DRAFT_OBJECT_KEY+"_city", "null");
        return(City) gson.fromJson(storedDraft,City.class);
    }
    public Integer getIdxFromDraft(){
        String storedDraft = sh.getString(DRAFT_OBJECT_KEY+"_idx", "null");
        return(Integer) gson.fromJson(storedDraft,Integer.class);
    }

    public void clearDraft(){
        saveDraft(null,null);
    }
}
