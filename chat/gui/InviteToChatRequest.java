/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.storage.User;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sels
 */
public class InviteToChatRequest extends JFrame{

    private User sender;
    
    /*
     * Opent een dialog als de gebruiker een ChatRequest krijgt.
     */

    public InviteToChatRequest(User sender) {
        this.sender = sender;
    }

    public boolean getAnswer() {
       int answer = JOptionPane.showConfirmDialog(
                this,
                "Would you like to chat with " + sender + "?",
                "Chat request",
                JOptionPane.YES_NO_OPTION);
       dispose();
       if ( answer ==  0){
           
           return true;
       }
       else
           return false;
    }
}
