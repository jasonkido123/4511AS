<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <jsp:useBean id="clientInfo" class="ict.bean.ClientInfo" scope="session" />
        <b>Hello, admin <jsp:getProperty name="client" property="username" /></b>
        <p>Welcome to Stationery Online Station</p>
        <table border='1'>
            <tr>
                <td><a href="addClient.jsp">Add Client</a></td>
            </tr>
            <tr>
                <td><a href="addclient?action=list">Show Client</a></td>
            </tr>
            <tr>
                <td><a href="addItem.jsp">Add Item</a></td>
            </tr>
            <tr>
                <td><a href="searchItem?action=showAll">Search Item</a></td>
            </tr>
        </table>
        <form method="post" action="main">
            <input type="hidden" name="action" value="logout">
            <input type="submit" name="Logout" value="logout">
        </form>
        <hr/>
    </body>
</html>
