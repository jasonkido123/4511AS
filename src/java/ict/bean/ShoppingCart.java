/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author chanyan
 */
public class ShoppingCart {
    private String ItemId,name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemId() {
        return ItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPoint() {
        return point;
    }

    public double getPrice() {
        return price;
    }
    private int quantity=0,point;
    private double price;
}
