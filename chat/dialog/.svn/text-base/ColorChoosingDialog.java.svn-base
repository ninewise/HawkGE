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
public class ColorChoosingDialog extends JFrame{
    
    private User ownUser;
    
    public ColorChoosingDialog(User ownUser){
        this.ownUser = ownUser;
    }
    
    public boolean getAnswer(){
        int answer = JOptionPane.showConfirmDialog(
                this, 
                ownUser + ", do you want to save your color?",
                "Save color selection",
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
