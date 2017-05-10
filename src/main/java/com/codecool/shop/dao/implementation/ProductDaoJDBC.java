package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends JDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (id, name, default_price, currency_string, description, category_id, supplier_id)"
                +"VALUES ('" + product.getId() + "', '" + product.getName() + "', '" + product.getDefaultPrice() + "', " +
                "'" + product.getDefaultCurrency() + "', '" + product.getDescription() + "', '" + product.getProductCategory() + "'" +
                ", '" + product.getSupplier() + "');";
        executeQuery(query);
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM products INNER JOIN suppliers ON products.supp_id=suppliers.supplier_id " +
                "INNER JOIN categories ON products.cat_id=categories.category_id WHERE product_id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                ProductCategory category = new ProductCategory(resultSet.getInt("category_id"), resultSet.getString("category_name"),resultSet.getString("department"), resultSet.getString("category_description"));
                Supplier supplier = new Supplier(resultSet.getInt("supplier_id"), resultSet.getString("supplier_name"), resultSet.getString("supplier_description"));

                //need to be able to set PRODUCT ID!
                Product result = new Product(
                        resultSet.getString("product_name"),
                        //resultSet.getString("id"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency_string"),
                        resultSet.getString("product_description"),
                        category,
                        supplier);
                return result;
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
        String query = "DELETE FROM products WHERE id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        List<Product> productsFromDB = new ArrayList<Product>();
        //gets all products objects from the db to productsFromDB arraylist

        return productsFromDB;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> productsFromDB = new ArrayList<Product>();
        //gets all products objects from the db to productsFromDB arraylist by supplier

        return productsFromDB;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> productsFromDB = new ArrayList<Product>();
        //gets all products objects from the db to productsFromDB arraylist by productcategory

        return productsFromDB;
    }

}
