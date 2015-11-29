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
        <style>
            div {
                padding-left: 1cm;
                padding-top: 0.5cm;
            }
        </style>

    </head>

    <body>

        <div>
            <form  class="pure-form" id="queryOrder" action="updateOrder" method="get">
                <input type="hidden" name="action" value="search" >
                <input type="text" name="keyword">
                <select name="col">
                    <option value="OrderId" >OrderID</option>
                    <option value="clientId" >ClientID</option>
                    <option value="status" >Status</option>
                </select>
                <button type="submit" class="pure-button pure-button-primary">Search</button>
            </form>
        </div>

        <div>
            <%
                ArrayList<OrderBean> orders = (ArrayList<OrderBean>) request.getAttribute("orders");
                String[] status = {"process", "cancel", "delivered", "packed-up"};
                out.print("<form action=\"updateOrder\" method=\"post\" class=\"pure-form pure-form-stacked\" >");

                out.print("<input type=\"hidden\" name=\"action\" value=\"update\">");

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

                    out.print("<td><input name=\"orderid\" value=\"" + b.getOrderId() + "\" readonly >" + "</td>");
                    out.print("<td>" + b.getClientId() + "</td>");
                    out.print("<td>" + b.getTotalPrice() + "</td>");
                    out.print("<td>" + b.getPircePoint() + "</td>");
                    out.print("<td>" + b.getPaymentmethod() + "</td>");

                    String cs = b.getStatus();
                    if (cs.equals("cancel")) {
                        out.print("<td><select readonly name=\"status\">");
                    } else {
                        out.print("<td><select name=\"status\">");
                    }

                    for (int j = 0; j < status.length; j++) {
                        if (status[j].equals(cs)) {
                            out.print("<option selected value=\"" + b.getStatus() + "\">" + b.getStatus() + "</option>");
                        } else {
                            out.print("<option value=\"" + status[j] + "\"  >" + status[j] + "</option>");
                        }
                    }
                    out.print("</select></td>");
                    out.print("<td>" + b.getOrderTime() + "</td>");
                    out.print("</tr>");
                }

                out.print("</tbody>");
                out.print("</table>");
                out.print("<button type=\"submit\" class=\"pure-button pure-button-primary\">Submit</button>");
                out.print("</form>");

            %>
        </div>

    </body>
</html>
