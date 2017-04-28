package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartDaoMem implements ShoppingCartDao {

    //List to store items in the cart
    private List<LineItem> DATA = new ArrayList<>();

    //initially there are no shopping cart instances
    private static ShoppingCartDaoMem instance = null;

    //private constructor
    private ShoppingCartDaoMem(){}

    //if there are no instances of cart, creates one
    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(LineItem item) {
        if(DATA.contains(item)){
            item.changeAmount(1);
        }
        else {
            DATA.add(item);
        }
//        System.out.println(DATA);
    }// add item to cart, which is the DATA list of products

    @Override
    public void remove(LineItem item) {
        DATA.remove(item);
    }

    //public void review(int id) { /*redirect to Shopping cart main page*/ }

    @Override
    public LineItem find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<LineItem> getAll() {
        return DATA;
    }

    @Override
    public LineItem getFirst() {return DATA.stream().findFirst().orElse(null);}

    public String getTotal() {
        LineItem item = DATA.stream().findFirst().orElse(null);
        if (item != null) {
            return String.format("%d %s", item.getSumOfAll(),item.getDefaultCurrency());
        }else {
            return "0 USD";
        }
    }

}

