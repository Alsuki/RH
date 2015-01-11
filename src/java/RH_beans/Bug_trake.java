/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RH_beans;

import static RH_beans.SQLconnector.SQLconnectionSTATUS;

/**
 *
 * @author alvega
 */
public class Bug_trake {
    public static void main(String[] args){
        Auth.LoginUser="admin";
        Auth.LoginPass="admin";
        //test = Auth.User("admin",true);
        //System.out.println(Auth.User("admin",true));
        //System.out.println(Auth.Pass("admin","admin",true));
        System.out.println(Auth.Login());
        
    }
}
