package com.codecool.shop.model;


import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import java.util.Currency;

public class LineItem {

    private int id;
    private Product product;
    private int quantity;
    static private int sumOfAll;


    public LineItem(Product product, int quantity) {
        this.product = product;
        this.id = product.getId();
        this.quantity = quantity;
        this.sumOfAll += (int) product.getDefaultPrice() * quantity;

    }

    public void changeAmount(int num) {
        quantity += num;
        sumOfAll += product.getDefaultPrice() * num;
        if (quantity < 1) {
            ShoppingCartDaoMem.getInstance().remove(this);
        }
    }

    public String getName() {
        return this.product.getName();
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return this.product.getId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnitPrice() {
        return (int) this.product.getDefaultPrice();
    }

    public int getTotalPrice() {
        return getUnitPrice() * this.quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSumOfAll() {
        return sumOfAll;
    }

    public Currency getDefaultCurrency() {
        return this.product.getDefaultCurrency();
    }
}

