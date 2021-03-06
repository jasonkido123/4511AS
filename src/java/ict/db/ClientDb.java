package ict.db;

import ict.bean.ClientInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDb {

    private String url = "";
    private String username = "";
    private String password = "";

    public ClientDb(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

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

    public void createClientDb() {
        Connection conn = null;
        Statement stmnt = null;
        try {
            conn = getConnection();
            stmnt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS client("
                    + "clientId varchar(5) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "tel int(8) NOT NULL,"
                    + "d_address varchar(100) NOT NULL,"
                    + "login_ac varchar(20) NOT NULL,"
                    + "login_pw varchar(16) NOT NULL,"
                    + "login_statues varchar(10) NOT NULL,"
                    + "balance Numeric(20,2) NOT NULL,"
                    + "point INTEGER(20) NOT NULL,"
                    + "admin varchar(1) NOT NULL,"
                    + "PRIMARY KEY(clientId)"
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

    public boolean isValidUser(String login_ac, String login_pw) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            //1. getb Connection
            String preQueryStatement = "SELECT * FROM client WHERE login_ac =  ?";
            //2. get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placeholders with username and pwd
            pStmnt.setString(1, login_ac);
            //4. execute the query and assign to the result
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                if (login_ac.equals(rs.getString("login_ac"))) {
                    isValid = login_pw.equals(rs.getString("login_pw"));
                }
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
        return isValid;
    }

    public boolean addClientInfo(String clientId, String name, int tel, String d_address, String login_ac, String login_pw, String login_statues, double balance, int point, String admin) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into client (clientId,name,tel,d_address,login_ac,login_pw,login_statues,balance,point,admin) values (?,?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, clientId);
            pStmnt.setString(2, name);
            pStmnt.setInt(3, tel);
            pStmnt.setString(4, d_address);
            pStmnt.setString(5, login_ac);
            pStmnt.setString(6, login_pw);
            pStmnt.setString(7, login_statues);
            pStmnt.setDouble(8, balance);
            pStmnt.setInt(9, point);
            pStmnt.setString(10, admin);
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

    public ArrayList queryCust() {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        ClientInfo cb = null;
        ArrayList<ClientInfo> cbs = new ArrayList<ClientInfo>();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from client";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = null;
            rs = pStnmt.executeQuery();
            while (rs.next()) {
                cb = new ClientInfo();
                String clientid = rs.getString("clientId");
                String name = rs.getString("name");
                int tel = rs.getInt("tel");
                String address = rs.getString("d_address");
                String username = rs.getString("login_ac");
                String password = rs.getString("login_pw");
                String status = rs.getString("login_statues");
                double balance = rs.getDouble("balance");
                int point = rs.getInt("point");
                String admin = rs.getString("admin");

                cb.setId(clientid);
                cb.setName(name);
                cb.setTel(tel);
                cb.setAddress(address);
                cb.setUsername(username);
                cb.setPassword(password);
                cb.setStatus(status);
                cb.setBalance(balance);
                cb.setPoint(point);
                cb.setAdmin(admin);
                cbs.add(cb);
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cbs;
    }

    public boolean delRecord(String clientID) {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatment = "delete from client where clientId = ?";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, clientID);
            int rowCount = pStnmt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean checkAdmin(String username) {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        boolean isAdmin = false;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select admin from client where login_ac=?";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, username);
            ResultSet rs = null;
            rs = pStnmt.executeQuery();
            if (rs.next()) {
                isAdmin = rs.getString("admin").equals("Y");
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isAdmin;
    }

    public boolean editRecord(ClientInfo cb) {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatment = "update client set name = ?, tel = ?, d_address = ?, login_ac=?, login_pw=?, login_statues=?, balance=?, point=?, admin=? where clientId = ?";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, cb.getName());
            pStnmt.setInt(2, cb.getTel());
            pStnmt.setString(3, cb.getAddress());
            pStnmt.setString(4, cb.getUsername());
            pStnmt.setString(5, cb.getPassword());
            pStnmt.setString(6, cb.getStatus());
            pStnmt.setDouble(7, cb.getBalance());
            pStnmt.setInt(8, cb.getPoint());
            pStnmt.setString(9, cb.getAdmin());
            pStnmt.setString(10, cb.getId());
            int rowCount = pStnmt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean updateRecord(ClientInfo cb) {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatment = "update client set name = ?, tel = ?, d_address = ?, login_ac=?, login_pw=? where clientId = ?";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, cb.getName());
            pStnmt.setInt(2, cb.getTel());
            pStnmt.setString(3, cb.getAddress());
            pStnmt.setString(4, cb.getUsername());
            pStnmt.setString(5, cb.getPassword());
            pStnmt.setString(6, cb.getId());
            int rowCount = pStnmt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public String queryID(String username, String password) {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        String id = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from client where login_ac=? and login_pw=?";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, username);
            pStnmt.setString(2, password);
            ResultSet rs = null;
            rs = pStnmt.executeQuery();
            if (rs.next()) {
                if (username.equals(rs.getString("login_ac")) && password.equals(rs.getString("login_pw"))) {
                    id = rs.getString("clientId");
                }
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public ClientInfo queryCustByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStnmt = null;
        ClientInfo cb = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from client where clientId = ?";
            pStnmt = cnnct.prepareStatement(preQueryStatment);
            pStnmt.setString(1, id);
            ResultSet rs = null;
            rs = pStnmt.executeQuery();
            if (rs.next()) {
                cb = new ClientInfo();
                String clientid = rs.getString("clientId");
                String name = rs.getString("name");
                int tel = rs.getInt("tel");
                String address = rs.getString("d_address");
                String username = rs.getString("login_ac");
                String password = rs.getString("login_pw");
                String status = rs.getString("login_statues");
                double balance = rs.getDouble("balance");
                int point = rs.getInt("point");
                String admin = rs.getString("admin");

                cb.setId(clientid);
                cb.setName(name);
                cb.setTel(tel);
                cb.setAddress(address);
                cb.setUsername(username);
                cb.setPassword(password);
                cb.setStatus(status);
                cb.setBalance(balance);
                cb.setPoint(point);
                cb.setAdmin(admin);
            }
            pStnmt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cb;
    }

    public void updatePoint(String orderID, String clientID) {
        
        try {
            Connection c = getConnection();
            String sql = "select totalPrice from orders where OrderId = ?";
            PreparedStatement preStm = c.prepareStatement(sql);
            preStm.setString(1, orderID);
            ResultSet rs = preStm.executeQuery();
            rs.next();
            BigDecimal bd = rs.getBigDecimal("totalPrice");
            long pointsGet = (long) ((bd.doubleValue()/1000) )* 100;
            sql = "update client set point =  point + ?  where clientID = ?";
            preStm = c.prepareStatement(sql);
            preStm.setInt(1, (int) pointsGet);
            preStm.setString(2, clientID);
            preStm.execute();
            preStm.close();
            c.close();
            //System.out.println(pointsGet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
