package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDb {

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

    public CategoryDb(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void createCategoryDb() {
        Connection conn = null;
        Statement stmnt = null;
        try {
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Category("
                    + "CategoryId varchar(5) NOT NULL,"
                    + "CategoryType varchar(30) NOT NULL,"
                    + "PRIMARY KEY(CategoryId)"
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

    public boolean addCategory(String categoryId, String CategoryType) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into category (CategoryId,CategoryType) values (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, categoryId);
            pStmnt.setString(2, CategoryType);
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

    public ArrayList AllCategory() {
        ArrayList<String> al = new ArrayList();
        ResultSet rs = null;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM category";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("CategoryType");
                al.add(type);
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
