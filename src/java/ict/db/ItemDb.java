package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemDb {
    private String url ="";
    private String username ="";
    private String password ="";
    
    public ItemDb(String url,String username,String password) {
        this.url = url;
        this.username=username;
        this.password=password;
    }
    
    public Connection getConnection() throws SQLException, IOException{
        try {
            //System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cnnct;
        return cnnct = DriverManager.getConnection(url,username,password);
    }
    
    public void createItemDb(){
        Connection conn =null;
        Statement stmnt=null;
        try{
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql ="CREATE TABLE IF NOT EXISTS Item("+
                    "ItemId varchar(5) NOT NULL,"+
                    "Item_name varchar(25) NOT NULL,"+
                    "price Numeric(20,2) NOT NULL,"+
                    "category varchar(20) NOT NULL,"+
                    "descriptions varchar(100) NOT NULL,"+
                    "PRIMARY KEY(ItemId)"
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
    
    public boolean addItem(String ItemId, String Item_name, double price, String category, String descriptions, String photo){

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "insert into Item (ItemId, Item_name, price, category, descriptions,photo) values (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ItemId);
            pStmnt.setString(2, Item_name);
            pStmnt.setDouble(3, price);
            pStmnt.setString(4, category);
            pStmnt.setString(5, descriptions);
            pStmnt.setString(6, photo);
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
