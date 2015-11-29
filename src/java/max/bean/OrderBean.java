/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.bean;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author YIKFEI
 */
public class OrderBean {

    String orderId, clientId, status;
    int pircePoint;
    BigDecimal totalPrice;
    char paymentmethod;
    Date orderTime;

    public int getPircePoint() {
        return pircePoint;
    }

    public void setPircePoint(int pircePoint) {
        this.pircePoint = pircePoint;
    }
    
    

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public char getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(char paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    
    

}
