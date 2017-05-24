package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCartDaoJDBC extends JDBC implements ShoppingCartDao {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartDaoJDBC.class);
    private static ShoppingCartDaoJDBC instance = null;

    private ShoppingCartDaoJDBC() {
    }

    public static ShoppingCartDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoJDBC();
        }
        return instance;
    }

    public LineItem lineItemSetup(ResultSet resultSet) throws SQLException {
        logger.info("Setting Products!");
        ProductDaoJDBC product = ProductDaoJDBC.getInstance();

        LineItem result = new LineItem(
                resultSet.getInt("prod_id"),
                product.find(resultSet.getInt("prod_id")),
                resultSet.getInt("quantity"));

        return result;
    }


    @Override
    public void add(LineItem item) {
        logger.info("Product added to the cart!");

        String query;
        if (find(item.getProductId()) == null) {
            query = "INSERT INTO shoppingcart( prod_id, quantity, subtotal_price,product_price)" +
                    "VALUES('" + item.getProductId() + "', '" + item.getQuantity() + "', '" + item.getSubtotalPrice() +
                    "', '" + item.getProductDefaultPrice() + "');";
        } else {
            query = "UPDATE shoppingcart SET quantity = quantity + 1 WHERE prod_id = '" + item.getProductId() + "';" +
                    "UPDATE shoppingcart SET subtotal_price = product_price * quantity WHERE prod_id = '"
                    + item.getProductId() + "';";
        }
        executeQuery(query);
    }


    @Override
    public void remove(LineItem item) throws NullPointerException {
        logger.info("Item removed from the cart!");

        String query = "DELETE FROM shoppingcart WHERE prod_id = '" + item.getProductId() + "';";
        executeQuery(query);
    }


    @Override
    public LineItem find(int id) {
        logger.info("Searching for the item in the database!");

        String query = "SELECT * FROM shoppingcart INNER JOIN products " +
                "ON shoppingcart.prod_id=products.product_id WHERE prod_id='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {

                return lineItemSetup(resultSet);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<LineItem> getAll() {
        logger.info("Getting all the products!");

        Integer numberOfItems = 0;
        List<Integer> productIDs = new ArrayList<Integer>();
        List<LineItem> lineItemsFromDB = new ArrayList<LineItem>();

        String query = "SELECT prod_id FROM shoppingcart;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                numberOfItems = resultSet.getInt("prod_id");
                productIDs.add(numberOfItems);
            }
            Collections.sort(productIDs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Integer item : productIDs) {
            lineItemsFromDB.add(find(item));
        }

        return lineItemsFromDB;
    }


    @Override
    public LineItem getFirst() {
        return null;
    }


    @Override
    public String getTotal() {

        Float totalPrice;
        String query = "SELECT SUM(subtotal_price) AS total_price FROM shoppingcart;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {

                totalPrice = resultSet.getFloat("total_price");
                return Float.toString(totalPrice)+" USD";
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void changeAmount(LineItem item, int num) {

        ProductDao product = ProductDaoJDBC.getInstance();

        if (item.getQuantity() == 1 && num == -1) {
            remove(item);
        } else {
            if (num == 1) {
                add(item);
            } else if (num == -1) {

                String query = "UPDATE shoppingcart SET quantity = quantity - 1 WHERE prod_id = '" + item.getProductId() + "';" +
                        "UPDATE shoppingcart SET subtotal_price = product_price * quantity WHERE prod_id = '"
                        + item.getProductId() + "';";

                executeQuery(query);
            }
        }
    }
}
