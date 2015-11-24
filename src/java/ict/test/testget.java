/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;


import ict.bean.Shopping;
import ict.db.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author chanyan
 */
public class testget {
    public static void main(String[]args) throws IOException, SQLException{
        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        ItemDb cdb = new ItemDb(url,username,"");
        ArrayList<Shopping> al = cdb.SearchByBrand("1");
        Shopping s = al.get(1);
        System.out.println(s.getItemId());
        System.out.println(s.getPrice());
        System.out.println(s.getPoint());
        System.out.println(s.getQuantity());
        System.out.println(s.getBrand());
        System.out.println(s.getCategory());
        System.out.println(s.getDescriptions());
        System.out.println(s.getItemName());
    }
}
