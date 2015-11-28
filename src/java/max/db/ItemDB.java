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
        String sql = "select * from item";

        try {

            if (col.equals("price") || col.equals("quantity") || col.equals("point")) {
                sql = "select * from item where " + col + "<=" + keyword;
                //System.out.print(sql);
            } else {
                sql = "select * from item where " + col + " like '%" + keyword + "%'";
            }

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

    public ItemBean queryById(String itemid) {
        ItemBean b = null;
        try {
            Connection c = super.getConnection();
            String sql = "select * from item where itemid = ?";
            PreparedStatement preStm = c.prepareStatement(sql);
            preStm.setString(1, itemid);
            ResultSet rs = preStm.executeQuery();
            rs.next();
            b = new ItemBean();
            b.setItemId(rs.getString("ItemId"));
            b.setItem_name(rs.getString("Item_name"));
            b.setPrice(rs.getInt("price"));
            b.setCategory(rs.getString("category"));
            b.setDescriptions(rs.getString("descriptions"));
            b.setBrand(rs.getString("brand"));
            b.setQuantity(rs.getInt("quantity"));
            b.setPoint(rs.getInt("point"));
        } catch (Exception e) {

        }
        return b;
    }

    public void updateRecord(ItemBean b) {
        try {
            Connection c = super.getConnection();
            String sql = "update item set "
                    + "ItemId = ?,"
                    + "Item_name = ?,"
                    + "price = ?,"
                    + "category = ?,"
                    + "descriptions = ?,"
                    + "brand =?,"
                    + "quantity =?,"
                    + "point = ? "
                    + "where itemid = ?";

            PreparedStatement preStm = c.prepareStatement(sql);
            preStm.setString(1, b.getItemId());
            preStm.setString(2, b.getItem_name());
            preStm.setDouble(3, b.getPrice());
            preStm.setString(4, b.getCategory());
            preStm.setString(5, b.getDescriptions());
            preStm.setString(6, b.getBrand());
            preStm.setInt(7, b.getQuantity());
            preStm.setInt(8, b.getPoint());
            preStm.setString(9, b.getItemId());
            preStm.execute();
            System.out.println(preStm);
            preStm.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
