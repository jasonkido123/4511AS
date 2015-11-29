package ict.test;

import ict.bean.Shopping;
import ict.db.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class testOrderGet {
    public static void main(String[]args) throws IOException, SQLException{
        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        OrderDb cdb = new OrderDb(url,username,"");
        
        String al = (String)cdb.genOrderId();

        System.out.println(al);

    }
}
