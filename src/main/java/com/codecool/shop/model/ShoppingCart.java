package com.codecool.shop.model;


public class ShoppingCart {

    private int id;
    private String status;

    public ShoppingCart() {

        this.id = (int) (Math.random() * 10000);
        this.status = "New";

    }

//    public int total(){
//
//        for (Product prod : ShoppingCartDaoMem.getInstance().getAll()) {
//            totalcost += prod.subtotal();
//        }
//        return totalcost;
//    }

    public int getId() { return id; }

    public String getStatus() { return status; }

    public void setStatus(String status) {this.status = status;}

    public String toString(){
        return String.format("id: %1$d, " + "status: %2$s " + this.id + this.status);
    }
}

