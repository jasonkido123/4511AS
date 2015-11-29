package ict.db;


import ict.bean.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderInfoDb {
    private String url ="";
    private String username ="";
    private String password ="";
    
    public OrderInfoDb(String url,String username,String password) {
        this.url = url;
        this.username=username;
        this.password=password;
    }
    public java.sql.Connection getConnection() throws SQLException,IOException{
        //System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
        //return DriverManager.getConnection(url,username,password);
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            //Logger.getLogger(dbGetConnection.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
        }
        java.sql.Connection cnnct = DriverManager.getConnection(url,username,password);
        return cnnct;
    }
    public void createOrderDb(){
        Connection conn =null;
        Statement stmnt=null;
        try{
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql ="CREATE TABLE IF NOT EXISTS orderInfo("+
                    "OrderId varchar(5) NOT NULL,"+
                    "ItemId varchar(5) NOT NULL,"+
                    "quantity Integer(20) NOT NULL,"+
                    "price Numeric(20,2) NOT NULL,"+
                    "point INTEGER(20) NOT NULL"+
                    ")";
            stmnt.execute(sql);
            stmnt.close();
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            ex = ex.getNextException();
        }catch(IOException e){
            e.printStackTrace();
        }
    } 
    
    public ArrayList queryOrderInfoById(String oid){
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        Item cb = null;
        ArrayList <OrderInfo> alo = new ArrayList<OrderInfo> ();
        try{
            cnnct = getConnection();
            String preQueryStatment = "select * from orderinfo where OrderId=? ";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, oid);
            ResultSet rs = null;
            rs = pStnmt.executeQuery();
            while (rs.next()){
                OrderInfo oi = new OrderInfo();
                oi.setItemId(rs.getString("itemId"));
                oi.setOrderId(rs.getString("orderId"));
                oi.setPoint(rs.getInt("point"));
                oi.setPrice(rs.getDouble("price"));
                oi.setQuantity(rs.getInt("quantity"));
                alo.add(oi);
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return alo;
    }
    public boolean addOrderinfo(String orderId, String ItemId, double price, int point,int quantity) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into orderinfo (OrderId, ItemId, price, point,quantity) values (?,?,?,?,?)";
            System.out.println(orderId);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderId);
            pStmnt.setString(2, ItemId);
            pStmnt.setDouble(3, price);
            pStmnt.setInt(5, quantity);
            pStmnt.setInt(4, point);
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
}
