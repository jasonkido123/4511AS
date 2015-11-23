package ict.test;

import ict.db.UserDB;

public class Test {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/itp4912_db";
        String username = "root";
        UserDB userDb = new UserDB(url,username,"");
        userDb.CreateClientTable();
        userDb.addUserInfo("002", "Jason", 12345677,"aaa","jason","jason",1,100,200);
    }
}