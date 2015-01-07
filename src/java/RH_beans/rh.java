/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RH_beans;
/**
 *
 * @author alvega && Edson
 */
public class rh extends javax.servlet.http.HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
        java.io.PrintWriter out = response.getWriter();
        javax.servlet.http.HttpSession session = request.getSession(false);
        try {
            if (session != null) {
            //html 
               
            } else {
                javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.include(request, response);
            }
        } finally {
           out.close();
       }
    }
}
