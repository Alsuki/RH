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
           // SQLconnectionSTATUS() = true;
        } catch (Exception e) {
            ErrorStatement.Err("Faild to connect to Server\n Please verify you settings.");
            Cstatus = false;
        }
        return Cstatus;
    }
    
    public static boolean SQLconnectionDISCONNECT() {
        boolean Cstatus = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connServer.close();
            Cstatus = true;
        } catch (Exception e) {
            Cstatus = false;
        }
        return Cstatus;
    }
    
    public static boolean SQLconnectionSTATUS() {
        boolean sqlConnectionSTATUS=false;
        return sqlConnectionSTATUS;
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
        return state;
    }
    
    //public staic boolean SQLconnetionALIVE() {   
    //}
    
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
    
    //public static String SQLInsert() {
    //}
    
   public static boolean SQLVerify(String SELECT, String ToVERIFY) {
       boolean correct = false;
       try {
           java.sql.Statement SQLSelectStatement = connServer.createStatement();
           java.sql.ResultSet SQLSelectResult = SQLSelectStatement.executeQuery(SELECT);
           if (ToVERIFY == SQLSelectResult.getString(1) ){
               correct = true;
           } else {
               correct = false;
           }
       } catch (java.sql.SQLException e){
           e.printStackTrace();
       }
       return correct;
       
   }
    
}
