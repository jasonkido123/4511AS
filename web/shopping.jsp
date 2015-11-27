<%@page import="ict.db.CategoryDb"%>
<%@page import="ict.bean.Shopping"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script type="text/javascript" src="/jquery/jquery-latest.js"></script> 
    <script type="text/javascript" src="/jquery/jquery.tablesorter.js"></script> 

    <script type="text/javascript">
        $(document).ready(function ()
        {
            $("#myTable").tablesorter(); 
            $("#myTable").tablesorter({sortList: [[0, 0], [1, 0]]});
        }
        );

    </script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <form action="shopping">
            <table border="1">
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
                    <td>
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
        <%if (al1 != null) {
                String s = "<table border=\"1\" id=\"search\" class=\"tablesorter\"><thead><tr><td>ItemName</td><td>descriptions</td><td>category</td><td>brand</td><td>quantity</td><td>Price</td><td>Point</td><td>Add To Cart</td></tr></thead>";
                s += "<tbody>";
                for (int i = 0; i < al1.size(); i++) {
                    Shopping item = (Shopping) al1.get(i);
                    s += "<tr><td>" + item.getItemName() + "</td>";
                    s += "<td>" + item.getDescriptions() + "</td>";
                    s += "<td>" + item.getCategory() + "</td>";
                    s += "<td>" + item.getBrand() + "</td>";
                    s += "<td>" + item.getQuantity() + "</td>";
                    s += "<td>" + item.getPrice() + "</td>";
                    s += "<td>" + item.getPoint() + "</td>";
                    s += "<td>" + "<a href=\"ShoppingCart?action=add&pid=" + item.getItemId() + "\"><input type=\"button\" value=\"Add\"/ ></a></td>";
                    s += "</tr>";
                }
                s += "</tbody></table>";
                out.print(s);
            }%>


        <table id="myTable" class="tablesorter"> 
            <thead> 
                <tr> 
                    <th>Last Name</th> 
                    <th>First Name</th> 
                    <th>Email</th> 
                    <th>Due</th> 
                    <th>Web Site</th> 
                </tr> 
            </thead> 
            <tbody> 
                <tr> 
                    <td>Smith</td> 
                    <td>John</td> 
                    <td>jsmith@gmail.com</td> 
                    <td>$50.00</td> 
                    <td>http://www.jsmith.com</td> 
                </tr> 
                <tr> 
                    <td>Bach</td> 
                    <td>Frank</td> 
                    <td>fbach@yahoo.com</td> 
                    <td>$50.00</td> 
                    <td>http://www.frank.com</td> 
                </tr> 
                <tr> 
                    <td>Doe</td> 
                    <td>Jason</td> 
                    <td>jdoe@hotmail.com</td> 
                    <td>$100.00</td> 
                    <td>http://www.jdoe.com</td> 
                </tr> 
                <tr> 
                    <td>Conway</td> 
                    <td>Tim</td> 
                    <td>tconway@earthlink.net</td> 
                    <td>$50.00</td> 
                    <td>http://www.timconway.com</td> 
                </tr> 
            </tbody> 
        </table> 
    </body>
</html>
