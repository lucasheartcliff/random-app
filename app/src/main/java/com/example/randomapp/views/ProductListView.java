package com.example.randomapp.views;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.calculator3.R;
import com.example.randomapp.adapters.ProductAdapter;
import com.example.randomapp.dao.ProductDAO;
import com.example.randomapp.model.Product;
import com.example.randomapp.service.ProductService;

import java.util.List;

public class ProductListView extends View{
    private ListView listView;
    private Button newItemButton;
    private ProductAdapter listAdapter;

    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);
        productService = new ProductService(new ProductDAO(this));
        associateElements();

    }
    @Override
    protected void associateElements() {
        listView = findViewById(R.id.list_view);
        newItemButton = findViewById(R.id.newItemButton);
        newItemButton.setOnClickListener(v->toView(v.getContext(),FormView.class));

        List<Product> itemList = productService.getAll();

        listAdapter = new ProductAdapter(this, itemList,this::onClickRow,this::onClickToDelete);
        listView.setAdapter(listAdapter);
    }

    private void onClickRow(List<Object> objects) {
        Context c= (Context)objects.get(0);
        Integer idx = (Integer)objects.get(1);
        Product product = (Product) objects.get(2);

        Object[] objs = {idx, product};
        toView(ProductListView.this,FormView.class,objs,0);
    }

    private void onClickToDelete(List<Object> objects) {
        Context c= (Context)objects.get(0);
        Integer idx = (Integer)objects.get(1);
        Product product = (Product) objects.get(2);

        productService.delete(product);
        listAdapter.remove(product);
    }
}
