package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "adam_kovacs";
    private static final String DB_PASSWORD = "postgres";
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
        Product foundProduct = new Product("", 1f, "", "",  new ProductCategory("","",""), new Supplier("",""));

        String query = "SELECT * FROM products WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                //need to be able to set PRODUCT ID!
                Product result = new Product(
                        resultSet.getString("name"),
                        //resultSet.getString("id"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency_string"),
                        resultSet.getString("description"),
                        resultSet.getString("category_id"),//should be a ProductCategory object
                        resultSet.getString("supplier_id"));//should be a Supplier object
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
