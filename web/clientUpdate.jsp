<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update client information</title>
    </head>
    <body>
        <jsp:useBean id="c" scope="request" class="ict.bean.ClientInfo" />
        <%
            String id = c.getId() != null ? c.getId() : "" ;
            String name = c.getId() != null ? c.getName(): "" ;
            String tel = c.getId() != null ? c.getTel()+"": "" ;
            String address = c.getId() != null ? c.getAddress(): "" ;
            String username = c.getId() != null ? c.getUsername(): "" ;
            String password = c.getId() != null ? c.getPassword(): "" ;            
        %>
        <h1>Update information</h1>
        <form method="post" action="handleClientUpdate">
            <input type="hidden" name="action" value="update"/>
            <table border="0">
                <p /><input type="hidden" name="id" maxlength="5" size="8" value="<%=id%>"/>
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
                    <td colspan="2"><p align="center" /><input type="submit" value="submit"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
