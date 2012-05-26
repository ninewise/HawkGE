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
public class LeaveChatQuestion  extends JFrame{

    private User ownUser;
    
    /*
     * Opent een dialog als de gebruiker een ChatRequest krijgt.
     */

    public LeaveChatQuestion(User ownUser) {
        this.ownUser = ownUser;
    }

    public boolean getAnswer() {
       int answer = JOptionPane.showConfirmDialog(
                this,
                ownUser + ",  are you sure you want to leave ?",
                "Leaving",
                JOptionPane.WARNING_MESSAGE);
       dispose();
       if ( answer ==  0){
           
           return true;
       }
       else
           return false;
    }
}

