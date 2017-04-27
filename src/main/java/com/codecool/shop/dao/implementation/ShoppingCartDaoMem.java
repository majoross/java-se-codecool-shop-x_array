package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    //List to store items in the cart
    private List<Product> DATA = new ArrayList<>();

    //initially there are no shopping cart instances
    private static ShoppingCartDaoMem instance = null;

    //private constructor
    private ShoppingCartDaoMem(){}

    //if there are no instances of cart, creates one
    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
            System.out.println("shopping cart instance created");
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(DATA.size() + 1);
        DATA.add(product);
//        System.out.println(DATA);
    }// add item to cart, which is the DATA list of products

    @Override
    public void remove(Product product) {
        DATA.remove(product);
    }

    //public void review(int id) { /*redirect to Shopping cart main page*/ }

    @Override
    public Product find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return DATA;
    }




    }

