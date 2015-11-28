/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author YIKFEI
 */
@WebServlet(urlPatterns = {"/searchItem"})
public class SearchItemController extends HttpServlet {

    private ItemDb db;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDb(dbUrl, dbUser, dbPassword);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {

            String action = request.getParameter("action");
            PrintWriter out = response.getWriter();
            if (action.equals("showAll")) {
                ArrayList items = db.AllItem();
                out.print(items.size());
            } else {
                
                out.print("No Such Action");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
