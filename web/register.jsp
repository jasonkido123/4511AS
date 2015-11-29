<%-- 
    Document   : register
    Created on : Nov 29, 2015, 8:51:56 PM
    Author     : YIKFEI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
        <link href="max/css/pure/pure-min.css" rel="stylesheet" type="text/css"/>
        <link href="max/css/div.css" rel="stylesheet" type="text/css"/>
        <style>

            input, .pure-button {
                width:4cm;
            }
        </style>
    </head>
    <body>
        <div class="distance">
            <form class="pure-form pure-form-stacked" action="regAcc" >
                <fieldset>

                    <label for="name">Name</label>
                    <input name="name" id="name" placeholder="Your name">

                    <label for="tel">Phone</label>
                    <input name="tel" id="tel" placeholder="Phone number">

                    <label for="address">Address</label>
                    <input  name="address" id="address" placeholder="Home Address">

                    <label for="username">Username</label>
                    <input name="username" id="username" placeholder="Username">

                    <label for="password">Password</label>
                    <input name="password" id="password" type="password" placeholder="Password">

                    <button type="submit" class="pure-button pure-button-primary">GO!</button>
                </fieldset>
            </form>

        </div>
    </body>
</html>
