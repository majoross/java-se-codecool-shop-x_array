package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.sql.*;

/**
 * Created by majoross on 2017.05.08..
 */
public class ProductDaoJdbc {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "majoross";
    private static final String DB_PASSWORD = "Warfront95";
    private static ProductDaoJdbc instance = null;

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    public void add(Product product) {
        try {
            Connection con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO products (id, name, default_price, currency_string, description, category, supplier)" +
                            "VALUES(?,?,?,?,?,?,?);");
            statement.setString(1, String.valueOf(product.getId()));
            statement.setString(2, product.getName());
            statement.setString(3, String.valueOf(product.getDefaultPrice()));
            statement.setString(4, String.valueOf(product.getDefaultCurrency()));
            statement.setString(5, product.getDescription());
            statement.setString(6, String.valueOf(product.getProductCategory()));
            statement.setString(7, String.valueOf(product.getSupplier()));
            statement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }}

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}
