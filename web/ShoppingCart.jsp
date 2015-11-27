<%-- 
    Document   : ShoppingCart
    Created on : Nov 25, 2015, 2:42:00 PM
    Author     : chanyan
--%>

<%@page import="ict.bean.ShoppingCart"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    function isNumberKey(e) {
        e = e || window.event;
        var charCode = e.which ? e.which : e.keyCode;
        return /\d/.test(String.fromCharCode(charCode));
    }
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  String pid = (String) request.getAttribute("id");
            ArrayList<ShoppingCart> itemAL = (ArrayList<ShoppingCart>) request.getAttribute("ItemList");%>
        <form action="ShoppingCart">
            <table border="1">
                <tr><td>Item_Name</td><td>Item_Point</td><td>Item_Price</td><td>Quantity</td><td>Del</td></tr>
            <%
                try {
                    String s = "";
                    for (int i = 0; i < itemAL.size(); i++) {
                        ShoppingCart sc = itemAL.get(i);
                        s += "<tr>";
                        s += "<td>" + sc.getName() + "</td>";
                        s += "<td>" + sc.getPoint() + "</td>";
                        s += "<td>" + sc.getPrice() + "</td>";
                        s += "<td><input type=\"Textbox\" name=\"quantity"+i+"\" value=\""+sc.getQuantity()+"\" onkeypress=\"return isNumberKey(event)\" ></td>";
                        s += "<td><a href=\"ShoppingCart?action=del&pid=" + sc.getItemId() + "\"><input type=\"button\" value=\"Del\"/ ></a></td>";
                        s += "</tr>";
                    }
                    s += "</table>";
                    out.print(s);
                    out.print("<input type=\"submit\" name=\"action\" value=\"update\"/>");
                } catch (NullPointerException e) {
                    out.print("invild access.");
                }
            %>
        </form>
    </body>
</html>
