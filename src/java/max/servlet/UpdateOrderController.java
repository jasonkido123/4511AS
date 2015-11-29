/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import max.bean.OrderBean;
import max.db.ItemDB;
import max.db.OrderDB;

/**
 *
 * @author YIKFEI
 */
@WebServlet(urlPatterns = {"/updateOrder"})

public class UpdateOrderController extends HttpServlet {

    private OrderDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new OrderDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            String action = request.getParameter("action");

            if (action.equals("showAll")) {
                ArrayList<OrderBean> orders = db.queryNonCancelOrders();
                request.setAttribute("orders", orders);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateOrder.jsp");
                rd.forward(request, response);
            } else if (action.equals("update")) {
                String[] orderid = request.getParameterValues("orderid");
                String[] status = request.getParameterValues("status");
                //System.out.print(orderid.length);
                //out.print("Here");
                for (int i = 0; i < status.length; i++) {
                    db.updateOrderStatus(orderid[i], status[i]);
                }
            } else if (action.equals("search")) {
                String col = request.getParameter("col");
                String keyword = request.getParameter("keyword");
                ArrayList<OrderBean> orders = db.queryByString(col, keyword);
                request.setAttribute("orders", orders);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateOrder.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {

        }

    }

}
