package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {

    //EDIT FEATURE - Add more of a certain item or remove upon clicking the associated button
    void add(Product product);

    void remove(int id);

    //view shopping cart all products listed
    //void review(ShoppingCart cart);

    //Changes status to checked upon clicking on button "Checkout"
    //void checkout(ShoppingCart cart);

    //Find an item(Product class is called) by ID
    Product find(int id);

    //A list with all products in the cart
    List<Product> getAll();

    //List orders by status - possible extra
    //List<Product> getBy(ShoppingCart status);



}
