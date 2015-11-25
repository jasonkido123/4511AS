<%-- 
    Document   : ShoppingCart
    Created on : Nov 25, 2015, 2:42:00 PM
    Author     : chanyan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String id = request.getParameter("pid");
        out.print(id);%>
    </body>
</html>
