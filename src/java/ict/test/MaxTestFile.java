/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.*;

/**
 *
 * @author YIKFEI
 */
public class MaxTestFile {

    public static void main(String[] agrs) {
        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        ClientDb db = new ClientDb(url,username,"");
        
        db.updatePoint("1", "U0001");
        
        
    }

}
