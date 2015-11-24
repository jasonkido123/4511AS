/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.*;
import ict.bean.Shopping;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chanyan
 */
@WebServlet(name = "ShoppingController", urlPatterns = {"/shopping"})
public class ShoppingController extends HttpServlet{
    private ItemDb db;
    public void init (){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDb(dbUrl, dbUser, dbPassword);
    }
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        ArrayList<Shopping> al = new ArrayList();
        try{
            ResultSet rs = db.AllItem();
            while(rs.next()){
                 Shopping sp = new Shopping();
                 sp.setItemId(rs.getString("ItemId"));
                 sp.setCategory(rs.getNString("category"));
                 sp.setItemName(rs.getNString("Item_name"));
                 sp.setPrice(rs.getDouble("price"));
                 sp.setDescriptions(rs.getNString("descriptions"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
    }
}
