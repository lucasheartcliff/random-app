package com.example.randomapp.service;

import com.example.randomapp.dao.ProductDAO;
import com.example.randomapp.model.Product;

import java.util.Date;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    public List<Product> getAll(){
        try{
            productDAO.open();
            return productDAO.getAll();
        }finally {
            productDAO.close();
        }
    }

    public Product create(Product product){
        try{
            productDAO.open();
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
            return productDAO.create(product);
        }finally {
            productDAO.close();
        }

    }

    public Product update(Product product){
        try {
            productDAO.open();
            product.setUpdatedAt(new Date());
            return productDAO.update(product);
        }finally {
            productDAO.close();
        }
    }

    public void delete(Product product){
        try {
            productDAO.open();
            productDAO.delete(product.getId());
        }finally {
            productDAO.close();
        }
    }
}
