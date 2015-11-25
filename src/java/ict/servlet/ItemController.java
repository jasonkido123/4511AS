package ict.servlet;

import ict.db.ItemDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ItemController", urlPatterns = {"/additem"})
public class ItemController extends HttpServlet {

    private ItemDb db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDb(dbUrl, dbUser, dbPassword);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("additem".equals(action)) {
            try {
                doAddItem(request, response);
            } catch (Exception ex) {
                PrintWriter out = new PrintWriter(System.out);
                out.println("<h1><font color='red'>Please input correct data!</font></h1>");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void doAddItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String targetURL = null;

        String ItemId = request.getParameter("itemid");
        String Item_name = request.getParameter("itemname");
        double price = Double.parseDouble(request.getParameter("itemprice"));
        String category = request.getParameter("category");
        String descriptions = request.getParameter("descriptions");
        String brand = request.getParameter("brand");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int point = Integer.parseInt(request.getParameter("point"));

        try {
            db.addItem(ItemId, Item_name, price, category, descriptions, brand, quantity, point);

        } catch (Exception ex) {
            PrintWriter out = new PrintWriter(System.out);
            out.println("<h1><font color='red'>Please input correct data!</font></h1>");
        }
        targetURL = "/addSuccess.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
