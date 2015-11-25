package ict.servlet;

import ict.bean.ClientInfo;
import ict.db.ClientDb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddClientController", urlPatterns = {"/addclient"})
public class AddClientController extends HttpServlet {

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

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
//        String action = request.getParameter("action");
//
//        if ("addclient".equals(action)) {
//            doAdd(request, response);
//        } else {
//            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
//        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String test = request.getParameter("test");
        if ("update".equalsIgnoreCase(test)) {
            String id = request.getParameter("id");

            String ClientId = request.getParameter("clientid");
            String ClientName = request.getParameter("clientname");
            int tel = Integer.parseInt(request.getParameter("tel"));
            String address = request.getParameter("address");
            String login_ac = request.getParameter("login_ac");
            String login_pw = request.getParameter("login_pw");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            Double balance = Double.parseDouble(request.getParameter("balance"));
            int point = Integer.parseInt(request.getParameter("point"));
            Boolean adminOrNot = Boolean.parseBoolean(request.getParameter("adminOrNot"));

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
            
        } 
        if ("list".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer
            ArrayList<ClientInfo> clients = db.queryCust();
// set the result into the attribute
            request.setAttribute("client", clients);
// redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/showClient.jsp");
            rd.forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            db.delRecord(id);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/showClient.jsp");
            rd.forward(request, response);
        } else if ("getEditClient".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            if (id != null) {
                ClientInfo client = db.queryCustByID(id);
                request.setAttribute("c", client);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editClient.jsp");
                rd.forward(request, response);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
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
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Double balance = Double.parseDouble(request.getParameter("balance"));
        int point = Integer.parseInt(request.getParameter("point"));
        Boolean adminOrNot = Boolean.parseBoolean(request.getParameter("adminOrNot"));

        db.addClientInfo(ClientId, ClientName, tel, address, login_ac, login_pw, status, balance, point, adminOrNot);
        targetURL = "/addSuccess.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
