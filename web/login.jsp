<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form method="post" action="main">
            <input type="hidden" name="action" value="authenticate"/>
            <table border="0">
                <tr>
                    <td><p align="right"><b>login:</b></p></td>
                    <td><p><input type="text" name="username" maxlength="15" size="20"></p></td>
                </tr>
                <tr>
                    <td><p align="right"><b>password:</b></p></td>
                    <td><p><input type="password" name="password" maxlength="11" size="16"></p></td>
                </tr>
                <tr>
                    <td colspan="2"><p align="center"><input type="submit" value="Login"></p></td>
                </tr>
            </table>
        </form>
    </body>
</html>
