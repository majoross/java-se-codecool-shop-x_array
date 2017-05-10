package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC extends JDBC implements SupplierDao {
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
        String query = "INSERT INTO suppliers (supplier_id,supplier_name,supplier_description)" +
                "VALUES ('" + supplier.getId() + "','" + supplier.getName() +"','"
                + supplier.getDescription() + "');";
        executeQuery(query);    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE supplier_id = '"+ id +"' ;";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                return new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));
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
        String query = "DELETE FROM suppliers WHERE supplier_id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        //get all suppliers from db as an arrayList
        List<Supplier> suppliersFromDB = new ArrayList<Supplier>();
        String query = "SELECT * FROM suppliers;";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));
                suppliersFromDB.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliersFromDB;
    }
}
