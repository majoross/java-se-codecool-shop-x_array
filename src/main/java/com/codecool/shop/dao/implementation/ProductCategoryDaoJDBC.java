package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC extends JDBC implements ProductCategoryDao {
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
        String query = "INSERT INTO categories (category_id,category_name,department,category_description)" +
                "VALUES ('" + category.getId() + "','" + category.getName() + "','" + category.getDepartment() +
                "','" + category.getDescription() + "');";
        executeQuery(query);

    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM categories WHERE category_id = '"+ id +"' ;";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                return new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("department"),
                        resultSet.getString("category_description"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void remove(int id) {
        String query = "DELETE FROM categories WHERE category_id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategoriesFromDB = new ArrayList<ProductCategory>();
        String query = "SELECT * FROM categories;";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                ProductCategory category = new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("department"),
                        resultSet.getString("category_description"));
                productCategoriesFromDB.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productCategoriesFromDB;
    }
}
