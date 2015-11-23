package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chanyan
 */
public class ClientDb {
    private String url ="";
    private String username ="";
    private String password ="";
    //private dbGetConnection dbc = new dbGetConnection(url,username,password);
    public ClientDb(String url,String username,String password) {
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
    
    public void createClientDb(){
        Connection conn =null;
        Statement stmnt=null;
        try{
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql ="CREATE TABLE IF NOT EXISTS client("+
                    "clientId varchar(5) NOT NULL,"+
                    "name varchar(25) NOT NULL,"+
                    "tel int(8) NOT NULL,"+
                    "d_address varchar(100) NOT NULL,"+
                    "login_ac varchar(20) NOT NULL,"+
                    "login_pw varchar(16) NOT NULL,"+
                    "login_statues BOOLEAN NOT NULL,"+
                    "balance Numeric(20,2) NOT NULL,"+
                    "point INTEGER(20) NOT NULL,"+
                    "PRIMARY KEY(clientId)"
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
