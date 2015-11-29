/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.ClientInfo;
import ict.bean.Order;
import ict.bean.OrderInfo;
import ict.bean.Shopping;
import ict.bean.ShoppingCart;
import ict.db.ClientDb;
import ict.db.ItemDb;
import ict.db.OrderDb;
import ict.db.OrderInfoDb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CancelOrderController", urlPatterns = {"/CancelOrderController"})
public class CancelOrderController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        bean = (ClientInfo) request.getSession().getAttribute("client");
        String oid = (String) request.getParameter("oid");
        String action = (String) request.getParameter("action");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CancelOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            if (bean == null) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/orderLoginError.jsp");
                rd.forward(request, response);
            } else if (action == null || oid.length() != 5) {
                out.println("invild access</br>");
                out.println("<a href=\"CheckLastTenOrderController\">please try again.</a>");
            } else if (action.equals("cancel") && oid != null) {
                Order o = odb.queryOrderByOId(oid);
                if (o.getStatus().equals("process")) {
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String NowDate = formatter.format(date);
                    String calDate = NowDate.substring(2, 4) + NowDate.substring(5, 7) + NowDate.substring(8, 10) + NowDate.substring(11, 13) + NowDate.substring(14, 16);
                    int Now = Integer.parseInt(calDate);
                    String Odate = o.getOrderTime();
                    String calODate = Odate.substring(2, 4) + Odate.substring(5, 7) + Odate.substring(8, 10) + Odate.substring(11, 13) + Odate.substring(14, 16);
                    int orderDate = Integer.parseInt(calDate);
                    if (orderDate + 2400 > Now) {
                        odb.updateStatus(oid, "cancel");
                        ArrayList<OrderInfo> aloi = oidb.seachOinfo(oid);
                        for (int i = 0; i < aloi.size(); i++) {
                            Shopping s = idb.SearchByIdShopping(aloi.get(i).getItemId());
                            int newQ = s.getQuantity()+aloi.get(i).getQuantity();
                            idb.updateQuantity(s.getItemId(),newQ);
                        }
                        if(o.getPaymentMothed().equals("b")){
                            if(o.getTotalPrice()>50){
                                double backPrice = o.getTotalPrice()-50;
                                bean.setBalance(bean.getBalance()+backPrice);
                                cdb.editRecord(bean);
                            }
                        }
                        out.println("cancel successful.</br><a href=\"welcomeNormal.jsp\">back to index page</a>");
                    } else {
                        out.println(o.getStatus() + " status can't change</br>");
                        out.println("<a href=\"CheckLastTenOrderController\">please try again.</a>");
                    }
                }
                out.println("</body>");
                out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CancelOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
