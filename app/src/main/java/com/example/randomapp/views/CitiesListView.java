package com.example.randomapp.views;

import android.os.Bundle;
import android.widget.ListView;

import com.example.calculator3.R;
import com.example.randomapp.adapters.ListAdapter;
import com.example.randomapp.model.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesListView extends View{
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cities_list);
        associateElements();

    }
    @Override
    protected void associateElements() {
        listView = findViewById(R.id.list_view);

        List<City> itemList = new ArrayList<>();

        listAdapter = new ListAdapter(this, itemList);
        listView.setAdapter(listAdapter);
    }
}
