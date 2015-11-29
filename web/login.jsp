<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
        <link href="max/css/button.css" rel="stylesheet" type="text/css"/>
        <style>
            input, .pure-button {
                width:4cm;
            }

            #outPopUp{
                position:absolute;
                top:50%;
                left:50%;
                margin:-100px 0 0 -150px;
            }
        </style>
    </head>
    <body background="img/bg.jpg" >
        <div id="outPopUp">
            <form method="post" action="main"  class="pure-form pure-form-stacked" >

                <label for="username" style="color: white" >Username</label>
                <input type="text" id="username" name="username" maxlength="15" size="20">

                <label for="password" style="color: white" >Password</label>
                <input type="password" name="password" maxlength="11" size="16">

                <input type="hidden" name="action" value="authenticate"/>
                <label></label>
                <button type="submit" class="pure-button pure-button-primary">Login</button>
            </form>
            <form  id="form2" action="register.jsp" class="pure-form pure-form-stacked">
                <label></label>
                <button type="submit" class="button-secondary pure-button">Register</button>
            </form>
        </div>
    </body>
</html>
