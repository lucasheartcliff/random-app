package com.example.calculator3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{
    EditText v1,v2;
    Spinner operationsEl;

    Button button;
    TextView result;
    private Double parseEditTextValueToDouble(EditText et){
        return et.getText() ==null ? null :Double.parseDouble(et.getText().toString());
    }

    private void onClickButton(View v){
        Double dv1 = parseEditTextValueToDouble(v1);
        Double dv2 = parseEditTextValueToDouble(v2);
        Double res = null;

        switch ((int)operationsEl.getSelectedItemId()){
            case 0:
                res =Calculator.sum(dv1,dv2);
                break;
            case 1:
                res =Calculator.sub(dv1,dv2);
                break;
            case 2:
                res =Calculator.multiply(dv1,dv2);
                break;
            case 3:
                res =Calculator.divide(dv1,dv2);
                break;
            case 4:
                res =Calculator.exp(dv1,dv2);
                break;
            case 5:
                res =Calculator.rad(dv1,dv2);
                break;
            default:


        }
        result.setText(String.valueOf(res));
    }

    private void associateAttributes(){
        v1 = (EditText) findViewById(R.id.formAgeInput);
        v2 = (EditText) findViewById(R.id.formWeightInput);
        operationsEl = (Spinner) findViewById(R.id.foodSelect);
        button = (Button) findViewById(R.id.formCalculate);
        button.setOnClickListener(this::onClickButton);

        result = (TextView) findViewById(R.id.resVal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associateAttributes();
    }
}