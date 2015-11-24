<%-- 
    Document   : shopping
    Created on : Nov 23, 2015, 10:52:28 PM
    Author     : chanyan
--%>

<%@page import="ict.db.CategoryDb"%>
<%@page import="ict.bean.Shopping"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <form action="shopping">
            <table border="1">
                <tr><td colspan="5">Search</td></tr>
                <tr><td>Product Name:<input type="Textbox" name="SearchName"></td>
                    <td>Money range:</br>
                        Min:
                    <select>
                        <option value="" name="min"></option>
                        <option value="1" name="min">1$</option>
                        <%
                            for(int i=5;i<=15;i+=5){
                                out.print("<option value=\""+i+"\" name=\"min\">"+i+"$"+"</option>");
                            }
                            for(int i=20;i<=100;i+=10){
                                out.print("<option value=\""+i+"\" name=\"min\">"+i+"$"+"</option>");
                            }
                            for(int i=200;i<=500;i+=100){
                                out.print("<option value=\""+i+"\" name=\"min\">"+i+"$"+"</option>");
                            }
                        %>
                        </select>~
                        Max:<select>
                        <option value="" name="max"></option>
                        <option value="1" name="max">1$</option>
                        <%
                            for(int i=5;i<=15;i+=5){
                                out.print("<option value=\""+i+"\" name=\"max\">"+i+"$"+"</option>");
                            }
                            for(int i=20;i<=100;i+=10){
                                out.print("<option value=\""+i+"\" name=\"max\">"+i+"$"+"</option>");
                            }
                            for(int i=200;i<=500;i+=100){
                                out.print("<option value=\""+i+"\" name=\"max\">"+i+"$"+"</option>");
                            }
                        %>
                        </select>
                    </td>
                    <td>
                        Brand:
                        <input type="Textbox" name="SearchBrand">
                    </td>    
                    <td>
                        <% ArrayList<Shopping> al1 = (ArrayList<Shopping>)request.getAttribute("product");
                            String dbUser = this.getServletContext().getInitParameter("dbUser");
                            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
                            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
                            CategoryDb db = new CategoryDb(dbUrl, dbUser, dbPassword);
                            ArrayList<Shopping> al = db.AllCategory();
                        %>
                        <select>
                            <option value="" name="category"></option>
                            <%
                                
                                for(int i=0;i<al.size();i++){
                                    out.print("<option value=\""+al.get(i)+"\" name=\"category\">"+al.get(i)+"</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td>
                        <input type="submit" name="action" value=search />
 
                    </td>
                </tr>
            </table>
            </br>
            
        </form>
    </body>
</html>
