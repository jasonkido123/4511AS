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

@WebServlet(urlPatterns = {"/handleEdit"})
public class handleEdit extends HttpServlet {

    private ClientDb db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ClientDb(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ClientId = request.getParameter("id");
        String ClientName = request.getParameter("name");
        int tel = Integer.parseInt(request.getParameter("tel"));
        String address = request.getParameter("address");
        String login_ac = request.getParameter("login_ac");
        String login_pw = request.getParameter("login_pw");
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Double balance = Double.parseDouble(request.getParameter("balance"));
        int point = Integer.parseInt(request.getParameter("point"));
        Boolean adminOrNot = Boolean.parseBoolean(request.getParameter("adminOrNot"));
        String action = request.getParameter("action");
        if ("add".equalsIgnoreCase(action)) {
            db.addClientInfo(ClientId, ClientName, tel, address, login_ac, login_pw, status, balance, point, adminOrNot);
            response.sendRedirect("addclient?action=list");
        } else if ("edit".equalsIgnoreCase(action)) {
            ClientInfo cb = new ClientInfo();
            cb.setId(ClientId);
            cb.setName(ClientName);
            cb.setTel(tel);
            cb.setAddress(address);
            cb.setUsername(login_ac);
            cb.setPassword(login_pw);
            cb.setStatus(status);
            cb.setBalance(balance);
            cb.setPoint(point);
            cb.setAdmin(adminOrNot);
            db.editRecord(cb);
            response.sendRedirect("addclient?action=list");
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
