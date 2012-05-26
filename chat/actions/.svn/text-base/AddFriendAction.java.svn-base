/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.gui.AddFriendFrame;
import hawkge.chat.model.ChatModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class AddFriendAction extends AbstractAction {

    protected ChatModel model;

    /*
     * Deze klasse zorgt voor het aanmaken van een nieuw venster als de gebruiker
     * op add friend drukt.
     */
    
    public AddFriendAction(ChatModel model) {
        super("Invite friend");
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        AddFriendFrame frame = new AddFriendFrame(model, null);
    }
}
