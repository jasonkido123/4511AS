<%-- 
    Document   : checkLastTenOrder
    Created on : Nov 29, 2015, 12:25:01 AM
    Author     : chanyan
--%>

<%@page import="ict.db.*"%>
<%@page import="ict.bean.*"%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="jquery-ui/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="jquery-ui/external/jquery/jquery.js" type="text/javascript"></script>
        <script src="jquery-ui/jquery-ui.js" type="text/javascript"></script>
        <%!
            String dbUser = "root";
            String dbPassword = "";
            String dbUrl = "jdbc:mysql://localhost/jsp_ass";
        %>
        <title>Check Last Ten order</title>
        <script>
            $(function () {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body>
        <div id="accordion">
            <% ArrayList<Order> alo = (ArrayList<Order>) request.getAttribute("orderList");
                OrderInfoDb oidb = new OrderInfoDb(dbUrl, dbUser, dbPassword);
                ItemDb idb = new ItemDb(dbUrl, dbUser, dbPassword);
                String s = "";
                int j = 0;
                if (alo.size() > 10) {
                    j = 10;
                } else {
                    j = alo.size();
                }
                for (int i = 0; i < j; i++) {
                    String paymentMethod = "";
                    double price = 0;
                    if (alo.get(i).getPaymentMothed().equals("p")) {
                        paymentMethod = "Point";
                        price = (double) alo.get(i).getGiftPoint();
                    } else if (alo.get(i).getPaymentMothed().equals("b")) {
                        paymentMethod = "Balance";
                        price = alo.get(i).getTotalPrice();
                    }
                    ArrayList<OrderInfo> aloi = oidb.queryOrderInfoById(alo.get(i).getOrderId());
                    s += "<h3>" + alo.get(i).getOrderId() + "</br>" + "Order Time:" + alo.get(i).getOrderTime()
                            + "</br>" + "Payment Method:" + paymentMethod
                            + "</br>" + "Total Price:" + price
                            + "</br>" + "status:" + alo.get(i).getStatus();

                    s += "</h3>";
                    s += "<div>";
                    s += "<p><ul>";
                    for (int k = 0; k < aloi.size(); k++) {
                        s += "<li>";
                        s += idb.SearchByIdGiftString(aloi.get(k).getItemId()) + "  " + aloi.get(k).getQuantity() + "  " + aloi.get(k).getPoint() + "  " + aloi.get(k).getPrice();
                        s += "</li>";

                    }
                    if (alo.get(i).getStatus().equals("process")) {
                        s += "</br><a href=\"CancelOrderController?action=cancel&oid=" + alo.get(i).getOrderId() + "\"><input type=\"button\" value=\"Cancel Order\"></a>";
                    }
                    s += "</ul></p>";
                    s += "</div>";
                }

                out.print(s);
            %>
        </div>
    </body>
</html>
