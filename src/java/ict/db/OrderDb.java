package ict.db;

import ict.bean.Order;
import ict.bean.Shopping;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDb {

    private String url = "";
    private String username = "";
    private String password = "";

    public java.sql.Connection getConnection() throws SQLException, IOException {
        //System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
        //return DriverManager.getConnection(url,username,password);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(dbGetConnection.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
        }
        java.sql.Connection cnnct = DriverManager.getConnection(url, username, password);
        return cnnct;
    }

    public OrderDb(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void createOrderDb() {
        Connection conn = null;
        Statement stmnt = null;
        try {
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Orders("
                    + "OrderId varchar(5) NOT NULL,"
                    + "clientId varchar(5) NOT NULL,"
                    + "totalPrice Numeric(20,2) NOT NULL,"
                    + "PricePoint Integer(20) NOT NULL,"
                    + "PaymentMothed char(1) NOT NULL,"
                    + "status varchar(10) NOT NULL,"
                    + "orderTime DATETIME NOT NULL,"
                    + "PRIMARY KEY(OrderId)"
                    + ")";
            stmnt.execute(sql);
            stmnt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addOrder(String orderId, String clientId, double totalPrice, int PricePoint, String PaymentMothed, String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into orders (OrderId, clientId, totalPrice, PricePoint, PaymentMothed,status,orderTime) values (?,?,?,?,?,?,now())";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderId);
            pStmnt.setString(2, clientId);
            pStmnt.setDouble(3, totalPrice);
            pStmnt.setInt(4, PricePoint);
            pStmnt.setString(5, PaymentMothed);
            pStmnt.setString(6, status);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public String genOrderId() throws SQLException, IOException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        cnnct = getConnection();
        String preQueryStatement = "SELECT * FROM orders WHERE OrderId=( SELECT max(OrderId) FROM orders )";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        ArrayList<Order> al = SearchFactory(pStmnt, cnnct);
        String id;
        try {
            id = al.get(0).getOrderId();
        } catch (IndexOutOfBoundsException e) {
            return "O0001";
        }
        id = id.substring(1,5);
        int temp = Integer.parseInt(id);
        temp++;
        id = Integer.toString(temp);
        if(4-id.length()!=0){
            String temp1="";
            for(int i=0;i<4-id.length();i++){
                temp1 +="0";
            }
            id = temp1+id;
        }
        id = "O"+id;
        return id;
    }

    public ArrayList SearchFactory(PreparedStatement pStmnt, Connection cnnct) {
        ResultSet rs = null;
        ArrayList<Order> al = null;
        try {

            //String preQueryStatement = "SELECT * FROM item";
            cnnct = getConnection();
            rs = pStmnt.executeQuery();
            al = new ArrayList();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getString("OrderId"));
                o.setClientId(rs.getString("clientId"));
                o.setGiftPoint(rs.getInt("PricePoint"));
                o.setTotalPrice(rs.getDouble("totalPrice"));
                o.setStatus(rs.getString("status"));
                o.setPaymentMothed(rs.getString("PaymentMothed"));
                o.setOrderTime(rs.getString("orderTime"));
                al.add(o);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return al;
    }
}
