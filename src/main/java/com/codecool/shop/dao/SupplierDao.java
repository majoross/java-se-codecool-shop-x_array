package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);

    Supplier find(int id) throws IllegalArgumentException;

    void remove(int id)throws IllegalArgumentException;

    List<Supplier> getAll();
}
