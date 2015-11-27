package ict.test;

import ict.db.*;

public class TestID {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        ClientDb clientdb = new ClientDb(url,username,"");
        String id = clientdb.queryID("tom", "tom");
        System.out.print(id);
    }
}
