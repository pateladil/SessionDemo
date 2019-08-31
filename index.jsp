<%-- 
    Document   : index
    Created on : Sep 4, 2018, 10:30:40 PM
    Author     : student74
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>LOGIN PAGE</h1>
        <form action="A_servlet" method="POST">
            User Name:<input type="text" name="uname" value="" /><br><br>
            Password:<input type="password" name="pass" value="" /><br><br>
            <input type="submit" value="login" />
        </form>
    </center>
    </body>
</html>
