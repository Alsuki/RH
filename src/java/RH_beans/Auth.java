package RH_beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Acer
 */
public class Auth {
   public void setLoginUser (String LoginUser) {
       this.LoginUser = LoginUser; 
   }
   
   public void setLoginPass (String LoginPass) {
       this.LoginPass = LoginPass;
   }
   
   public String getLogin(){
       return CheckLogin(LoginUser, LoginPass);
   }
   
   public String getLoginUser(){
       return LoginUser;
   }
   
   private String LoginUser;
   private String LoginPass;
   
   private boolean User(String user, boolean n) {   
    //n tem que ser "true" na primeira chamada
    boolean verify = false;
    String SQLQuery = " SELECT * from account_login where Name = '"+user+"'";
    if (SQLconnector.SQLconnetionALIVE() && n) {
        if (SQLconnector.SQLVerify(SQLQuery,user,false)) {
            verify = true;
        }
    } else {
        if (n){
            Conf.Connect();
            User(user, false);
        }
        if (SQLconnector.SQLconnetionALIVE() && !n){
            if (SQLconnector.SQLVerify(SQLQuery, user, false)){
                verify = true;
            }
        }
    }
    /* se nao for = verdade retorna o false declarado acima. */
    return verify;
   }

   private boolean Pass(String password, boolean n ) {
       boolean verify = false;
       String str = Conf.encrypt(password);
       String SQLQuery = "SELECT * from account_login where Password ='"+str+"'";

       if (SQLconnector.SQLVerify(SQLQuery, password,true)) {
         verify = true;
          } else {
           if (n){
               Conf.Connect();
               Pass(str, false);
           }
           if (SQLconnector.SQLconnetionALIVE() && !n){
               if (SQLconnector.SQLVerify(SQLQuery, password, false)){
                   verify = true;
               }
           }
       }
    /* se nao for = verdade retorna o false declarado acima. */
     return verify;
     }

   private boolean Estado(String user){
       boolean verify = false;
       //Novo select from account_login onde user estado
        String SQLQuery = "SELECT * from account_login ";
        if (SQLconnector.SQLVerify(SQLQuery,"" ,true)) {
            verify = true;
        } else {
           
        }
     return verify;
   }
   
   
  private int contador(int i){
      i++;
      return i;
  } 
  
  private String CheckLogin(String luser, String lpas) {
      String status = "";
      int Counter = 0;
      if (User(luser,true)) {
          if (Pass(lpas,true)){
              if(Estado(luser)) {
                  status = "login,Welcome!!";
              } else {
                  status = "error,Your account is lock, please contact this system Administrator.";
              } 
          } else {
              if (contador(Counter) < 3) {
                  String SQLInsert = "INSERT INTO account_lock Integer.toString(Counter)";
                  SQLconnector.SQLInsert(SQLInsert);
              } else { 
                  status = "error,User or Password were incorrect. Please try again!";
              }
          } 
      } else {
          status = "error,User or Password were incorrect. Please try again!";
      }
      return status;
  } 
}
