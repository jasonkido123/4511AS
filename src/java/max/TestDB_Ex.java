/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max;

import max.db.OrderDB;

/**
 *
 * @author YIKFEI
 */
public class TestDB_Ex {

    public static void main(String[] agrs) {
        OrderDB db = new OrderDB("jdbc:mysql://localhost/jsp_ass", "root", "");
        System.out.print(db.queryNonCancelOrders().size());
    }

}
