package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoJDBC extends JDBC implements ShoppingCartDao{
    private boolean isThere;

    //initially there are no shopping cart instances
    private static ShoppingCartDaoJDBC instance = null;

    //private constructor
    private ShoppingCartDaoJDBC(){}

    //if there are no instances of cart, creates one
    public static ShoppingCartDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(LineItem item) {
        //Add to db (add lineItem :'()
        isThere = false;
    }

    @Override
    public void remove(LineItem item) {
        //remove from db
    }

    //public void review(int id) { /*redirect to Shopping cart main page*/ } dafuq is this?

    @Override
    public LineItem find(int id) {
        //find lineitem from db...
        LineItem foundLineItem = null;

        return foundLineItem;
    }

    @Override
    public List<LineItem> getAll() {
        //get all lineitem objects from db (shopping cart table)
        List<LineItem> lineItemsFromDB = new ArrayList<LineItem>();

        return lineItemsFromDB;
    }

    @Override
    public LineItem getFirst() {
        //get the first item(is it used?)
        LineItem lineitem= null;

        return lineitem;}

    public String getTotal() {
//        LineItem item = DATA.stream().findFirst().orElse(null);
//        if (item != null) {
//            return String.format("%d %s", item.getSumOfAll(),item.getDefaultCurrency());
//        }else {
//            return "0 USD";
//        }
        //gets total from db... somehow...
    return "total";
    }
}
