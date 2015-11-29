<%@page import="ict.db.CategoryDb"%>
<%@page import="ict.bean.Shopping"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <script src="jquery/jquery-latest.js" type="text/javascript"></script>
    <script type="text/javascript" src="jquery/jquery.tablesorter.js"></script> 
    <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        $(document).ready(function ()
        {
            $("#search").tablesorter({sortList: [[0, 0], [1, 0]]});
        }
        );

    </script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <form action="shopping">
            <table border="1"  class="pure-table pure-table-bordered">
                <tr><td colspan="6">Search</td></tr>
                <tr><td>Product Name:<input type="Textbox" name="SearchName"></td>
                    <td>Money range:</br>
                        Min:
                        <select name="min">
                            <option value=""></option>
                            <option value="1">1$</option>
                            <%
                                for (int i = 5; i <= 15; i += 5) {
                                    out.print("<option value=\"" + i + "\" >" + i + "$" + "</option>");
                                }
                                for (int i = 20; i <= 100; i += 10) {
                                    out.print("<option value=\"" + i + "\" >" + i + "$" + "</option>");
                                }
                                for (int i = 200; i <= 500; i += 100) {
                                    out.print("<option value=\"" + i + "\" >" + i + "$" + "</option>");
                                }
                            %>
                        </select>~
                        Max:<select name="max">
                            <option value="" ></option>
                            <option value="1" >1$</option>
                            <%
                                for (int i = 5; i <= 15; i += 5) {
                                    out.print("<option value=\"" + i + "\" >" + i + "$" + "</option>");
                                }
                                for (int i = 20; i <= 100; i += 10) {
                                    out.print("<option value=\"" + i + "\" >" + i + "$" + "</option>");
                                }
                                for (int i = 200; i <= 500; i += 100) {
                                    out.print("<option value=\"" + i + "\" >" + i + "$" + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td>
                        Brand:
                        <input type="Textbox" name="SearchBrand">
                    </td>    
                    <td>Category:
                        <% ArrayList<Shopping> al1 = (ArrayList<Shopping>) request.getAttribute("product");
                            String dbUser = this.getServletContext().getInitParameter("dbUser");
                            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
                            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
                            CategoryDb db = new CategoryDb(dbUrl, dbUser, dbPassword);
                            ArrayList<Shopping> al = db.AllCategory();
                        %>
                        <select name="category">
                            <option value=""></option>
                            <%
                                for (int i = 0; i < al.size(); i++) {
                                    out.print("<option value=\"" + al.get(i) + "\">" + al.get(i) + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td>Point range:</br>
                        Min:
                        <select name="Pmin">
                            <option value=""></option>
                            <option value="1">1Point</option>
                            <%
                                for (int i = 5; i <= 15; i += 5) {
                                    out.print("<option value=\"" + i + "\" >" + i + "Point" + "</option>");
                                }
                                for (int i = 20; i <= 100; i += 10) {
                                    out.print("<option value=\"" + i + "\" >" + i + "Point" + "</option>");
                                }
                                for (int i = 200; i <= 500; i += 100) {
                                    out.print("<option value=\"" + i + "\" >" + i + "Point" + "</option>");
                                }
                            %>
                        </select>~
                        Max:<select name="Pmax">
                            <option value="" ></option>
                            <option value="1" >1Point</option>
                            <%
                                for (int i = 5; i <= 15; i += 5) {
                                    out.print("<option value=\"" + i + "\" >" + i + "Point" + "</option>");
                                }
                                for (int i = 20; i <= 100; i += 10) {
                                    out.print("<option value=\"" + i + "\" >" + i + "Point" + "</option>");
                                }
                                for (int i = 200; i <= 500; i += 100) {
                                    out.print("<option value=\"" + i + "\" >" + i + "Point" + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td>
                        <input type="submit" name="action" value=search />
                    </td>
                </tr>
            </table>
        </form>
        </br>
        <table border="1" id="search" class="tablesorter pure-table pure-table-bordered">
            <thead>
                <tr>
                    <th>ItemName</th>
                    <th>descriptions</th>
                    <th>category</th>
                    <th>brand</th>
                    <th>quantity</th>
                    <th>Price</th>
                    <th>Point</th>
                    <th>Add To Cart</th>
                </tr>
            </thead>
            <tbody>
                <%if (al1 != null) {
                        for (int i = 0; i < al1.size(); i++) {
                            Shopping item = (Shopping) al1.get(i);
                            out.println("<tr><td>" + item.getItemName() + "</td>");
                            out.println("<td>" + item.getDescriptions() + "</td>");
                            out.println("<td>" + item.getCategory() + "</td>");
                            out.println("<td>" + item.getBrand() + "</td>");
                            out.println("<td>" + item.getQuantity() + "</td>");
                            out.println("<td>" + item.getPrice() + "</td>");
                            out.println("<td>" + item.getPoint() + "</td>");
                            out.println("<td>" + "<a href=\"ShoppingCart?action=add&pid=" + item.getItemId() + "\"><input type=\"button\" value=\"Add\"/ ></a></td>");
                            out.println("</tr>");
                        }
                    }%>
            </tbody>
        </table>
    </body>
</html>
