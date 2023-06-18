package com.example.randomapp.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.calculator3.R;
import com.example.randomapp.dao.ProductDAO;
import com.example.randomapp.model.Product;
import com.example.randomapp.service.ProductService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class FormView extends View {
    private ProductService productService;
    private EditText editName;
    private EditText editBrand;
    private CheckBox checkboxRegulated;
    private RatingBar editRate;
    private Button btnSave;
    private Integer idx;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        associateElements();
        productService = new ProductService(new ProductDAO(this));
        Optional<Object[]> optional = getParamsObject();

        if(optional.isPresent()){
            idx = (Integer) optional.get()[0];
            btnSave.setOnClickListener(v->this.onClickToSave(v,idx));
            Product product = (Product)optional.get()[1];
            setViewFromProduct(product);
        }
    }

    @Override
    protected void associateElements() {
        editName = findViewById(R.id.edit_name);
        editBrand = findViewById(R.id.edit_brand);
        checkboxRegulated = findViewById(R.id.checkbox_regulated);
        editRate = findViewById(R.id.rate);
        editRate.setNumStars(5);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this::onClickToSave);
    }

    private void onClickToSave(android.view.View view) {
    this.onClickToSave(view,null);

    }
    @SuppressLint("NewApi")
    private void onClickToSave(android.view.View view, Integer idx) {
        Product product = getProductFromView();

        Optional<Object[]> optional = getParamsObject();

        if(optional.isPresent()) {
            Product p = (Product) optional.get()[1];
            product.setCreatedAt(p.getCreatedAt());
            product.setUpdatedAt(p.getUpdatedAt());
        }

        if(idx !=null) productService.update(product);
        else productService.create(product);

        toView(view.getContext(), ProductListView.class);

    }

    @SuppressLint("NewApi")
    public Product getProductFromView() {
        Product product = new Product();

        product.setName(editName.getText().toString());
        product.setBrand(editBrand.getText().toString());
        product.setRegulated(checkboxRegulated.isChecked());
        product.setRate((double) editRate.getRating());

        return product;
    }
    @SuppressLint("NewApi")
    public void setViewFromProduct(Product product) {
        editName.setText(product.getName());
        editBrand.setText(product.getBrand());
        checkboxRegulated.setChecked(product.isRegulated());
        editRate.setRating(product.getRate().floatValue());
    }
}
