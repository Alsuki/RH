/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RH_servlet;
/**
 *
 * @author alvega
 */
public class login_Servlet extends javax.servlet.http.HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
           throws javax.servlet.ServletException, java.io.IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        java.io.PrintWriter out = response.getWriter();
        
        RH_beans.Auth.LoginUser = request.getParameter("Name");
        RH_beans.Auth.LoginPass = request.getParameter("Password");
        
        javax.servlet.http.HttpSession session = request.getSession(false);
        if(session!=null) {
            session.setAttribute("user", RH_beans.Auth.LoginUser);
        }

        if (RH_beans.Auth.Login())
        {
            javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
            rd.forward(request, response);
        }else {
            out.print("<p style=\"color:red\">Sorry username or password error</p>"); 
            javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
        }
        out.close();
    }
}
