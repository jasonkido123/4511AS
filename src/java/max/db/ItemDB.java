/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.db;

import max.bean.ItemBean;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author YIKFEI
 */
public class ItemDB extends Database {

    public ItemDB(String url, String username, String password) {
        super(url, username, password);
    }

    public ArrayList<ItemBean> queryAll() {
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();

        try {
            Connection c = super.getConnection();
            String Sql = "select * from item";
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(Sql);

            while (rs.next()) {
                ItemBean b = new ItemBean();
                b.setItemId(rs.getString("ItemId"));
                b.setItemId(rs.getString("Item_name"));
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
