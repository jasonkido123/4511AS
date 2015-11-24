/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;


import ict.db.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author chanyan
 */
public class testget {
    public static void main(String[]args){
        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        CategoryDb cdb = new CategoryDb(url,username,"");
        ArrayList<String> al = cdb.AllCategory();
            System.out.println(al.get(0));
    }
}
