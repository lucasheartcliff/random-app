package com.example.randomapp.views;

import android.os.Bundle;

import com.example.calculator3.R;

public class LoadingView extends View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        associateElements();

    }

    @Override
    protected void associateElements() {

    }
}
