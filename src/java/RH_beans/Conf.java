package RH_beans;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alvega
 */

public class Conf {
    
    private static String[] miArray = new String[6];
    private static String ConfFile = "rh.xml";
    
    private static final int[] cypher = {
        382019, 8320198, 23, 565655, 839, 5362, 3672, 3781, 6371866, 80321, 3267, 7312938
    };
    
    
    public static String encrypt(String str) {
        String result = "";
        int lenght = str.length();
        char caracter;
        int ck = 0;
        
        for (int i = 0; i < lenght; i++) {
            if (ck >= cypher.length -1) 
                ck = 0;
            caracter = str.charAt(i);
            caracter += cypher[ck];
            result += caracter;
            ck++;
        }
        
        return result;
    }
    
    public static String decrypt(String str) {
        String result = "";
        int length = str.length();
        char caracter;
        int ck = 0;
        for (int i = 0; i < length; i++) {
            if (ck >= cypher.length -1)
                ck = 0;
            caracter = str.charAt(i);
            caracter -= cypher[ck];
            result += caracter;
            ck++;
        }
        return result;
    }
    
    public static void save(String str){
        //Saves settings to file
        java.io.File setings = new java.io.File(ConfFile);
        if (setings.exists()) {
            setings.delete();
            try {
                java.io.BufferedWriter savemi = new java.io.BufferedWriter(new java.io.FileWriter(setings));
                savemi.write(str);
                savemi.flush();
                savemi.close();
            } catch (java.io.IOException evt) {
                evt.printStackTrace();
            }
        } else {
            try {
                java.io.BufferedWriter savemi = new java.io.BufferedWriter(new java.io.FileWriter(setings));
                savemi.write(str);
                savemi.flush();
                savemi.close();
            } catch (java.io.IOException evt) {
                evt.printStackTrace();
            }
        }
        
    }
    
    public static void SaveToFile(String ServerSTR, String PortSTR, String UserSTR, String PasswordSTR, String InstanceSTR, String DatabaseSTR) {
        //Preperes the information to be save on the file
        String result = "";
        String Server = encrypt(ServerSTR);
        String Port = encrypt(PortSTR);
        String User = encrypt(UserSTR);
        String Password = encrypt(PasswordSTR);
        String Instancia = encrypt(InstanceSTR);
        String Database = encrypt(DatabaseSTR);
        result = ("<Server>" + Server + "</Server>\n");
        result = (result + "<Port>" + Port + "</Port>\n");
        result = (result + "<User>" + User + "</User>\n");
        result = (result + "<Password>" + Password + "</Password>\n");
        result = (result + "<Instancia>" + Instancia + "</Instancia>\n");
        result = (result + "<Database>" + Database + "</Database>");
        save(result);
    }
    private static String Value(String str){
        //Returns the expected string
	String[] splinter = str.split(">");
	splinter = splinter[1].split("<");
	return splinter[0];
    }
    
    private static String[] LineToArray(String[] lines) {
        // Obtem a linha do ficheiro e retorna o valor correcto na posicao correcta do array
        String[] str = new String[6];

        for (String line : lines) {
            if (line.contains("Server")){
                str[0] = Value(line);
            }
            if (line.contains("Port")) {
                str[1] = Value(line);
            }
          if (line.contains("Database")) {
                str[2] = Value(line);
            }
            if (line.contains("Instancia")) {
                str[3] = Value(line);
            }
            if (line.contains("User")) {
                str[4] = Value(line);
            }
            if (line.contains("Password")) {
                str[5] = Value(line);
            }
        }
        return str;
    }
    
    private static String[] readFile(String str) {
        //parses a given file for configuration information
        java.io.FileReader file;
        java.io.BufferedReader conf;
        int i = 0;
        String[] lines = new String[6];
        try {
             file = new java.io.FileReader(str);
            try {
                conf = new java.io.BufferedReader(file);
                try {
                    while ( i<6 ) {
                        lines[i] = conf.readLine();
                        i++;
                    }
                } catch (java.io.IOException evt) {}
            } finally {
                try {
                    conf = new java.io.BufferedReader(file);
                    conf.close();
                }catch (java.io.IOException evt) {}
            }
        }  catch (java.io.FileNotFoundException evt) {}  
       return LineToArray(lines);
    }
    
    public static String server(){
        miArray = readFile(ConfFile);
        return miArray[1];
    }
    
    public static String serverport(){
        miArray = readFile(ConfFile);
        return miArray[2];
    }
    
    public static String dbname(){
        miArray = readFile(ConfFile);
        return miArray[3];
    }
    
    public static String dbinstance(){
        miArray = readFile(ConfFile);
        return miArray[4];
    }
    
    public static String dbuser(){
        miArray = readFile(ConfFile);
        return miArray[5];
    }
    
    public static String dbpassword(){
        miArray = readFile(ConfFile);
        return decrypt(miArray[6]);
    }
    
    public static void Connect() {
        // criado este metodo porque o mesmo e necessario para testar a ligacao
       SQLconnector.SQLconnectionSTART(server(),serverport(),dbname(),dbinstance(),dbuser(),dbpassword());
   }
}
