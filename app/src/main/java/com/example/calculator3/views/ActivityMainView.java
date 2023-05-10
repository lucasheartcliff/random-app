package com.example.calculator3.views;

import android.os.Bundle;
import android.widget.Button;

import com.example.calculator3.R;

public class ActivityMainView extends View {
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void associateElements() {
        calculate = (Button) findViewById(R.id.mainCalculateButton);
        calculate.setOnClickListener(this::onClickToCalculate);
    }

    private void onClickToCalculate(android.view.View view) {
        toView(ActivityMainView.this, FormView.class);
    }
}
