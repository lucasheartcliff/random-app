
package com.example.calculator3.model;


import com.example.calculator3.exceptions.FormException;

import java.io.Serializable;

public class FormModel implements Serializable {
    private Integer age;
    private Double height;
    private Double weight;
    private Sex sex;
    private String favoriteFood;
    private boolean isActive;

    private boolean hasValidated = false;


    public void validate() {
        validateField(age, "Idade");
        validateField(height, "Altura");
        validateField(weight, "Peso");
        validateField(sex, "Sexo");
        validateField(favoriteFood, "Comida Favorita");
        if (age <= 15) throw new FormException("A idade deve ser maior do que 15 anos.");
        hasValidated=true;
    }

    private void validateField(Object field, String fieldName) {
        if (field == null) throw new FormException(buildMessage(fieldName));
    }

    private String buildMessage(String field) {
        return "O campo \"" + field + "\" deve ser preenchido.";
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean hasValidated(){
        return hasValidated;
    }
}
