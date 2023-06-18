package com.example.randomapp.service;

import com.example.randomapp.dao.ProductDAO;
import com.example.randomapp.model.Product;

import java.util.Date;
import java.util.List;

public class ProductService {
    private final  ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    public List<Product> getAll(){
        return productDAO.getAll();
    }

    public Product create(Product product){
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return productDAO.create(product);
    }

    public Product update(Product product){
        product.setUpdatedAt(new Date());
        return productDAO.update(product);
    }

    public void delete(Product product){
        productDAO.delete(product.getId());
    }
}
