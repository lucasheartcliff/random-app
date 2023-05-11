package com.example.randomapp.service;

import android.view.View;
import android.widget.EditText;

public class CalculatorService {

    public static double sum(double v1, double v2) {
        return v1 + v2;
    }

    public static double sub(double v1, double v2) {
        return sum(v1, -1 * v2);
    }

    public static double multiply(double v1, double v2) {
        return v1 * v2;
    }

    public static double divide(double v1, double v2) {
        return multiply(v1, 1 / v2);
    }

}
