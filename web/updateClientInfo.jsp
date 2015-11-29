<%@page import="ict.db.ClientDb"%>
<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Info</title>
    </head>
    <script>
        function isNumberKey(evt) {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
            return true;
        }
        function validate(form) {
            if (!valid) {
                alert('Please correct the errors in the form!');
                return false;
            } else {
                return confirm('Do you really want to submit the form?');
            }
        }
    </script>
    <%! private ClientInfo bean;
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost/jsp_ass";
        private ClientDb cdb = new ClientDb(dbUrl, dbUser, dbPassword);
    %>
    <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
    <body>
        <%
            bean = (ClientInfo) request.getSession().getAttribute("client");
            if (bean == null) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/loginError.jsp");
                rd.forward(request, response);
            }
        %>
        <form action="updateClientInfo" onSubmit="if(!confirm('Are you really to update these information?')){return false;}" method="post">
            <table border="1" class="pure-table pure-table-bordered">
                <tr>
                    <td>Name:</td>
                    <td>
                        <input type="text" value="<%//out.print(bean.getName());%>" name="name" />
                    </td>
                </tr>
                <tr>
                    <td>Tel:</td>
                    <td>
                        <input type="text" value=""<%//out.print("\""+bean.getTel() +"\" onkeypress=\"return isNumberKey(event)\"") ;%> name="tel"/>
                    </td>
                </tr>
                <tr>
                    <td>Delivery Address:</td>
                    <td>
                        <input type="text" value=" <%//out.print(bean.getAddress());%>" name="daddress"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">Update Pasword(Need over 6digit):</td>
                </tr>
                <tr>
                    <td> Old Password:</td>    
                    <td><input type="password" value="" name="Opassword"/></td>
                </tr>
                <tr>
                    <td>New Password:</td>
                    <td><input type="password" value="" name="Npassword"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <input type="submit" value="update" name="action"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
