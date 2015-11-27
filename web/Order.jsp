<%-- 
    Document   : Order
    Created on : Nov 27, 2015, 4:19:38 PM
    Author     : chanyan
--%>

<%@page import="ict.bean.ShoppingCart"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Order</title>
    </head>
    <body>
        <form >
            <table border="1">
                <tr><td>Item_Name</td><td>Item_Point</td><td>Item_Price</td><td>Quantity</td></tr>
        <%

            ArrayList<ShoppingCart> itemAL = (ArrayList<ShoppingCart>) request.getSession().getAttribute("shoppingCart");
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
                    s += "<td>" + sc.getQuantity() + "</td>";
                    s += "</tr>";
                    totalPrice += sc.getPrice() * sc.getQuantity();
                    totalPoint += sc.getPoint() * sc.getQuantity();
                }
                s += "<tr><td align=\"right\" colspan=\"3\">Total Price:</td>"
                        + "<td align=\"right\" colspan=\"2\">" + totalPrice + "</td></tr>";
                s += "<tr><td align=\"right\" colspan=\"3\">Total Point:</td>"
                        + "<td align=\"right\" colspan=\"2\">" + totalPoint + "</td></tr>";
                s += "<tr><td align=\"right\" colspan=\"3\">Payment Method</td>"
                        + "<td align=\"right\" colspan=\"2\">"
                        + "<input type=\"radio\" value=\"point\" name=\"payment\">Point"
                        + "<input type=\"radio\" value=\"cash\" name=\"payment\">Cash"
                        + "</td></tr>";
                s += "<tr><td align=\"left\" colspan=\"3\"><a href=\"ShoppingCart?action=\"><input type=\"button\" value=\"Edit Product\"/ ></a></td>"
                        + "<td align=\"right\" colspan=\"1\"><a href=\"OrderController\"><input type=\"button\" name=\"action\" value=\"Confrim\" /></a></td></tr>";
                s += "</table>";
                out.print(s);
            } catch (NullPointerException e) {
                out.print("invild access.");
            }


        %>
        </form>
    </body>
</html>
