package ict.test;

import ict.bean.ClientInfo;
import ict.db.ClientDb;

public class TestUpdate {

    public static void main(String[] arg) {

        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        ClientDb clientdb = new ClientDb(url, username, "");
        ClientInfo cb = new ClientInfo();
        cb.setId("U0001");
        cb.setName("tom");
        cb.setTel(324624);
        cb.setAddress("ebtnetn");
        cb.setUsername("tom");
        cb.setPassword("tom");
        clientdb.updateRecord(cb);
    }
}
