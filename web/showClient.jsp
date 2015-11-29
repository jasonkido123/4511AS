
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Client</title>
        <link href="max/css/div.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="distance">
            <%
                ArrayList<ClientInfo> client
                        = (ArrayList<ClientInfo>) request.getAttribute("client");
                out.println("<h1>Here is the clients details!</h1>");
                out.println("<table class=\"pure-table pure-table-horizontal\" >");
                out.println("<thead>");
                out.println("<th>ClientId</th> <th>Name</th><th>Tel</th><th>Address</th ><th>Login name</th ><th>Login password</th ><th>Status</th ><th>Balance</th ><th>Point</th ><th>Is admin?</th ><th>Edit client</th><th>Delete client</th>");
                out.println("</thead>");
                // loop through the customer array to display each customer record
                for (int i = 0; i < client.size(); i++) {
                    ClientInfo c = client.get(i);
                    if (i % 2 == 1) {
                        out.print("<tr class=\"pure-table-odd\">");
                    } else {
                        out.println("<tr>");
                    }
                    out.println("<td>" + c.getId() + "</td>");
                    out.println("<td>" + c.getName() + "</td>");
                    out.println("<td>" + c.getTel() + "</td>");
                    out.println("<td>" + c.getAddress() + "</td>");
                    out.println("<td>" + c.getUsername() + "</td>");
                    out.println("<td>" + c.getPassword() + "</td>");
                    out.println("<td>" + c.getStatus() + "</td>");
                    out.println("<td>" + c.getBalance() + "</td>");
                    out.println("<td>" + c.getPoint() + "</td>");
                    out.println("<td>" + c.getAdmin() + "</td>");
                    out.println("<td><a href=\"addclient?action=getEditClient&id=" + c.getId() + "\">edit</td>");
                    out.println("<td><a href=\"addclient?action=delete&id=" + c.getId() + "\">delete</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            %>
        </div>
    </body>
</html>
