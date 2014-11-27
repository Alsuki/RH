/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alvega
 */

public class SQLconnector {
    
    private static java.sql.Connection connServer;
    
    private static String SQLconnection(String db_server, String db_port, String db_name, String db_instance, String db_user, String db_password) {
        return "jdbc:mysql://"+ db_server + "/" + db_instance + ":" + db_port + ";user=" + db_user + ";password=" + db_password + ";databaseName=" + db_name + ";";
    }
    
    public static boolean SQLconnectionSTART(String db_server, String db_port, String db_name, String db_instance,
            String db_userid, String db_password){
        boolean Cstatus= false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connServer = java.sql.DriverManager.getConnection(SQLconnection(db_server, db_port, db_name, db_instance ,db_userid, db_password));
            Cstatus = true;    
            SQLconnectionSTATUS(true);
        } catch (Exception e) {
            ErrorStatement.Err("Faild to connect to Server\n Please verify you settings.");
            Cstatus = false;
        }
        SQLconnectionSTATUS(Cstatus);
        return Cstatus;
    }
    
    public static boolean SQLconnectionDISCONNECT() {
        boolean Cstatus = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connServer.close();
            Cstatus = true;
            SQLconnectionSTATUS(false);
        } catch (Exception e) {
            Cstatus = false;
            SQLconnectionSTATUS(true);
        }
        return Cstatus;
    }
    
    public static boolean SQLconnectionSTATUS(boolean Status) {
        return Status;
    }
    
    public static boolean SQLconnectionTEST(String db_server, String db_port, String db_name, String db_instance,
            String db_userid, String db_password){
        boolean state = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connServer = java.sql.DriverManager.getConnection(SQLconnection(db_server, db_port, db_name, db_instance ,db_userid, db_password));
            connServer.close();
            state = true;
        } catch (Exception evt) {
            evt.printStackTrace();
            state = false;
        }
        SQLconnectionSTATUS(state);
        return state;
    }
    
    public static boolean SQLconnetionALIVE() {  
        boolean Status = false;
        String SQLquery = "SELECT 1 from account_login;";
        try {
            java.sql.Statement SQLSelectStatement = connServer.createStatement();  
            java.sql.ResultSet SQLSelectResult = SQLSelectStatement.executeQuery(SQLquery);
            Status = true;
        } catch (Exception evt) {
            evt.printStackTrace();
            SQLconnectionSTATUS(false);
        }
        return Status;
    }
    
   public static java.util.Vector SQLSelect(String SELECT, int n) {
       //retorma um vector com o resultado do select solicitado 
       java.util.Vector<java.util.Vector<String>> Result = new java.util.Vector<java.util.Vector<String>>();
    try {
        java.sql.Statement SQLSelectStatement = connServer.createStatement();
        java.sql.ResultSet SQLSelectResult = SQLSelectStatement.executeQuery(SELECT);
        while (SQLSelectResult.next()) {
                java.util.Vector<String> Dline = new java.util.Vector<String>();
                for (int i = 1; i < n; i++) {
                   Dline.add(SQLSelectResult.getString(i));
                }
                Result.add(Dline);
            }
            SQLSelectStatement.close();
    
    } catch (java.sql.SQLException e) {
            e.printStackTrace();
    }
    return Result;
   }
    
   public static boolean SQLInsert(String Insert) {
       // Executa um insert recebido via string e retorna o resultado da execucao
       boolean Status = false;
        try {
            java.sql.Statement SQLSelectStatement = connServer.createStatement();
            SQLSelectStatement.executeUpdate(Insert);
            SQLSelectStatement.close();
            Status = true;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return Status;
   }
    
   public static boolean SQLVerify(String SELECT, String ToVERIFY, boolean Cicle) {
       //Executes a SQL query recived via the SELECT string and compares it with
       //the string ToVERIFY.
       //Cicle is a boolean variable that determines if the SQL query should be run 
       //to the end of the table.
       //it returns true or false if ToVERIFY is found and identical to what is stored 
       //on the database.

       boolean correct = false;
       boolean NotFound = true;
       try {
           java.sql.Statement SQLSelectStatement = connServer.createStatement();
           java.sql.ResultSet SQLSelectResult = SQLSelectStatement.executeQuery(SELECT);
           if (Cicle) {
               while (SQLSelectResult.next() && NotFound) {
                   if ( ToVERIFY == SQLSelectResult.getString(1)){
                       correct = true;
                       NotFound = false;
                   } else {
                       correct = false;
                   }
               }
           } else {
               if (ToVERIFY == SQLSelectResult.getString(1) ){
                   correct = true;
               } else {
                   correct = false;
               }
           }
           SQLSelectStatement.close();
       } catch (java.sql.SQLException e){
           e.printStackTrace();
       }
       return correct;
       
   }
    
}
