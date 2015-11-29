/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.servlet;

import ict.bean.ClientInfo;
import max.db.*;
import max.bean.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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

    private ItemDB db;
    private ClientInfo bean;
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
        db = new ItemDB(dbUrl, dbUser, dbPassword);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String action = request.getParameter("action");
            bean = (ClientInfo) request.getSession().getAttribute("client");
            if (bean.getAdmin().equals("Y")) {
                if (action.equals("showAll")) {
                    ArrayList<ItemBean> items = db.queryAll();
                    request.setAttribute("items", items);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchItem.jsp");
                    rd.forward(request, response);
                } else if (action.equals("condition")) {
                    String col = request.getParameter("col");
                    String keyword = request.getParameter("keyword");
                    //System.out.print(col+" "+ keyword);
                    ArrayList<ItemBean> items = db.queryByCondition(col, keyword);
                    request.setAttribute("items", items);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchItem.jsp");
                    rd.forward(request, response);
                } else {
                    PrintWriter out = response.getWriter();
                    out.print("No Such Action");
                }
            }else {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/welcomeNormal.jsp");
            rd.forward(request, response);
        }
            }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }

    }
