package ict.bean;

import java.io.Serializable;

public class Order implements Serializable{
    private String OrderId, clientId;
    private double totalPrice;
    private int giftPoint;

    public Order() {
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(int giftPoint) {
        this.giftPoint = giftPoint;
    }
    
    
}
