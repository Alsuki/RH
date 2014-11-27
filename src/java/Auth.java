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

   public static boolean User(String user, boolean n) {
       
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

   public static boolean Pass(String password, boolean n ) {
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

   public static boolean Estado(){
       boolean verify = false;
        String SQLQuery = "SELECT * from account_login ";
        if (SQLconnector.SQLVerify(SQLQuery,"" ,true)) {
            verify = true;
        } else {
           
        }
     return verify;
   }
   
   
  public static int contador(int i){
      i++;
      return i;
  } 
}
