/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.ClientInfo;
import ict.bean.Shopping;
import ict.bean.ShoppingCart;
import ict.db.ClientDb;
import ict.db.ItemDb;
import ict.db.OrderDb;
import ict.db.OrderInfoDb;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author chanyan
 */
@WebServlet(name = "CheckoutOrderController", urlPatterns = {"/CheckoutOrderController"})
public class CheckoutOrderController extends HttpServlet {

    private ItemDb idb;
    private ClientDb cdb;
    private OrderDb odb;
    private OrderInfoDb oidb;
    private ClientInfo bean;
    private ArrayList<ShoppingCart> al;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        cdb = new ClientDb(dbUrl, dbUser, dbPassword);
        odb = new OrderDb(dbUrl, dbUser, dbPassword);
        oidb = new OrderInfoDb(dbUrl, dbUser, dbPassword);
        idb = new ItemDb(dbUrl, dbUser, dbPassword);
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        bean = (ClientInfo) request.getSession().getAttribute("client");
        al = (ArrayList<ShoppingCart>) request.getSession().getAttribute("shoppingCart");
        try (PrintWriter out = response.getWriter()) {
            String orderid = odb.genOrderId();
            if (bean != null && al != null) {
                String payment = request.getParameter("payment");
                if (payment.equals("cash")) {
                    double balance = bean.getBalance();
                    double totalPrice = getTotalPrice();
                    if (checkQuantity()) {
                        if (balance >= totalPrice) {
                            bean.setBalance(balance - totalPrice);
                            cdb.editRecord(bean);
                            odb.addOrder(orderid, bean.getId(), totalPrice, 0, "b", "process");
                            addinfo(orderid);
                            updateQuantity();
                            out.print("ko");
                            ArrayList temp = null;
                            al = temp;
                            request.getSession().setAttribute("shoppingCart", al);
                        } else {
                            out.print("balance isn't enough.</br><a href=\"OrderController\">Please try again.</a>");
                        }
                    } else {
                        out.print("stock isn't enough.</br>You can use other of payment method or recharge.</br><a href=\"OrderController\">Please try again.</a>");
                    }
                } else if (payment.equals("point")) {
                    int point = bean.getPoint();
                    int totalPoint = getTotalPoint();
                    if (checkQuantity()) {
                        if (point >= totalPoint) {
                            bean.setPoint(point - totalPoint);
                            cdb.editRecord(bean);
                            odb.addOrder(orderid, bean.getId(), 0, totalPoint, "p", "process");
                            addinfo(orderid);
                            updateQuantity();
                            out.print("ko");
                            ArrayList temp = null;
                            al = temp;
                            request.getSession().setAttribute("shoppingCart", al);
                        } else {
                            out.print("Point of balance isn't enough.</br><a href=\"OrderController\">Please try again.</a>");
                        }
                    } else {
                        out.print("stock isn't enough.</br>You can use other of payment method or recharge.</br><a href=\"OrderController\">Please try again.</a>");
                    }
                } else {
                    out.print("invaild payment method</br><a href=\"OrderController\">Please try again.</a>");
                }
            } else if (bean == null) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/orderLoginError.jsp");
                rd.forward(request, response);
            } else if (al == null) {
                out.print("your order haven't product in cart.");
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < al.size(); i++) {
            totalPrice += al.get(i).getPrice();
        }
        return totalPrice;
    }

    public int getTotalPoint() {
        int totalpoint = 0;
        for (int i = 0; i < al.size(); i++) {
            totalpoint += al.get(i).getPoint();
        }
        return totalpoint;
    }

    public void addinfo(String orderid) {
        for (int i = 0; i < al.size(); i++) {
            oidb.addOrderinfo(orderid, al.get(i).getItemId(), al.get(i).getPrice(), al.get(i).getPoint(), al.get(i).getQuantity());
        }
    }

    public boolean checkQuantity() throws IOException, SQLException {
        boolean check = true;
        for (int i = 0; i < al.size(); i++) {
            Shopping s = idb.SearchByIdShopping(al.get(i).getItemId());
            System.out.println(s.getQuantity() + "         " + al.get(i).getQuantity() + "         " + (s.getQuantity() >= al.get(i).getQuantity()));
            if (s.getQuantity() < al.get(i).getQuantity()) {
                check = false;
                break;
            }
        }
        return check;
    }

    public void updateQuantity() throws IOException, SQLException {
        for (int i = 0; i < al.size(); i++) {
            Shopping s = idb.SearchByIdShopping(al.get(i).getItemId());
            int newQuantity = s.getQuantity() - al.get(i).getQuantity();
            idb.updateQuantity(s.getItemId(), newQuantity);
        }
    }
}
