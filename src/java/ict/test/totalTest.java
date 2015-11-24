package ict.test;
import ict.db.*;
import java.sql.SQLException;

public class totalTest {
    public static void main(String [] args){
        String url="jdbc:mysql://localhost:3306/jsp_ass";
        String username ="root";
        String password="";
        ClientDb cdb = new ClientDb(url,username,password);
        ItemDb idb = new ItemDb(url,username,password);
        OrdersDb odb = new OrdersDb(url,username,password);
        OrderInfoDb oidb = new OrderInfoDb(url,username,password);
        CategoryDb cadb = new CategoryDb (url,username,password);
        try{
            cdb.createClientDb();
            idb.createItemDb();
            odb.createOrderDb();
            oidb.createOrderDb();
            cadb.createCategoryDb();
            System.out.println("ok");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
