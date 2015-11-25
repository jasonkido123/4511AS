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
        String[] search = new String[7];
        search[0] = request.getParameter("min");
        search[1] = request.getParameter("max");
        search[2] = request.getParameter("Pmin");
        search[3] = request.getParameter("Pmax");
        search[4] = request.getParameter("SearchName");
        search[5] = request.getParameter("SearchBrand");
        search[6] = request.getParameter("category");
        if ("search".equals(action)) {
            try {
                ArrayList<Shopping> al;
                al = db.SearchBy(search);
                PrintWriter out = response.getWriter();

                request.setAttribute("product", al);
            } catch (SQLException ex) {
                while (ex != null) {
                    ex.printStackTrace();
                    ex = ex.getNextException();
                }
            }

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shopping.jsp");
            rd.forward(request, response);
        }
    }
}
