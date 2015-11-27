package ict.servlet;

import ict.bean.ClientInfo;
import ict.db.ClientDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "handleClientUpdate", urlPatterns = {"/handleClientUpdate"})
public class handleClientUpdate extends HttpServlet {

    private ClientDb db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ClientDb(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ClientId = request.getParameter("id");
        String ClientName = request.getParameter("name");
        int tel = Integer.parseInt(request.getParameter("tel"));
        String address = request.getParameter("address");
        String login_ac = request.getParameter("login_ac");
        String login_pw = request.getParameter("login_pw");
        String action = request.getParameter("action");
        if("update".equalsIgnoreCase(action)){
            ClientInfo cb = new ClientInfo();
            cb.setId(ClientId);
            cb.setName(ClientName);
            cb.setTel(tel);
            cb.setAddress(address);
            cb.setUsername(login_ac);
            cb.setPassword(login_pw);
            db.updateRecord(cb);
            response.sendRedirect("welcomeNormal.jsp");
        }else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }
}
