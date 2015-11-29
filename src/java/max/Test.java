/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max;

import java.util.*;
import max.db.*;

/**
 *
 * @author YIKFEI
 */
public class Test {

    public static void main(String[] args) {
        OrderDB db = new OrderDB("jdbc:mysql://localhost/jsp_ass", "root", "");

        for (int i = 1; i <= 100; i++) {
            //db.simpleInsert(genOrderID, i);
            db.simpleUpdate(i, "clientid", genClientID());
            db.simpleUpdate(i, "PaymentMothed", genPayMethod());
            db.simpleUpdate(i, "status", genStatus());
        }
    }

    public static String genOrderID(int i) {
        String id = "O";
        String formatStr = "%04d";
        id += String.format(formatStr, i);

        return id;
    }

    public static String genClientID() {
        String id = "O";
        String formatStr = "%04d";
        Random r = new Random();
        int i = r.nextInt(10) + 1;

        id += String.format(formatStr, i);

        return id;
    }

    public static String genPayMethod() {
        String pm = "B";

        Random r = new Random();

        if (r.nextInt() % 2 == 0) {
            pm = "P";
        }

        return pm;
    }

    public static String genStatus() {
        String[] status = {"process", "cancel", "delivered", "packed-up"};
        Random r = new Random();
        return status[r.nextInt(4)];
    }

}
