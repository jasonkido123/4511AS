/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.db;

import java.sql.*;
import java.util.*;
import max.bean.OrderBean;

/**
 *
 * @author YIKFEI
 */
public class OrderDB extends Database {

    public OrderDB(String url, String username, String password) {
        super(url, username, password);
    }

    public ArrayList<OrderBean> queryNonCancelOrders() {
        ResultSet rs = null;
        try {
            Connection c = super.getConnection();
            Statement stm = c.createStatement();
            String sql = "select * from orders where status != \"cancel\"";
            //System.out.println(sql);
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return fillList(rs);
    }

    public ArrayList<OrderBean> fillList(ResultSet rs) {
        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
        try {
            while (rs.next()) {
                OrderBean b = new OrderBean();
                b.setClientId(rs.getString("clientid"));
                b.setOrderId(rs.getString("orderid"));
                b.setTotalPrice(rs.getBigDecimal("totalPrice"));
                b.setPircePoint(rs.getInt("PricePoint"));
                b.setPaymentmethod(rs.getString("paymentmothed").charAt(0));
                b.setStatus(rs.getString("status"));
                b.setOrderTime(rs.getDate("ordertime"));
                orders.add(b);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return orders;
    }

    public void simpleInsert(String orderid, int i) {
        try {
            Connection c = getConnection();
            String sql = "INSERT into orders (orderid, row) value (?,?)";
            PreparedStatement stm = c.prepareStatement(sql);
            //stm.setString(1, col);
            stm.setString(1, orderid);
            stm.setInt(2, i);
            System.out.println(stm);
            stm.execute();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void simpleUpdate(int id, String col, String value) {
        try {
            Connection c = getConnection();
            String sql = "update orders set " + col + " = ? where id = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            //stm.setString(1, col);
            stm.setString(1, value);
            stm.setInt(2, id);
            System.out.println(stm);
            stm.execute();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOrderStatus(String orderid, String status) {
        try {
            Connection c = super.getConnection();
            String sql = "update orders set status = ? where orderid = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, orderid);
            stm.execute();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<OrderBean> queryByString(String col, String keyword) {
        ResultSet rs = null;
        try {
            Connection c = super.getConnection();
            String sql = "select * from orders where " + col + " like '%" + keyword + "%'";
            Statement stm = c.createStatement();
            System.out.println(stm);
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fillList(rs);
    }

    public ArrayList<OrderBean> queryByIDs(String[] ids) {
        String idSet = "";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                idSet += "'" + ids[i] + "'";
            } else {
                idSet += "'" + ids[i] + "'" + ",";
            }
        }

        try {
            Connection c = super.getConnection();
            String sql = "Select * from orders where orderid in ("+idSet+ ")";
            Statement stm = c.createStatement();
            //System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            return fillList(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        //System.out.print(idSet);
        return null;
    }

}
