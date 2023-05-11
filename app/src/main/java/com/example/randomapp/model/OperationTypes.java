package com.example.randomapp.model;

public enum OperationTypes {
    ADDITION("Adição"),
    SUBTRACTION("Subtração"),
    MULTIPLICATION("Multiplicação"),
    DIVISION("Divisão");

    private final String title;

    OperationTypes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
