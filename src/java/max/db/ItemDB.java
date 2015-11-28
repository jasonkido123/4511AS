/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.db;

import max.bean.ItemBean;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author YIKFEI
 */
public class ItemDB extends Database {

    public ItemDB(String url, String username, String password) {
        super(url, username, password);
    }

    public ArrayList<ItemBean> queryAll() {
        ResultSet rs = null;

        try {
            Connection c = super.getConnection();
            String Sql = "select * from item";
            Statement stm = c.createStatement();
            rs = stm.executeQuery(Sql);

        } catch (Exception e) {

        }
        return fillList(rs);
    }

    public ArrayList<ItemBean> queryByCondition(String col, String keyword) {
        ResultSet rs = null;
        try {
            String sql = "select * from item where " + col + " like '%"+  keyword+"%'";
            Connection c = super.getConnection();
            Statement stm = c.createStatement();
            
            //PreparedStatement preStm = c.prepareStatement(sql);
            //preStm.setString(1, keyword);
            //System.out.println(sql);
            rs = stm.executeQuery(sql);
        } catch (Exception e) {

        }

        return fillList(rs);
    }

    public ArrayList<ItemBean> fillList(ResultSet rs) {
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();
        try {
            while (rs.next()) {
                ItemBean b = new ItemBean();
                b.setItemId(rs.getString("ItemId"));
                b.setItem_name(rs.getString("Item_name"));
                b.setPrice(rs.getInt("price"));
                b.setCategory(rs.getString("category"));
                b.setDescriptions(rs.getString("descriptions"));
                b.setBrand(rs.getString("brand"));
                b.setQuantity(rs.getInt("quantity"));
                b.setPoint(rs.getInt("point"));
                items.add(b);
            }

        } catch (Exception e) {

        }
        return items;
    }

}
