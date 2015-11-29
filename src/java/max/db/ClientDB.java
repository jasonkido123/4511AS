/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.db;

import java.sql.*;
/**
 *
 * @author YIKFEI
 */
public class ClientDB extends Database {

    public ClientDB(String url, String username, String password) {
        super(url, username, password);
    }

    public String nextClientID() {

        try {
            Connection c = super.getConnection();
            String sql = "select max(clientid) AS \"maxid\" from client";
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            String maxid = rs.getString("maxid");
            int nextNum = Integer.parseInt(maxid.substring(1)) + 1;
            String formatStr = "%04d";
            String formatAns = String.format(formatStr, nextNum);
            String nextID = "C" + formatAns;
            return nextID;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

}
