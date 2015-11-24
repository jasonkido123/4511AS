<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Client</title>
    </head>
    <body>
        <h1>Please enter the following details:</h1>
        <form method="post" action="addclient">
            <input type="hidden" name="action" value="addclient"/>
            <table border='1'>
                <tr>
                    <td>Client id:</td>
                    <td><input type="text" name="clientid" maxlength="5" size="8"></td>
                </tr>
                <tr>
                    <td>Client name:</td>
                    <td><input type="text" name="clientname" maxlength="25" size="30"></td>
                </tr>
                <tr>
                    <td>Contact number:</td>
                    <td><input type="text" name="tel" maxlength="8" size="10"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" maxlength="100" size="105"></td>
                </tr>
                <tr>
                    <td>Login name:</td>
                    <td><input type="text" name="login_ac" maxlength="20" size="25"></td>
                </tr>
                <tr>
                    <td>Login password:</td>
                    <td><input type="text" name="login_pw" maxlength="16" size="20"></td>
                </tr>
                <tr>
                    <td>Login status(Y/N):</td>
                    <td><input type="text" name="status" maxlength="1" size="1"></td>
                </tr>
                <tr>
                    <td>Balance:</td>
                    <td><input type="number" name="balance" step="10" value="0" min="0" maxlength="20" size="25"></td>
                </tr>
                <tr>
                    <td>Point:</td>
                    <td><input type="number" name="point" min="0" max="10000"></td>
                </tr>
                <tr>
                    <td>Is admin?:(Y/N)</td>
                    <td><input type="text" name="adminOrNot" maxlength="1" size="1"></td>
                </tr>
                <tr>
                    <td><a href="welcome.jsp">Back</a></td>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
