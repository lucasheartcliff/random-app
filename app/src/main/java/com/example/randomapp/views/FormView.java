package com.example.randomapp.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculator3.R;
import com.example.randomapp.exceptions.FormException;
import com.example.randomapp.model.FormModel;
import com.example.randomapp.model.Sex;
import com.example.randomapp.model.ZoneSituation;
import com.example.randomapp.service.IMCService;

public class FormView extends View {
    EditText ageInput, weightInput, heightInput;
    TextView errorMessage;
    Spinner foodSelect;
    RadioGroup sex;
    CheckBox isActive;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form);
        associateElements();

    }

    @Override
    protected void associateElements() {
        ageInput = (EditText) findViewById(R.id.formAgeInput);
        weightInput = (EditText) findViewById(R.id.formWeightInput);
        heightInput = (EditText) findViewById(R.id.formHeightInput);
        foodSelect = (Spinner) findViewById(R.id.foodSelect);
        errorMessage = (TextView) findViewById(R.id.formErrorText);
        sex = (RadioGroup) findViewById(R.id.formSex);
        isActive = (CheckBox) findViewById(R.id.formIsActiveCheckbox);
        calculate = (Button) findViewById(R.id.formCalculate);
        calculate.setOnClickListener(this::onClickToCalculate);
    }

    @SuppressLint("NonConstantResourceId")
    private void onClickToCalculate(android.view.View view) {
        FormModel formModel = new FormModel();

        formModel.setAge(parseEditTextValueToInteger(ageInput));
        formModel.setHeight(parseEditTextValueToDouble(heightInput));
        formModel.setWeight(parseEditTextValueToDouble(weightInput));

        Sex s = null;
        switch (sex.getCheckedRadioButtonId()) {
            case R.id.formSexMale:
                s = Sex.MALE;
                break;
            case R.id.formSexFemale:
                s = Sex.FEMALE;
                break;
            default:
        }

        formModel.setSex(s);
        formModel.setActive(isActive.isChecked());
        formModel.setFavoriteFood((String) foodSelect.getSelectedItem());

        try {
            formModel.validate();

            toView(FormView.this, LoadingView.class);

            // Calculate

            IMCService imcService = new IMCService();
            ZoneSituation situation = imcService.calculateIMC(formModel);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toView(FormView.this, ResultView.class,new Object[]{formModel,situation}, 0);
            }, 3000);


        } catch (FormException e) {
            errorMessage.setText(e.getMessage());
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
