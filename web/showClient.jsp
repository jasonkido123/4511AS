
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Client</title>
    </head>
    <body>
        <%
            ArrayList<ClientInfo> client
                    = (ArrayList<ClientInfo>) request.getAttribute("client");
            out.println("<h1>Clients</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>ClientId</th> <th>Name</th><th>Tel</th><th>Address</th ><th>Login name</th ><th>Login password</th ><th>Status</th ><th>Balance</th ><th>Point</th ><th>Is admin?</th >");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < client.size(); i++) {
                ClientInfo c = client.get(i);
                out.println("<tr>");
                out.println("<td>" + c.getId()+ "</td>");
                out.println("<td>" + c.getName() + "</td>");
                out.println("<td>" + c.getTel() + "</td>");
                out.println("<td>" + c.getAddress()+ "</td>");
                out.println("<td>" + c.getUsername()+ "</td>");
                out.println("<td>" + c.getPassword()+ "</td>");
                out.println("<td>" + c.isStatus()+ "</td>");
                out.println("<td>" + c.getBalance()+ "</td>");
                out.println("<td>" + c.getPoint()+ "</td>");
                out.println("<td>" + c.isAdmin()+ "</td>");
                out.println("<td><a href=\"addclient?action=delete&id=" + c.getId()+ "\">delete</td>");
                out.println("<td><a href=\"addclient?action=getEditClient&id=" + c.getId() + "\">edit</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
    </body>
</html>