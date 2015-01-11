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
public class Auth {
            
   public static boolean Login(){
       // corrigido o construtor para apenas rentornar verdadiro/falso.
        ErrorDetected = "";
        if (CheckLogin(LoginUser, LoginPass).contains("error")) {
            String segments[] = CheckLogin(LoginUser, LoginPass).split(";");
            ErrorDetected = segments[segments.length - 1];
            return false;
        } else {
            return true;
        }
   }
   
   private String Error(){
       return this.ErrorDetected;
   }
   
   public static String LoginUser;
   public static String LoginPass;
   public static String ErrorDetected;
   
   private static boolean User(String user, boolean n) {   
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

   private static boolean Pass(String password,String user, boolean n ) {
       boolean verify = false;
       // a ser usado depois...
       //String str = Conf.encrypt(password);
       String SQLQuery = "SELECT Password FROM account_login WHERE Nome= \"" + user + "\" AND Password=\"" + password + "\";";

       if (!SQLconnector.SQLconnetionALIVE() && n) {
            Conf.Connect();
            Pass(password,user,false);
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

   private static boolean Estado(String user){
       boolean verify = false;
       //Novo select from account_login onde user estado
       //Se o estado for 0 entao true so o estado for 1 entao false
        String SQLQuery = "SELECT Estado FROM account_login WHERE Nome=\"" + user +"\";";
        if (SQLconnector.SQLVerify(SQLQuery,"0",false)) {
            verify = true;
        } 
        return verify;
   }

   private static int contador(int i){
      i++;
      return i;
   }
   
   private static String CheckLogin(String luser, String lpas) {
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
}
