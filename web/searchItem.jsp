<%-- 
    Document   : searchItem
    Created on : Nov 28, 2015, 12:06:26 PM
    Author     : YIKFEI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.ArrayList,max.bean.ItemBean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Item</title>
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
            <form class="pure-form" method="get" action="searchItem">
                <input type="hidden"  name="action"  value="condition" />
                <input name="keyword"/>
                <select name="col" >
                    <option  value="ItemId">ID</option>
                    <option  value="Item_name" selected>Name</option>
                    <option  value="price">Price</option>
                    <option  value="category">Category</option>
                    <option  value="descriptions">Description</option>
                    <option  value="brand">Brand</option>
                    <option  value="quantity">Quantity</option>
                    <option  value="point">Point</option>
                </select>
                <button type="submit" class="pure-button pure-button-primary">Search</button>
            </form>
        </div>
        <br/>
        <div>
            <%
                ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getAttribute("items");
                //create table with table head
                out.print("<table class=\"pure-table pure-table-bordered\">");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Id</th>");
                out.print("<th>Name</th>");
                out.print("<th>Price</th>");
                out.print("<th>Category</th>");
                out.print("<th>Description</th>");
                out.print("<th>Brand</th>");
                out.print("<th>Quantity</th>");
                out.print("<th>Point</th>");
                out.print("<th>Modify</th>");
                out.print("</tr>");
                out.print("</thead>");

                //out.print("<tbody>");
                for (int i = 0; i < items.size(); i++) {
                    ItemBean b = items.get(i);

                    if (i % 2 > 0) {
                        out.print("<tr class=\"pure-table-odd\">");
                    } else {
                        out.print("<tr>");
                    }

                    out.print("<td>" + b.getItemId() + "</td>");
                    out.print("<td>" + b.getItem_name() + "</td>");
                    out.print("<td>" + b.getPrice() + "</td>");
                    out.print("<td>" + b.getCategory() + "</td>");
                    out.print("<td>" + b.getDescriptions() + "</td>");
                    out.print("<td>" + b.getBrand() + "</td>");
                    out.print("<td>" + b.getQuantity() + "</td>");
                    out.print("<td>" + b.getPoint() + "</td>");
                    out.print("<td><a href=\"modifyItem?action=show&itemid=" + b.getItemId() + "\">Modify</a></td>");
                    out.print("</tr>");
                }
                //out.print("</tbody>");

                out.print("</table>");

            %>
        </div>
    </body>
</html>
