package ict.servlet;

import ict.bean.Item;
import ict.db.ItemDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ItemController", urlPatterns = {"/additem"})
public class AddItemController extends HttpServlet {
    private ItemDb db;
    
    public void init (){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDb(dbUrl, dbUser, dbPassword);
    }
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String targetURL = null;
        
        String ItemId = request.getParameter("ItemId");
        String Item_name = request.getParameter("Item_name");
        Double price = Double.parseDouble(request.getParameter("itemprice"));
        String category = request.getParameter("category");
        String descriptions = request.getParameter("descriptions");
        if ("additem".equals(action)) {
            db.addItem(ItemId, Item_name, price, category, descriptions);
            targetURL = "/addSuccess.jsp";
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/"+targetURL);
        rd.forward(request, response);
    }
}
