/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db.max;

import ict.db.ItemDb;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YIKFEI
 */
public class ItemDB extends Database {

    public ItemDB(String url, String username, String password) {
        super(url, username, password);
    }
    
    

}
