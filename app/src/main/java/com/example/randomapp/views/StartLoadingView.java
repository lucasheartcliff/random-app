package com.example.randomapp.views;

import android.os.Bundle;

import com.example.calculator3.R;

public class StartLoadingView extends View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_loading);

        toView(StartLoadingView.this, ActivityMainView.class, null,3000);
    }

    @Override
    protected void associateElements() {

    }
}







