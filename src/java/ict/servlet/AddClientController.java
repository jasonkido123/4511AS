package ict.servlet;

import ict.bean.ClientInfo;
import ict.bean.Item;
import ict.db.ClientDb;
import ict.db.ItemDb;
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
    private ItemDb itemdb;
    private ClientInfo bean;

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
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        bean = (ClientInfo) request.getSession().getAttribute("client");
        if (bean.getAdmin().equals("Y")) {
            if ("list".equalsIgnoreCase(action)) {
                // call the query db to get retrieve for all customer
                ArrayList<ClientInfo> clients = db.queryCust();
// set the result into the attribute
                request.setAttribute("client", clients);
// redirect the result to the listCustomers.jsp
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/showClient.jsp");
                rd.forward(request, response);
            } else if ("listItem".equalsIgnoreCase(action)) {
                ArrayList<Item> items = itemdb.queryItem();
                request.setAttribute("item", items);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/showItem.jsp");
                rd.forward(request, response);
            } else if ("delete".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                db.delRecord(id);
                response.sendRedirect("addclient?action=list");
            } else if ("getEditClient".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                if (id != null) {
                    ClientInfo client = db.queryCustByID(id);
                    request.setAttribute("c", client);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/editClient.jsp");
                    rd.forward(request, response);
                }
            } else if ("getClientUpdate".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                if (id != null) {
                    ClientInfo client = db.queryCustByID(id);
                    request.setAttribute("c", client);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/clientUpdate.jsp");
                    rd.forward(request, response);
                }
            } else if ("add".equalsIgnoreCase(action)) {

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
                response.sendRedirect("addclient?action=list");
            } else {
                PrintWriter out = response.getWriter();
                out.println("No such action!!!");
            }
        } else {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/welcomeNormal.jsp");
            rd.forward(request, response);
        }

    }
}
