/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RH_beans;

/**
 *
 * @author alvega & Edson
 */

/* Este programa e um "Stand Allone" para a criacao do
   ficheiro de configuracao rh.xml, onde vai estar toda 
   a informacao de acessoa a base de dados.
    Este programa e executado na consola apenas nao tem interface grafica.
*/
public class Create_Config_File {
    private static String Server;
    private static String Database;
    private static String ServerPort;
    private static String DBUser;
    private static String DBPass;
    
    private static String getPassword(String prompt) {
        String password = "";
        ConsoleEraser consoleEraser = new ConsoleEraser();
        System.out.print(prompt);
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        consoleEraser.start();
        try {
            password = in.readLine();
        }
        catch (java.io.IOException e){
            System.out.println("Error trying to read your password!");
            System.exit(1);
        }

        consoleEraser.halt();
        System.out.print("\b");

        return password;
    }

    private static class ConsoleEraser extends Thread {
        private boolean running = true;
        public void run() {
            while (running) {
                System.out.print("\b ");
            }
        }
        public synchronized void halt() {
            running = false;
        }
    }
    
    private static void AskQuestions() {
        java.util.Scanner in = new java.util.Scanner(System.in);
        java.io.Console c = System.console();
        System.out.println("Nome ou IP do Servidor?");
        System.out.println("Ex. \"localhost\", \"test.localdomain.local\", \"192.168.1.1\"");
        System.out.println("?: ");
        Server = in.nextLine();
        System.out.println("");
        System.out.println("Nome da Base de Dados? ");
        System.out.println("?: ");
        Database = in.nextLine();
        System.out.println("");
        System.out.println("Nome da Base de Dados? ");
        System.out.println("Ex. \"3306\"");
        System.out.println("?: ");
        ServerPort = in.nextLine();
        System.out.println("");
        System.out.println("Utilizador da Base de Dados? ");
        System.out.println("?: ");
        DBUser = in.nextLine();
        System.out.println("");
        System.out.println("Password? ");
        DBPass = getPassword("?: ");
    }
    
    public static void main(String[] args){
        /* Para este programa nao foi desenvolvido nenhum sistema de verificacao de erros
            ou confirmacao de dados intruduzido.
        Essa parte ficara em falta, podendo ser desenvolvida se dermos continuidade...
        */
        AskQuestions();
        Conf.SaveToFile(Server, ServerPort, DBUser, DBPass, Database);
        //System.out.println(Conf.dbuser());
        //System.out.println(Conf.dbpassword());
    }
    
}
