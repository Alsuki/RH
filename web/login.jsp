<%-- 
    Document   : login
    Created on : Nov 19, 2014, 1:04:21 AM
    Author     : alvega
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/RH.css"/>
        <title>RH Managing Your Future</title>
    </head>
    <body bgcolor="#e2e2ee">
        <%-- fonte da imagem
           http://blog.inszoom.com/five-reasons-why-immigration-is-important-in-overall-hr-strategy/humanresources/
        --%>
        <div style="width:200px;height:120px;border:1px solid #000; margin:0 auto;">
            <form action="login" method="post">
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
                <!--<input type="submit" value="Login"/><br> -->
                <script>
                    // JavaScript para chamar Funcoes java
                    function Preform_login(){
                        
                    }
                </script>
                <button type="button" onclick="Preform_login()" style="margin: 0px auto; display: block"><b>Login</b></button>
        </div>
        <br><br><br><br><br><br><br><br>
        <!-- <img src="Images/HumanResources-300x182.jpg" style="width:300px; height:182px; margin:0px auto; display: block"> -->
        <img_center src="Images/HumanResources-300x182.jpg" style="width:300px; height:182px">
        <!-- footer referenciador de tecnologias usadas -->
        <footer>
            <a href="http://www.w3.org/"><img src="http://www.w3.org/Icons/valid-html401-blue" style="height: 30px"></a>
            <a href="http://www.w3schools.com/css/"><img src="http://www.w3.org/Icons/valid-css2-blue.png" style="height: 30px"></a>
            
        </footer>
    </body>
</html>