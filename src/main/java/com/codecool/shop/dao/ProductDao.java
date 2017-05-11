package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {


    void add(Product product);

    Product find(int id) throws  IllegalArgumentException;

    void remove(int id)throws  IllegalArgumentException;

    List<Product> getAll();

    List<Product> getBy(Supplier supplier);

    List<Product> getBy(ProductCategory productCategory);

}
