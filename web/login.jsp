<%-- 
    Document   : login
    Created on : Feb 28, 2024, 5:34:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="login" method="post">
            <table>
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" id=""></td>
                    </tr>

                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" id=""></td>
                    </tr>
                <div style="color: red">${error}</div>

                <tr>
                    <td></td>
                    <td><input type="submit" value="submit"/></td>
                </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
