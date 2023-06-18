package com.example.randomapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.randomapp.helpers.ProductSQLHelper;
import com.example.randomapp.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements AutoCloseable{
    private SQLiteDatabase database;
    private ProductSQLHelper helper;
    private final String[] columns = {ProductSQLHelper.ID,ProductSQLHelper.NAME,ProductSQLHelper.BRAND,ProductSQLHelper.IS_REGULATED,ProductSQLHelper.RATE,ProductSQLHelper.CREATED_AT,ProductSQLHelper.UPDATED_AT};

    public ProductDAO(Context context) {
        helper = new ProductSQLHelper(context);
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    @Override
    public void close() {
        helper.close();
    }

    private ContentValues buildBaseContentValues(Product p){
        ContentValues values = new ContentValues();
        values.put(ProductSQLHelper.NAME, p.getName());
        values.put(ProductSQLHelper.BRAND, p.getBrand());
        values.put(ProductSQLHelper.IS_REGULATED, p.isRegulated());
        values.put(ProductSQLHelper.RATE, p.getRate());
        values.put(ProductSQLHelper.CREATED_AT, p.getCreatedAt().getTime());
        values.put(ProductSQLHelper.UPDATED_AT, p.getUpdatedAt().getTime());
        return values;
    }

    public Product create(Product p) {
            ContentValues values = buildBaseContentValues(p);

        long insertId = database.insert(ProductSQLHelper.TABLE, null,
                values);
        p.setId((int) insertId);
        return p;
    }

    public Product update(Product p) {
        ContentValues values = buildBaseContentValues(p);

        database.update(ProductSQLHelper.TABLE, values, ProductSQLHelper.ID + "=" + p.getId(), null);
        return p;
    }

    public void delete(long id) {

        database.delete(ProductSQLHelper.TABLE, ProductSQLHelper.ID
                + " = " + id, null);
    }
    private Product buildProductFromCursor(Cursor cursor){
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setBrand(cursor.getString(2));
        product.setRegulated(cursor.getInt(3)==1);
        product.setRate(cursor.getDouble(4));
        product.setCreatedAt(new Date(cursor.getLong(5)));
        product.setUpdatedAt(new Date(cursor.getLong(6)));

        return product;
    }
    @SuppressLint("NewApi")
    public Optional<Product> get(long id) {
        Cursor cursor = database.query(ProductSQLHelper.TABLE,
                columns,ProductSQLHelper.ID + " = " + id, null,
                null, null, null);

        if(cursor.isLast()) return Optional.empty();

        cursor.moveToFirst();
        Product product = buildProductFromCursor(cursor);
        cursor.close();

        return Optional.of(product);
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = database.query(ProductSQLHelper.TABLE,
                columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = buildProductFromCursor(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }
}
