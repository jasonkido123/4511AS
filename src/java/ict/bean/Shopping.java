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
public class Shopping {
    private String ItemId,ItemName,category,descriptions,brand;
    private int quantity,point;

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
    private double price;

    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getItemId() {
        return ItemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public double getPrice() {
        return price;
    }
    
}
