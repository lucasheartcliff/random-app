package com.example.randomapp.views;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculator3.R;
import com.example.randomapp.model.OperationTypes;
import com.example.randomapp.service.CalculatorService;

public class CalculatorView extends View {

    private EditText v1, v2;

    private OperationTypes operationType;

    private Button button, backButton;
    private TextView result, title;

    @Override
    protected void associateElements() {
        v1 = (EditText) findViewById(R.id.v1);
        v2 = (EditText) findViewById(R.id.v2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this::onClickButton);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClickToBack);

        result = (TextView) findViewById(R.id.resVal);
        title = (TextView) findViewById(R.id.calculatorTitle);
    }

    private void onClickButton(android.view.View view) {
        Double dv1 = parseEditTextValueToDouble(v1);
        Double dv2 = parseEditTextValueToDouble(v2);
        Double res = null;

        switch (operationType) {
            case ADDITION:
                res = CalculatorService.sum(dv1, dv2);
                break;
            case SUBTRACTION:
                res = CalculatorService.sub(dv1, dv2);
                break;
            case MULTIPLICATION:
                res = CalculatorService.multiply(dv1, dv2);
                break;
            case DIVISION:
                res = CalculatorService.divide(dv1, dv2);
                break;
            default:
        }
        result.setText(String.valueOf(res));

    }

    private void onClickToBack(android.view.View view) {
        toView(CalculatorView.this, ActivityMainView.class);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        associateElements();
        operationType = (OperationTypes) getParamsObject();
        String text = "Calculadora de " + operationType.getTitle();
        title.setText(text);
    }

}
