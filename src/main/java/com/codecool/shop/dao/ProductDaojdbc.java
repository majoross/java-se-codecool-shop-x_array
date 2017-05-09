package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;

/**
 * Created by majoross on 2017.05.08..
 */
public interface ProductDaojdbc {

    public void add(Product product);

    public Product find(String id);

    public void update(String id, String title);

    public void remove(String id);



}
