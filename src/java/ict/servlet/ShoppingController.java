/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.*;
import ict.bean.Shopping;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.*;


/**
 *
 * @author chanyan
 */
@WebServlet(name = "ShoppingController", urlPatterns = {"/shopping"})
public class ShoppingController extends HttpServlet {

    private ItemDb db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDb(dbUrl, dbUser, dbPassword);
    }

    protected void categoryList(String dbUrl, String dbUser, String dbPassword, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDb cdb = new CategoryDb(dbUrl, dbUser, dbPassword);
        ArrayList<String> al = cdb.AllCategory();
        request.setAttribute("categoryType", al);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/shopping.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        
        
    }

    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        //categoryList(dbUrl, dbUser, dbPassword, request, response);
        String action = request.getParameter("action");
        if("search".equals(action)){
            PrintWriter out = response.getWriter();
            ArrayList<Shopping> al =  db.AllItem();
            request.setAttribute("product", al);
            System.out.println("hi");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shopping.jsp");
            rd.forward(request, response);
        }
    }
}
