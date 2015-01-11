<%-- 
    Document   : login
    Created on : Nov 19, 2014, 1:04:21 AM
    Author     : alvega && Edson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <title>RH Managing Your Future</title>
    </head>
    <body bgcolor="#e2e2ee">
        <div style="width:200px;height:120px;border:1px solid #000; margin:0 auto;">
            <form action="login_Servlet" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td>
                                Name
                            </td>
                            <td>
                                <input type="text" name="Name">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Password
                            </td>
                            <td>
                                <input type="password" name="Password">
                            </td>
                        </tr>
                    </tbody>
                </table><br><br>
                <input type="submit" value="login"/> 
                <input type="reset" value="clear"/>
            </form>
        </div>
        <br><br><br><br><br><br><br><br>
        <div id="logo">
            <img src="Images/HumanResources-300x182.jpg" style="width:300px; height:182px">
        </div>
        <!-- footer referenciador de tecnologias usadas -->
        <footer>
            <a href="http://www.w3.org/"><img id="img_LB" src="http://www.w3.org/Icons/valid-html401-blue"></a>
            <a href="http://www.w3schools.com/css/"><img id="img_LB2" src="http://www.w3.org/Icons/valid-css2-blue.png"></a>
        </footer>
    </body>
</html>
