/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.gui.AddFriendFrame;
import hawkge.chat.gui.AddPlayerToTeamChatFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class CancelTeamAction extends AbstractAction{
    
    private AddPlayerToTeamChatFrame frame;
    
    /*
     * Klasse zorgt voor het afhandelen als de gebruiker op "Cancel" duwt in het
     * addFriendFrame
     */
    
    public CancelTeamAction(AddPlayerToTeamChatFrame frame){
        super("Cancel");
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent ae) {     
        frame.dispose();
    }
}

