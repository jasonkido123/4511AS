package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDb {
    private String url ="";
    private String username ="";
    private String password ="";
    
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
    public OrderDb(String url,String username,String password) {
        this.url = url;
        this.username=username;
        this.password=password;
    }
    
    public void createOrderDb(){
        Connection conn =null;
        Statement stmnt=null;
        try{
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql ="CREATE TABLE IF NOT EXISTS Orders("+
                    "OrderId varchar(5) NOT NULL,"+
                    "clientId varchar(5) NOT NULL,"+
                    "totalPrice Numeric(20,2) NOT NULL,"+
                    "PricePoint Integer(20) NOT NULL,"+
                    "PaymentMothed char(1) NOT NULL,"+
                    "status varchar(10) NOT NULL,"+
                    "PRIMARY KEY(OrderId)"
                    +")";
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
    
    
}
