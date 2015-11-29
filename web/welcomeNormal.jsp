<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
        <link href="max/css/div.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <jsp:useBean id="clientInfo" class="ict.bean.ClientInfo" scope="session" />
        <%
            ClientInfo c = (ClientInfo) request.getSession().getAttribute("client");
            String id = c.getId();
        %>
        <div class="distance">
            <b>Hello, client <jsp:getProperty name="client" property="username" /></b>
            <p>Welcome to Stationery Online Station</p>
        
        
            <table class="pure-table">
                <tr>
                    <td><a href="addclient?action=getClientUpdate&id=<%=id%>">Update personal infomation</a></td>
                    <td><a href="CheckLastTenOrderController">last 10 Order</a></td>
                    <td><a href="shopping.jsp">Shopping</a></td>
                    <td><a href="ShoppingCart?action=">Shopping Cart</a></td>
                </tr>
            </table>
        </div>
        <div class="distance" >
            <form method="post" action="main">
                <input type="hidden" name="action" value="logout">                
                <button type="submit" name="Logout" value="logout" class="button-secondary pure-button">Log out</button>
            </form>
        </div>
    </body>
</html>
