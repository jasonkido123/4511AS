<%-- 
    Document   : ShoppingCart
    Created on : Nov 25, 2015, 2:42:00 PM
    Author     : chanyan
--%>

<%@page import="ict.bean.ShoppingCart"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try{
                String pid = (String) request.getAttribute("id");
                //out.print(pid);
                ArrayList<ShoppingCart> itemAL = (ArrayList<ShoppingCart>) request.getAttribute("ItemList");
                //out.print(itemAL.size());
                String s = "<form><table border=\"1\">";
                s += "<tr><td>Item_Name</td><td>Item_Point</td><td>Item_Price</td><td>Quantity</td><td>Plus</td><td>Minus</td><td>Del</td>";
                for (int i = 0; i < itemAL.size(); i++) {
                    ShoppingCart sc = itemAL.get(i);
                    s += "<tr>";
                    s += "<td>" + sc.getName() + "</td>";
                    s += "<td>" + sc.getPoint() + "</td>";
                    s += "<td>" + sc.getPrice() + "</td>";
                    s += "<td>" + sc.getQuantity() + "</td>";
                    s += "<td><a href=\"ShoppingCart?action=plus&pid="+sc.getItemId()+"\">+</a></td>";
                    s += "<td><input type=\"text\" name\"q"+i+"\"></td>";
                    s += "<td><a href=\"ShoppingCart?action=Minus&pid="+sc.getItemId()+"\">-</a></td>";
                    s += "<td><a href=\"ShoppingCart?action=del&pid="+sc.getItemId()+"\">del</a></td>";
                    s += "</tr>";
                }
                s += "<table></form>";
                out.print(s);
            }catch(NullPointerException e){
                out.print("invild access.");
            }
        %>
    </body>
</html>
