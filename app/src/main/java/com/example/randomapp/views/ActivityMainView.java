package com.example.randomapp.views;

import android.os.Bundle;
import android.widget.Button;

import com.example.calculator3.R;

public class ActivityMainView extends View {
    private Button citiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associateElements();
    }
    @Override
    protected void associateElements() {
        citiesButton = findViewById(R.id.mainCitiesButton);
        citiesButton.setOnClickListener(this::gotoCitiesPage);
    }

    private void gotoCitiesPage(android.view.View view) {
        toView(view.getContext(), CitiesListView.class,null,0);
    }
}
