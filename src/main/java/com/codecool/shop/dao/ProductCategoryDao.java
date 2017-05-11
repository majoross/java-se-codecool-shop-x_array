package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);

    ProductCategory find(int id)throws IllegalArgumentException;

    void remove(int id)throws IllegalArgumentException;

    List<ProductCategory> getAll();

}
