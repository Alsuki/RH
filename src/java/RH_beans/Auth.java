/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RH_beans;

/**
 *
 * @author alvega
 */
public class Auth extends javax.servlet.http.HttpServlet {
            
   public void setLoginUser (String LogUser) {
       this.LoginUser = LogUser; 
   }
   
   public void setLoginPass (String LogPass) {
       this.LoginPass = LogPass;
   }
   
   public boolean getLogin(){
       // corrigido o construtor para apenas rentornar verdadiro/falso.
        this.ErrorDetected = "";
        if (CheckLogin(this.LoginUser, this.LoginPass).contains("error")) {
            String segments[] = CheckLogin(this.LoginUser, this.LoginPass).split(";");
            this.ErrorDetected = segments[segments.length - 1];
            return false;
        } else {
            return true;
        }
   }
   
   private String getError(){
       return this.ErrorDetected;
   }
   
   public String getLoginUser(){
       return this.LoginUser;
   }
   
   private String LoginUser;
   private String LoginPass;
   private String ErrorDetected;
   
   private boolean User(String user, boolean n) {   
        //n tem que ser "true" na primeira chamada
        boolean verify= false;

        String SQLQuery = "SELECT Nome from account_login where Nome = \"" + user + "\";";
        if (!SQLconnector.SQLconnetionALIVE() && n) {
            Conf.Connect();
            User(user,false);
        }
        
        if (SQLconnector.SQLconnetionALIVE() && n) {
            if (SQLconnector.SQLVerify(SQLQuery,user,false)) {
                verify = true;
            }
        }
        
        if (SQLconnector.SQLconnetionALIVE() && !n){
            if (SQLconnector.SQLVerify(SQLQuery,user,false)){
                verify = true;
            }
        }
    
        /* se nao for = verdade retorna o false declarado acima. */
        return verify;
   }

   private boolean Pass(String password,String user, boolean n ) {
       boolean verify = false;
       String str = Conf.encrypt(password);
       String SQLQuery = "SELECT Password FROM account_login WHERE Nome= \"" + user + "\" AND Password=\"" + password + "\";";

       if (!SQLconnector.SQLconnetionALIVE() && n) {
            Conf.Connect();
            Pass(str,user,false);
        }
       
       if (SQLconnector.SQLconnetionALIVE() && n) {
           if (SQLconnector.SQLVerify(SQLQuery, password, false)) {
                verify = true;
           }
       }
       
       if (SQLconnector.SQLconnetionALIVE() && !n){
           if (SQLconnector.SQLVerify(SQLQuery, password, false)){
                verify = true;
           }
       }
    /* se nao for = verdade retorna o false declarado acima. */
     return verify;
     }

   private boolean Estado(String user){
       boolean verify = false;
       //Novo select from account_login onde user estado
       //Se o estado for 0 entao true so o estado for 1 entao false
        String SQLQuery = "SELECT Estado FROM account_login WHERE Nome=\"" + user +"\";";
        if (SQLconnector.SQLVerify(SQLQuery,"0",false)) {
            verify = true;
        } 
        return verify;
   }

   private int contador(int i){
      i++;
      return i;
   }
   
   private  String CheckLogin(String luser, String lpas) {
      String status = "";
      int Counter = 0;
      if (User(luser,true)) {
          if (Pass(lpas,luser,true)){
              //  verificar logica esta com erro
              if(Estado(luser)) {
                  status = "login";
              } else {
                  status = "error;Your account is lock, please contact this system Administrator.";
              } 
          } else {
              // falta verificar o estado da variavel
              String SQLQuery = "SELECT Contador FROM account_login WHERE Nome=\"" + luser +"\";";
             // String Rturn = SQLconnector.SQLSelect(SQLQuery,0);
              String Rturn = "";
              Counter = Integer.parseInt(Rturn);
              if (contador(Counter /*converter para inteiro*/) < 3) {
                  String SQLInsert = "INSERT INTO account_lock Integer.toString(Counter)";
                  SQLconnector.SQLInsert(SQLInsert);
              } else { 
                  status = "error;User or Password were incorrect. Please try again!";
              }
          } 
      } else {
          status = "error;User or Password were incorrect. Please try again!";
      }
      return status;
    }
   
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
        this.LoginUser = request.getParameter("Name");
        this.LoginPass = request.getParameter("Password");
        try {           
            if (this.getLogin()){
               javax.servlet.http.HttpSession session = request.    getSession();
               session.setAttribute("user", LoginUser);
               javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("rh");
               rd.forward(request, response);
           }else {
               javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
               rd.include(request, response);
           }
               
       } finally {
           out.close();
       }
    }

 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
