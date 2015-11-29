/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.ClientInfo;
import ict.db.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "updateClientInfo", urlPatterns = {"/updateClientInfo"})
public class updateClientInfo extends HttpServlet {

    private ClientDb db;
    private ClientInfo bean;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ClientDb(dbUrl, dbUser, dbPassword);
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
        String action = request.getParameter("action");
        bean = (ClientInfo) request.getSession().getAttribute("client");
        try (PrintWriter out = response.getWriter()) {

            if (bean != null) {
                if (action.equals("update")) {
                    String name = request.getParameter("name");
                    String tel = request.getParameter("tel");
                    String daddress = request.getParameter("daddress");
                    String Opassword = request.getParameter("Opassword");
                    String Npassword = request.getParameter("Npassword");
                    if (name.length()>0) {
                        bean.setName(name);
                        out.print("name edit successful.</br>");
                    }
                    if (tel.length()>0) {
                        if(tel.length()!=8){
                            out.print("length of tel edition isn't successful.</br><a href=\"updateClientInfo.jsp\">if need edit,please try again.</a></br>");
                        }else{
                            int tel1 = Integer.parseInt(tel);
                            bean.setTel(tel1);
                            out.print("Tel edit successful.</br>");
                        }
                    }
                    if(daddress.length()>0){
                        bean.setAddress(daddress);
                        out.print("Delivery address edit successful.</br>");
                    }
                    if(Opassword.length()>0){
                        if(Opassword.equals(bean.getPassword())){
                            if(Npassword.length()>=6){
                            bean.setPassword(Npassword);
                            out.print("Password edit successful.</br>");
                            }else{
                                out.print("Password edit isn't successful.Becasue new password of length isn't to over 6digit</br>");
                            }
                            
                        }else{
                            out.print("Password edition isn't successful.Because old password is incorrect.</br><a href=\"updateClientInfo.jsp\">if need edit,please try again.</a>");
                        }
                    }
                    db.editRecord(bean);
                }
            } else {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/orderLoginError.jsp");
                rd.forward(request, response);
            }
            /* TODO output your page here. You may use following sample code. */

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
        //processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
