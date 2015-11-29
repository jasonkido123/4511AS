<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item</title>
        <link href="max/css/div.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            ClientInfo bean = (ClientInfo) request.getSession().getAttribute("client");
            if (bean.getAdmin().equals("N")) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/welcomeNormal.jsp");
                rd.forward(request, response);
            }
        %>
        <div class="distance">
            <h1>Please enter the following details:</h1>
            <form method="post" action="additem">
                <input type="hidden" name="action" value="additem"/>
                <table class="pure-table pure-table-bordered">
                    <tr>
                        <td><b>Item id:</b></td>
                        <td><input type="text" name="itemid" maxlength="5" size="5"></td>
                    </tr>
                    <tr>
                        <td><b>Item name:</b></td>
                        <td><input type="text" name="itemname" maxlength="25" size="25"></td>
                    </tr>
                    <tr>
                        <td><b>Price($):</b></td>
                        <td><input type="text" name="itemprice" min="0" max="1000" value="10"></td>
                    </tr>
                    <tr>
                        <td><b>Category:</b></td>
                        <td><input type="text" name="category" maxlength="15" size="20"></td>
                    </tr>
                    <tr>
                        <td><b>Descriptions:</b></td>
                        <td><input type="text" name="descriptions" maxlength="95" size="100"></td>
                    </tr>
                    <tr>
                        <td><b>Brands:</b></td>
                        <td><input type="text" name="brand" maxlength="20" size="25"></td>
                    </tr>
                    <tr>
                        <td><b>Quantity:</b></td>
                        <td><input type="number" name="quantity" min="0" max="1000"></td>
                    </tr>                
                    <tr>
                        <td><b>Point:</b></td>
                        <td><input type="number" name="point" min="0" max="10000"></td>
                    </tr>
                </table>
                <br>
                <button type="submit" class="pure-button pure-button-primary">Submit</button>
            </form>
        </div>
        
    </body>
</html>
