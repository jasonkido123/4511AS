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
    <script>
        function isNumberKey(evt) {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
            return true;
        }
    </script>
    <%! private ClientInfo bean;
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost/jsp_ass";
        private ClientDb cdb = new ClientDb(dbUrl, dbUser, dbPassword);
    %>
    <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
    <body>
        <%
            bean = (ClientInfo) request.getSession().getAttribute("client");
            if (bean == null) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/loginError.jsp");
                rd.forward(request, response);
            }
        %>
        <form action="update">
            <table border="1" class="pure-table pure-table-bordered">
                <tr>
                    <td>
                        Nmae:<input type="text" value="<%out.print(bean.getName());%>" name="name" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Tel:<input type="text" value=<%out.print("\""+bean.getTel() +"\" onkeypress=\"return isNumberKey(event)\"") ;%> name="tel"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Delivery Address:<input type="text" value=" <%out.print(bean.getAddress());%>" name="daddress"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Update Pasword:</br>
                        Old Password:<input type="password" value="" name="Opassword"/></br>
                        New Password:<input type="password" value="" name="Npassword"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Update" name="action"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
