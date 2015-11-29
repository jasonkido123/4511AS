<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
        <link href="max/css/div.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="distance" >
            <form method="post" action="main"  class="pure-form pure-form-stacked" >

                <label for="username">Username</label>
                <input type="text" id="username" name="username" maxlength="15" size="20">

                <label for="password">Password</label>
                <input type="password" name="password" maxlength="11" size="16">

                <input type="hidden" name="action" value="authenticate"/>
                <button type="submit" class="pure-button pure-button-primary">Login</button>
            </form>
        </div>
    </body>
</html>
