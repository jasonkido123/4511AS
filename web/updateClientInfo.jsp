<%-- 
    Document   : updateClientInfo
    Created on : Nov 29, 2015, 7:23:55 PM
    Author     : chanyan
--%>

<%@page import="ict.db.ClientDb"%>
<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Info</title>
    </head>
    <%! private ClientInfo bean;
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost/jsp_ass";
        private ClientDb cdb = new ClientDb(dbUrl, dbUser, dbPassword);
    %>
    <body>
        <%
            bean = (ClientInfo) request.getSession().getAttribute("client");
            if (bean==null) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/loginError.jsp");
                rd.forward(request, response);
            }
        %>
        <form action="update">
            <table border="1">
                <tr>
                    <td>
                        Nmae:<input type="text" value="<%bean.getName();%>"
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
