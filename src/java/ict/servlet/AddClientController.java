package ict.servlet;

import ict.db.ClientDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddClientController", urlPatterns = {"/addclient"})
public class AddClientController extends HttpServlet {
    private ClientDb db;
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ClientDb(dbUrl, dbUser, dbPassword);
    }
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addclient".equals(action)) {
            doAdd(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }
    
    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetURL;

        String ClientId = request.getParameter("clientid");
        String ClientName = request.getParameter("clientname");
        int tel = Integer.parseInt(request.getParameter("tel"));
        String address = request.getParameter("address");
        String login_ac = request.getParameter("login_ac");
        String login_pw = request.getParameter("login_pw");
        String status = request.getParameter("status");
        Double balance = Double.parseDouble(request.getParameter("balance"));
        int point = Integer.parseInt(request.getParameter("point"));
        String adminOrNot = request.getParameter("adminOrNot");
        
        db.addClientInfo(ClientId, ClientName, tel, address, login_ac, login_pw, status, balance, point, adminOrNot);
        targetURL = "/addSuccess.jsp";
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
