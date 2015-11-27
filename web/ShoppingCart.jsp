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
    function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  String pid = (String) request.getAttribute("id");
            ArrayList<ShoppingCart> itemAL = (ArrayList<ShoppingCart>) request.getSession().getAttribute("shoppingCart");%>
        <form action="ShoppingCart">
            <table border="1">
                <tr><td>Item_Name</td><td>Item_Point</td><td>Item_Price</td><td>Quantity</td><td>Del</td></tr>
                <%
                    try {
                        double totalPrice = 0;
                        int totalPoint = 0;
                        String s = "";
                        for (int i = 0; i < itemAL.size(); i++) {
                            ShoppingCart sc = itemAL.get(i);
                            s += "<tr>";
                            s += "<td>" + sc.getName() + "</td>";
                            s += "<td>" + sc.getPoint() + "</td>";
                            s += "<td>" + sc.getPrice() + "</td>";
                            s += "<td><input type=\"Textbox\" name=\"quantity" + i + "\" value=\"" + sc.getQuantity() + "\" onkeypress=\"return isNumberKey(event)\" ></td>";
                            s += "<td><a href=\"ShoppingCart?action=del&pid=" + sc.getItemId() + "\"><input type=\"button\" value=\"Del\"/ ></a></td>";
                            s += "</tr>";
                            totalPrice += sc.getPrice() * sc.getQuantity();
                            totalPoint += sc.getPoint() * sc.getQuantity();
                        }
                        s += "<tr><td align=\"right\" colspan=\"3\">Total Price:</td>"
                                + "<td align=\"right\" colspan=\"2\">" + totalPrice + "</td></tr>";
                        s += "<tr><td align=\"right\" colspan=\"3\">Total Point:</td>"
                                + "<td align=\"right\" colspan=\"2\">" + totalPoint + "</td></tr>";
         
                        s += "<tr><td align=\"left\" colspan=\"3\"><a href=\"shopping.jsp\"><input type=\"button\" value=\"Continue Shopping\"/ ></a></td>"
                                + "<td align=\"right\" colspan=\"1\"><input type=\"submit\" name=\"action\" value=\"update\"/></td>"
                                + "<td align=\"right\" colspan=\"1\"><a href=\"OrderController\"><input type=\"button\" name=\"action\" value=\"CheckOut\" /></a></td></tr>";
                        s += "</table>";
                        out.print(s);
                    } catch (NullPointerException e) {
                        out.print("invild access.");
                    }
                %>
                
        </form>
    </body>
</html>
