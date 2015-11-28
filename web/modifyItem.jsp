<%-- 
    Document   : modifyItem
    Created on : Nov 28, 2015, 10:48:16 PM
    Author     : YIKFEI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,max.bean.ItemBean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Item</title>
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%
            ItemBean b = (ItemBean) request.getAttribute("item");
        %>

        <form method="get" action="modifyItem" class="pure-form pure-form-aligned">

            <input type="hidden" name="action" value="update">
            <div class="pure-control-group">
                <label for="itemid">ID</label>
                <input type="text" id="itemid"  name="itemid"  value="<%= b.getItemId()%>" readonly>
            </div>
            <div class="pure-control-group">
                <label for="item_name">Name</label>
                <input type="text" id="item_name"  name="item_name" value="<%= b.getItem_name()%>" maxlength="25" >
            </div>
            <div class="pure-control-group">
                <label for="price">Price</label>
                <input type="text" id="price"  name="price" value="<%= b.getPrice()%>" maxlength="5" >
            </div>
            <div class="pure-control-group">
                <label for="category">Category</label>
                <input type="text" id="category"  name="category" value="<%= b.getCategory()%>">
            </div>
            <div class="pure-control-group">
                <label for="descriptions">Description</label>
                <input type="text" id="descriptions"  name="descriptions" value="<%= b.getDescriptions()%>">
            </div>
            <div class="pure-control-group">
                <label for="brand">Brand</label>
                <input type="text" id="brand"  name="brand" value="<%= b.getBrand()%>">
            </div>
            <div class="pure-control-group">
                <label for="brand">Quantity</label>
                <input type="text" id="quantity"  name="quantity" value="<%= b.getQuantity()%>">
            </div>
            <div class="pure-control-group">
                <label for="point">Point</label>
                <input type="text" id="point"  name="point" value="<%= b.getPoint()%>">
            </div>

            <div class="pure-controls">
                <button type="submit" class="pure-button pure-button-primary">Update</button>
            </div>

        </form>

    </body>
</html>
