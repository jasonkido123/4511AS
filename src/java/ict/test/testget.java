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
        String[] search = new String[7];
        search[0] = "";//request.getParameter("min");
        search[1] = "";//request.getParameter("max");
        search[2] = "3";//request.getParameter("SearchName");
        search[3] = "6";//request.getParameter("SearchBrand");
        search[4] = "";//request.getParameter("category");
        search[5] = "";//request.getParameter("category");
        search[6] = "";//request.getParameter("category");
        ArrayList<Shopping> al = cdb.SearchById("i0001");
        Shopping s = al.get(0);
        System.out.println(al.size());
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
