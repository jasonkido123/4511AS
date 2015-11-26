/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Shopping;
import ict.bean.ShoppingCart;
import ict.db.ItemDb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chanyan
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/ShoppingCart"})
public class ShoppingCartController extends HttpServlet {

    private ItemDb db;
    private ArrayList<ShoppingCart> al;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ItemDb(dbUrl, dbUser, dbPassword);
        al = new ArrayList();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String pid = request.getParameter("pid");
        String action = request.getParameter("action");
        //al = request.getParameter(ItemList);
        //ShoppingCart sc = new ShoppingCart();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (action.equals("add")) {
                ActionAdd(pid);
            }
            if (action.equals("del")) {
                actionDel(pid);
            }
            request.setAttribute("ItemList", al);
            request.setAttribute("id", pid);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ShoppingCart.jsp");
            rd.forward(request, response);
        } catch (IOException e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
    }

    public void ActionAdd(String pid) throws ServletException, IOException {
        try {
            ArrayList als = db.SearchById(pid);
            Shopping s = (Shopping) als.get(0);
            if (al.size() > 0) {
                ShoppingCart sc = new ShoppingCart();
                boolean isExitst = false;
                for (int i = 0; i < al.size(); i++) {
                    sc = al.get(i);
                    if (sc.getItemId().equals(pid)) {
                        isExitst = true;
                    }
                }
                //System.out.println(sc.getItemId() + "   " + pid);
                if (isExitst) {
                    sc.setQuantity(sc.getQuantity() + 1);
                } else {
                    sc = new ShoppingCart();
                    sc.setQuantity(1);
                    sc.setItemId(pid);
                    sc.setPoint(s.getPoint());
                    sc.setPrice(s.getPrice());
                    sc.setName(s.getItemName());
                    al.add(sc);
                }
            } else {
                ShoppingCart sc = new ShoppingCart();
                sc.setItemId(pid);
                sc.setPoint(s.getPoint());
                sc.setPrice(s.getPrice());
                sc.setName(s.getItemName());
                int q = sc.getQuantity();
                sc.setQuantity(1);
                al.add(sc);
            }
        } catch (IOException e) {

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
    }

    public void actionDel(String pid) {
        if (al.size() > 0) {
            ShoppingCart sc = new ShoppingCart();
            for (int i = 0; i < al.size(); i++) {
                sc = al.get(i);
                if (sc.getItemId().equals(pid)) {
                    al.remove(i);
                    break;
                }
            }
        }
    }
}
