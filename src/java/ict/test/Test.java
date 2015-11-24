package ict.test;

import ict.db.*;

public class Test {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/jsp_ass";
        String username = "root";
        ClientDb clientdb = new ClientDb(url,username,"");
        clientdb.addClientInfo("U0001", "Tom", 12345678, "ABC", "tom", "tom", false, 200, 100,false);
        ItemDb idb = new ItemDb(url,username,"");
        idb.addItem("I0001","P1",300,"pen", "super pen","brand1",10,5);
        idb.addItem("I0002","P2",200,"pen", "super pen1","brand2",10,5);
        idb.addItem("I0003","P3",400,"pen", "super pen2","brand3",20,5);
        idb.addItem("I0004","R1",100,"rubber", "super rubber2","brand3",0,5);
        idb.addItem("I0005","R2",500,"rubber", "super rubber","brand1",3,5);
        CategoryDb cdb = new CategoryDb(url,username,"");
        cdb.addCategory("C0001", "pen");
        cdb.addCategory("C0002", "rubber");
    }
}