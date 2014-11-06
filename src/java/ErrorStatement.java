/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alvega
 */
public class ErrorStatement {
    public static void Err(String err) {
        javax.swing.JOptionPane optionPane = new javax.swing.JOptionPane(err, javax.swing.JOptionPane.ERROR_MESSAGE);
        javax.swing.JDialog dialog = optionPane.createDialog("");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
     
}
