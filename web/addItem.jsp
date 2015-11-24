<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item</title>
    </head>
    <body>
        <h1>Please enter the following details:</h1>
        <form method="post" action="additem">
            <table border='1'>
                <tr>
                    <td>Item id:</td>
                    <td><input type="text" name="itemid" maxlength="5" size="5"></td>
                </tr>
                <tr>
                    <td>Item name:</td>
                    <td><input type="text" name="itemname" maxlength="25" size="25"></td>
                </tr>
                <tr>
                    <td>Price($):</td>
                    <td><input type="text" name="itemprice" min="0" max="1000" value="10"></td>
                </tr>
                <tr>
                    <td>Category:</td>
                    <td><input type="text" name="category" maxlength="15" size="20"></td>
                </tr>
                <tr>
                    <td>Descriptions:</td>
                    <td><input type="text" name="descriptions" maxlength="95" size="100"></td>
                </tr>
                <tr>
                    <td><a href="welcome.jsp">Back</a></td>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
