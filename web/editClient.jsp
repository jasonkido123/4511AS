<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Client</title>
    </head>
    <body>
        <jsp:useBean id="c" scope="request" class="ict.bean.ClientInfo" />
        <%
            String type = c.getCustId() != null ? "edit" : "add";
            String id = c.getCustId() != null ? c.getCustId() : "" ;
            String name = c.getCustId() != null ? c.getName(): "" ;
            String tel = c.getCustId() != null ? c.getTel(): "" ;
            String age = c.getCustId() != null ? Integer.toString(c.getAge()): "" ;
        %>
        <h1><%=type%></h1>
        <form method="post" action="handleEdit">
            <input type="hidden" name="action" value="<%=type%>"/>
            <table border="0">
                <tr>
                    <td><p align="right" /><b>id </b></td>
                    <td><p /><input type="text" name="id" maxlength="10" size="15" value="<%=id%>"></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>name </b></td>
                    <td><p /><input type="text" name="name" maxlength="10" size="15" value="<%=name%>"></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>tel </b></td>
                    <td><p /><input type="text" name="tel" maxlength="10" size="15" value="<%=tel%>"></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>age </b></td>
                    <td><p /><input type="text" name="age" maxlength="10" size="15" value="<%=age%>"></td>
                </tr>
                <tr>
                    <td colspan="2"><p align="center" /><input type="submit" value="submit"</td>
                </tr>
            </table>
        </form>
    </body>
</html>
