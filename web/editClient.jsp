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
            String type = c.getId() != null ? "update" : "add";
            String id = c.getId() != null ? c.getId() : "" ;
            String name = c.getId() != null ? c.getName(): "" ;
            String tel = c.getId() != null ? c.getTel()+"": "" ;
            String address = c.getId() != null ? c.getAddress(): "" ;
            String username = c.getId() != null ? c.getUsername(): "" ;
            String password = c.getId() != null ? c.getPassword(): "" ;
            String status = c.getId() != null ? Boolean.toString(c.isStatus()): "" ;
            String balance = c.getId() != null ? Double.toString(c.getBalance()): "" ;
            String point = c.getId() != null ? Integer.toString(c.getPoint()): "" ;
            String admin = c.getId() != null ? Boolean.toString(c.isAdmin()): "" ;
            
        %>
        <h1><%=type%></h1>
        <form method="post" action="addclient">
            <input type="hidden" name="action" value="<%=type%>"/>
            <table border="0">
                <tr>
                    <td><p align="right" /><b>Client id </b></td>
                    <td><p /><input type="text" name="id" maxlength="5" size="8" value="<%=id%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Client name </b></td>
                    <td><p /><input type="text" name="name" maxlength="25" size="30" value="<%=name%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Contact number </b></td>
                    <td><p /><input type="text" name="tel" maxlength="8" size="10" value="<%=tel%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Address </b></td>
                    <td><p /><input type="text" name="address" maxlength="100" size="105" value="<%=address%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Login name </b></td>
                    <td><p /><input type="text" name="login_ac" maxlength="20" size="25" value="<%=username%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Login password </b></td>
                    <td><p /><input type="text" name="login_pw" maxlength="16" size="20" value="<%=password%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Login status(Y/N) </b></td>
                    <td><p /><input type="text" name="status" maxlength="1" size="1" value="<%=status%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Balance </b></td>
                    <td><p /><input type="number" name="balance" step="10" min="0" maxlength="20" size="25" value="<%=balance%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Point </b></td>
                    <td><p /><input type="number" name="point" min="0" max="10000" value="<%=point%>"/></td>
                </tr>
                <tr>
                    <td><p align="right" /><b>Is admin?:(Y/N) </b></td>
                    <td><p /><input type="text" name="adminOrNot" maxlength="1" size="1" value="<%=admin%>"/></td>
                </tr>
                <tr>
                    <td colspan="2"><p align="center" /><input type="submit" name="test" value="update"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
