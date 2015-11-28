/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max;

import max.db.ItemDB;
import max.bean.ItemBean;
import java.util.ArrayList;

/**
 *
 * @author YIKFEI
 */
public class Test {
       
    public static void main(String[] args) {
        ItemDB db = new ItemDB("jdbc:mysql://localhost/jsp_ass","root","");
        ArrayList<ItemBean> al = db.queryByCondition("Item_name", "1");
        
        //System.out.println( al.size());
    }
}
