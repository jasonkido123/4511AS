package ict.test;

import ict.bean.ClientInfo;
import ict.db.ClientDb;

public class TestEdit {

    public static void main(String[] arg) {

        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        ClientDb clientdb = new ClientDb(url, username, "");
        ClientInfo cb = new ClientInfo();
        cb.setId("U0001");
        cb.setName("Tom");
        cb.setTel(132);
        cb.setAddress("ABC");
        cb.setUsername("tom");
        cb.setPassword("tom");
        cb.setStatus(true);
        cb.setBalance(100.0);
        cb.setPoint(100);
        cb.setAdmin(true);
        clientdb.editRecord(cb);
    }
}
