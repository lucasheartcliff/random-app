package com.example.randomapp.views;

import android.os.Bundle;
import android.widget.Button;

import com.example.calculator3.R;
import com.example.randomapp.model.OperationTypes;

public class ActivityMainView extends View {
    private Button calculate,addiction,subtraction,multiplication,division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associateElements();
    }
    @Override
    protected void associateElements() {
        calculate = (Button) findViewById(R.id.mainCalculateButton);
        calculate.setOnClickListener(this::onClickToCalculate);

        addiction = (Button) findViewById(R.id.mainCalculateAdditctionButton);
        addiction.setOnClickListener((v)->this.addOperationTypeAndCallView(OperationTypes.ADDITION));

        subtraction = (Button) findViewById(R.id.mainCalculateSubtractionButton);
        subtraction.setOnClickListener((v)->this.addOperationTypeAndCallView(OperationTypes.SUBTRACTION));

        multiplication = (Button) findViewById(R.id.mainCalculateMultiplicationButton);
        multiplication.setOnClickListener((v)->this.addOperationTypeAndCallView(OperationTypes.MULTIPLICATION));

        division = (Button) findViewById(R.id.mainCalculateDivisionButton);
        division.setOnClickListener((v)->this.addOperationTypeAndCallView(OperationTypes.DIVISION));
    }

    private void addOperationTypeAndCallView(OperationTypes type){
        toView(ActivityMainView.this, CalculatorView.class,type,0);
    }

    private void onClickToCalculate(android.view.View view) {
        toView(ActivityMainView.this, FormView.class);
    }
}
