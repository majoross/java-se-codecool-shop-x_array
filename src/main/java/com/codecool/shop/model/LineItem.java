package com.codecool.shop.model;


import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

public class LineItem{

    private int id;
    private Product product;
    private int quantity;
    static private float sumOfAll;


    public LineItem(Product product, int quantity) {
        this.product = product;
        this.id = product.getId();
        this.quantity = quantity;
        this.sumOfAll += product.getDefaultPrice()*quantity;

    }

    public void changeAmount(int num){
        quantity += num;
        sumOfAll += product.getDefaultPrice()*num;
        if(quantity < 1){
            ShoppingCartDaoMem.getInstance().remove(this);
        }
    }
    public String getName() {return this.product.getName();}

    public int getId() {
        return id;
    }

    public int getProductId() {return this.product.getId();}

    public void setId(int id) {
        this.id = id;
    }

    public Float getUnitPrice() {
        return this.product.getDefaultPrice();
    }

    public Float getTotalPrice() {
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

    public Float getTotalTotalTotalTotal() {
        Float sum = 0f;
        for (LineItem item: ShoppingCartDaoMem.getInstance().getAll()) {
            sum += getTotalPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%f",sumOfAll);
    }
}

