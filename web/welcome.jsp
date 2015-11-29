<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
        <title>Welcome</title>

        <style>
            div {
                padding-left: 1cm;
                padding-top: 0.5cm;
            }
        </style>

    </head>
    <body>
        <jsp:useBean id="clientInfo" class="ict.bean.ClientInfo" scope="session" />
        <div>
            <b>Hello, admin <jsp:getProperty name="client" property="username" /></b>
            <p>Welcome to Stationery Online Station</p>
        </div>

        <div>
            <table  class="pure-table pure-table-horizontal">
                <tbody>
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
                    <tr>
                        <td><a href="updateOrder?action=showAll">Update Order</a></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div>
            <form method="post" action="main">
                <input type="hidden" name="action" value="logout">
                <input type="submit" name="Logout" value="logout">
            </form>
        </div>
    </body>
</html>
