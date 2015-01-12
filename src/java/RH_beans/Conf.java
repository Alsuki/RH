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
    
    private static String[] miArray = new String[5];
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
    
    private static void save(String str){
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
    
    public static void SaveToFile(String ServerSTR, String PortSTR, String UserSTR, String PasswordSTR, String DatabaseSTR) {
        //Preperes the information to be save on the file
        String result = "";
        String Server = encrypt(ServerSTR);
        String Port = encrypt(PortSTR);
        String User = encrypt(UserSTR);
        String Password = encrypt(PasswordSTR);
        String Database = encrypt(DatabaseSTR);
        result = ("<Server>" + Server + "</Server>\n");
        result = (result + "<Port>" + Port + "</Port>\n");
        result = (result + "<User>" + User + "</User>\n");
        result = (result + "<Password>" + Password + "</Password>\n");
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
        String[] str = new String[5];

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
            
            if (line.contains("User")) {
                str[3] = Value(line);
            }
            
            if (line.contains("Password")) {
                str[4] = Value(line);
            }
        }
        return str;
    }

    private static String[] readFile(String str)
        throws java.io.IOException {
        //parses a given file for configuration information

        java.io.File filo = new java.io.File(str);
        System.out.println(filo.getAbsolutePath());
        int i = 0;
        String[] lines = new String[5];
        String content = "";
        java.io.FileInputStream fis = null;
        java.io.BufferedReader reader = null;
        
        try  {
            fis = new java.io.FileInputStream(str);
            reader = new java.io.BufferedReader(new java.io.InputStreamReader(fis));
            while ((content = reader.readLine()) != null) {
                lines[i] = content;
                i++;
            }
        } catch (java.io.FileNotFoundException ex) {
               // deve executar um html de recolha de dados.
        }
        catch (java.io.IOException e) {
                e.printStackTrace();
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
        return LineToArray(lines);
    }
    
    public static String server(){
       try {
            miArray = readFile(ConfFile);
        } catch (java.io.IOException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return decrypt(miArray[0]);
    }
    
    public static String serverport(){
       try {
            miArray = readFile(ConfFile);
        } catch (java.io.IOException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return decrypt(miArray[1]);
    }
    
    public static String dbname(){
      try {
            miArray = readFile(ConfFile);
        } catch (java.io.IOException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return decrypt(miArray[2]);
    }
    
    public static String dbuser(){
      try {
            miArray = readFile(ConfFile);
        } catch (java.io.IOException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return decrypt(miArray[3]);
    }
    
    public static String dbpassword(){
        try {
            miArray = readFile(ConfFile);
        } catch (java.io.IOException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return decrypt(miArray[4]);
    }
    
    public static void Connect() {
        // criado este metodo porque o mesmo e necessario para testar a ligacao
        // nao consigo encontrar o ficheiro no sistema tomcat.
        // para resolver...
       //SQLconnector.SQLconnectionSTART(server(),serverport(),dbname(),dbuser(),dbpassword());
       SQLconnector.SQLconnectionSTART("localhost","3306","RH","RH","RH");
   }
}
