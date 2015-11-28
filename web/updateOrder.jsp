<%-- 
    Document   : updateOrder
    Created on : Nov 29, 2015, 12:47:00 AM
    Author     : YIKFEI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.ArrayList,max.bean.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order</title>
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <%
            ArrayList<OrderBean> orders = (ArrayList<OrderBean>) request.getAttribute("orders");

            out.print("<table class=\"pure-table pure-table-bordered\">");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>OrderID</th>");
            out.print("<th>ClientID</th>");
            out.print("<th>Total Price</th>");
            out.print("<th>Total Point</th>");
            out.print("<th>Payment Method</th>");
            out.print("<th>Status</th>");
            out.print("<th>Order Time</th>");
            out.print("</tr>");
            out.print("</thead>");

            out.print("<tbody>");
            for (int i = 0; i < orders.size(); i++) {
                OrderBean b = orders.get(i);
                if (i % 2 > 0) {
                    out.print("<tr class=\"pure-table-odd\">");
                } else {
                    out.print("<tr>");
                }

                out.print("<td>" + b.getOrderId() + "</td>");
                out.print("<td>" + b.getClientId() + "</td>");
                out.print("<td>" + b.getTotalPrice() + "</td>");
                out.print("<td>" + b.getPircePoint() + "</td>");
                out.print("<td>" + b.getPaymentmethod() + "</td>");
                out.print("<td>" + b.getStatus() + "</td>");
                out.print("<td>" + b.getOrderTime() + "</td>");
                out.print("</tr>");
            }
            out.print("</tbody>");

        %>

    </body>
</html>
