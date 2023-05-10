package com.example.calculator3.views;

import android.os.Bundle;

import com.example.calculator3.R;

public class StartLoadingView extends View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_loading);

        toView(StartLoadingView.this, ActivityMainView.class, 3000);
    }

    @Override
    protected void associateElements() {

    }
}







