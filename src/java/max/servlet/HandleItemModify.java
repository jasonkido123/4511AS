/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import max.bean.ItemBean;
import max.db.ItemDB;

/**
 *
 * @author YIKFEI
 */
@WebServlet(urlPatterns = {"/modifyItem"})
public class HandleItemModify extends HttpServlet {

    private ItemDB db;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String itemid = request.getParameter("itemid");
        String action = request.getParameter("action");
        try {

            if (action.equals("show")) {
                ItemBean item = db.queryById(itemid);
                request.setAttribute("item", item);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/modifyItem.jsp");
                rd.forward(request, response);
            } else if (action.equals("update")) {
                ItemBean b = new ItemBean();
                b.setItemId(request.getParameter("itemid"));
                b.setItem_name(request.getParameter("item_name"));
                b.setPrice(Double.parseDouble(request.getParameter("price")));
                b.setCategory(request.getParameter("category"));
                b.setDescriptions(request.getParameter("descriptions"));
                b.setBrand(request.getParameter("brand"));
                b.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                b.setPoint(Integer.parseInt(request.getParameter("point")));
                //System.out.print(b);
                db.updateRecord(b);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchItem?action=showAll");
                rd.forward(request, response);

            } else {
                PrintWriter out = response.getWriter();
                out.print("No Such Action");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDB(dbUrl, dbUser, dbPassword);
    }

}
