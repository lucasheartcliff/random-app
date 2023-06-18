package com.example.randomapp.views;

import android.os.Bundle;
import android.widget.Button;

import com.example.calculator3.R;

public class ActivityMainView extends View {
    private Button productsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associateElements();
    }
    @Override
    protected void associateElements() {
        productsButton = findViewById(R.id.mainProductsButton);
        productsButton.setOnClickListener(this::gotoCitiesPage);
    }

    private void gotoCitiesPage(android.view.View view) {
        toView(view.getContext(), ProductListView.class,null,0);
    }
}
