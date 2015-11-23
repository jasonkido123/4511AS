package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDb {
    private String url ="";
    private String username ="";
    private String password ="";
    
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
    
    public boolean isValidUser(String login_ac, String login_pw){
        Connection cnnct = null; 
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        try{
            cnnct = getConnection();
            //1. getb Connection
            String preQueryStatement = "SELECT * FROM client WHERE login_ac =  ? and  login_pw =  ?";
            //2. get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placeholders with username and pwd
            pStmnt.setString(1, login_ac);
            pStmnt.setString(2, login_pw);
            //4. execute the query and assign to the result
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while(ex !=null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isValid; 
    }
    
    public boolean addClientInfo(String clientId, String name, int tel, String d_address, String login_ac, String login_pw, int login_statues, double balance, int point){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "insert into client (clientId,name,tel,d_address,login_ac,login_pw,login_statues,balance,point) values (?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, clientId);
            pStmnt.setString(2, name);
            pStmnt.setInt(3, tel);
            pStmnt.setString(4, d_address);
            pStmnt.setString(5, login_ac);
            pStmnt.setString(6, login_pw);
            pStmnt.setInt(7, login_statues);
            pStmnt.setDouble(8, balance);
            pStmnt.setInt(9, point);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while(ex !=null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
