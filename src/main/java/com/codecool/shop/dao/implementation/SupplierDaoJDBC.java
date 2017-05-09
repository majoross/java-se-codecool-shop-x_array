package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {
    private static SupplierDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        //add supplier to db
    }

    @Override
    public Supplier find(int id) {
        //find supplier, construct object
        Supplier supplier = null;
        return supplier;
    }


    @Override
    public void remove(int id) {
        //remove supplier from db
    }

    @Override
    public List<Supplier> getAll() {
        //get all suppliers from db as an arrayList
        List<Supplier> suppliersFromDB = new ArrayList<Supplier>();

        return suppliersFromDB;
    }
}
