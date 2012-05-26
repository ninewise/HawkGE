/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.gui.AddFriendFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class CancelAction extends AbstractAction {
    
    private AddFriendFrame frame;
    
    /*
     * Klasse zorgt voor het afhandelen als de gebruiker op "Cancel" duwt in het
     * addFriendFrame
     */
    
    public CancelAction(AddFriendFrame frame){
        super("Cancel");
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent ae) {     
        frame.dispose();
    }
}
