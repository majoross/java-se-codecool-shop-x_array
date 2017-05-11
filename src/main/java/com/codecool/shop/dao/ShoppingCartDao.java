package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;

import java.util.List;

public interface ShoppingCartDao {

    //EDIT FEATURE - Add more of a certain item or remove upon clicking the associated button
    void add(LineItem item);

    void remove(LineItem item);

    //Find an item(Product class is called) by ID
    LineItem find(int id);

    LineItem getFirst();

    //A list with all products in the cart
    List<LineItem> getAll();

    String getTotal();

    void changeAmount(LineItem item, int num);
}

