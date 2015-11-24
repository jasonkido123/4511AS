package ict.db;

import ict.bean.Shopping;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemDb {

    private String url = "";
    private String username = "";
    private String password = "";

    public ItemDb(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            //System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cnnct;
        return cnnct = DriverManager.getConnection(url, username, password);
    }

    public void createItemDb() {
        Connection conn = null;
        Statement stmnt = null;
        try {
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Item("
                    + "ItemId varchar(5) NOT NULL,"
                    + "Item_name varchar(25) NOT NULL,"
                    + "price int(5) NOT NULL,"
                    + "category varchar(20) NOT NULL,"
                    + "descriptions varchar(100) NOT NULL,"
                    + "brand varchar(20) NOT NULL,"
                    + "quantity Integer(20) NOT NULL,"
                    + "point Integer(20) NOT NULL,"
                    + "PRIMARY KEY(ItemId)"
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

    public boolean addItem(String ItemId, String Item_name, double price, String category, String descriptions, String brand, int quantity, int point) {


        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into Item (ItemId, Item_name, price, category, descriptions,brand,quantity,point) values (?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ItemId);
            pStmnt.setString(2, Item_name);
            pStmnt.setDouble(3, price);
            pStmnt.setString(4, category);
            pStmnt.setString(5, descriptions);
            pStmnt.setString(6, brand);
            pStmnt.setInt(7, quantity);
            pStmnt.setInt(8, point);
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

    public ArrayList AllItem()throws  IOException,SQLException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        cnnct = getConnection();
        String preQueryStatement = "SELECT * FROM item";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        return SearchFactory(pStmnt,cnnct);
    }
    public ArrayList SearchByPrice(int min,int max)throws  IOException,SQLException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        cnnct = getConnection();
        String preQueryStatement = "SELECT * FROM item WHERE price>=? AND price<=?";

        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setInt(1, min);
        pStmnt.setInt(2, max);
        return SearchFactory(pStmnt,cnnct);
    }
    public ArrayList SearchFactory(PreparedStatement pStmnt,Connection cnnct){
        ResultSet rs = null;
        ArrayList<Shopping> al = null;
        try {
            
            //String preQueryStatement = "SELECT * FROM item";
            cnnct = getConnection();
            rs = pStmnt.executeQuery();
            al = new ArrayList();
            while (rs.next()) {
                Shopping sp = new Shopping();
                sp.setItemId(rs.getString("ItemId"));
                sp.setPrice(rs.getDouble("price"));
                sp.setPoint(rs.getInt("point"));
                sp.setQuantity(rs.getInt("quantity"));
                sp.setBrand(rs.getString("brand"));
                sp.setCategory(rs.getString("category"));
                sp.setDescriptions(rs.getString("descriptions"));
                sp.setItemName(rs.getString("item_name"));
                al.add(sp);
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
