package ict.bean;

import java.io.Serializable;

public class OrderInfo implements Serializable{
    private String orderId,itemId;
    private double price;
    private int point,quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public OrderInfo() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
