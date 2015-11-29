<%@page import="ict.bean.ClientInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Client</title>
        <link href="max/css/div.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
<<<<<<< HEAD
<<<<<<< 218b514906bd530507f69ed9d49f65ec396ae9e8
=======
>>>>>>> origin/master
        <%
            ClientInfo bean = (ClientInfo) request.getSession().getAttribute("client");
            if (bean.getAdmin().equals("N")) {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/welcomeNormal.jsp");
                rd.forward(request, response);
            }
        %>
<<<<<<< HEAD
=======
>>>>>>> hjkl
=======
>>>>>>> origin/master
        <div class="distance" >
            <h1>Please enter the following details</h1>
        </div>
        <form method="post" action="addclient" class="pure-form pure-form-aligned">
            <fieldset>
                <input type="hidden" name="action" value="add"/>

                <div class="pure-control-group">
                    <label for="clientid" >Client id</label>
                    <input type="text" id="clientid"  name="clientid" maxlength="5" size="8">
                </div>

                <div class="pure-control-group">
                    <label for="clientname">
                        Client Name
                    </label>
                    <input type="text" name="clientname" maxlength="25" size="30">
                </div>

                <div class="pure-control-group">
                    <label for="tel">Contact number</label>
                    <input type="text" id="tel"  name="tel" maxlength="8" size="10">
                </div>

                <div class="pure-control-group">
                    <label for="address">Address</label>
                    <td><input type="text" id="address" name="address" maxlength="100" size="105"></td>
                </div>

                <div class="pure-control-group">
                    <label for="login_ac">Login name</label>
                    <input type="text" id="login_ac"  name="login_ac" maxlength="20" size="25">
                </div>

                <div class="pure-control-group">
                    <label for="login_pw">Login password</label>
                    <input type="text" id="login_pw" name="login_pw" maxlength="16" size="20">
                </div>

                <div class="pure-control-group">
                    <label>Login status(Y/N)</label>
                    <input type="text" name="status" maxlength="1" size="1">
                </div>

                <div class="pure-control-group">
                    <label>Balance</label>
                    <input type="number" name="balance" step="10" value="0" min="0" maxlength="20" size="25">
                </div>

                <div class="pure-control-group">
                    <label>Point</label>
                    <input type="number" name="point" min="0" max="10000">
                </div>

                <div class="pure-control-group">
                    <label>Is admin?:(Y/N)</label>
                    <input type="text" name="adminOrNot" maxlength="1" size="1">
                </div>

                <div class="pure-control-group">
                    <label><a href="welcome.jsp">Back</a></label>
                    <button type="submit" class="pure-button pure-button-primary">Submit</button>
                </div>
            </fieldset>
        </form>

    </body>
</html>
