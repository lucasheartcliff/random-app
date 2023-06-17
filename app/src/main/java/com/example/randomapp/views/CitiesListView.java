package com.example.randomapp.views;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.calculator3.R;
import com.example.randomapp.adapters.CityAdapter;
import com.example.randomapp.model.City;
import com.example.randomapp.service.CityService;

import java.util.ArrayList;
import java.util.List;

public class CitiesListView extends View{
    private ListView listView;
    private Button newItemButton;
    private CityAdapter listAdapter;

    private CityService cityService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_list);
        cityService = new CityService(getSharedPreferences());
        associateElements();

    }
    @Override
    protected void associateElements() {
        listView = findViewById(R.id.list_view);
        newItemButton = findViewById(R.id.newItemButton);
        newItemButton.setOnClickListener(v->toView(v.getContext(),FormView.class));

        List<City> itemList = cityService.getAll();

        listAdapter = new CityAdapter(this, itemList,this::onClickRow);
        listView.setAdapter(listAdapter);
    }

    private void onClickRow(List<Object> objects) {
        Context c= (Context)objects.get(0);
        Integer idx = (Integer)objects.get(1);
        City city = (City) objects.get(2);

        Object[] objs = {idx,city};
        toView(c,FormView.class,objs,0);
    }
}
