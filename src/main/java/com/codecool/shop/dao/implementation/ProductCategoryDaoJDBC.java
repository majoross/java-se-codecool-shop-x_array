package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private static ProductCategoryDaoJDBC instance = null;

    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        //add to db
    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory foundProductCategory = new ProductCategory("s","s","s");
        //find Product category in db => builds object with the parameters.
        return foundProductCategory;
    }


    @Override
    public void remove(int id) {
        //remove from db
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategoriesFromDB = new ArrayList<ProductCategory>();
        //gets all productcategory objects from the db to productsFromDB arraylist

        return productCategoriesFromDB;
    }


}
