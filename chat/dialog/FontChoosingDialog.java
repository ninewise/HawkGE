/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.dialog;

import hawkge.storage.User;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sels
 */
public class FontChoosingDialog extends JFrame{
    
    private User ownUser;
    
    public FontChoosingDialog(User ownUser){
        this.ownUser = ownUser;
    }
    
    public boolean getAnswer(){
        int answer = JOptionPane.showConfirmDialog(
                this, 
                ownUser + ", do you want to save your font?",
                "Save font selection",
                JOptionPane.WARNING_MESSAGE);
        dispose();
        if ( answer == 0){
            return true;
        }
        else{
            return false;
        }
    }
}