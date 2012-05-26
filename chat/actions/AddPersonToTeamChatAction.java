/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.gui.AddPlayerToTeamChatFrame;
import hawkge.chat.model.TeamChatModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class AddPersonToTeamChatAction extends AbstractAction {

    protected TeamChatModel model;

    /*
     * Deze klasse zorgt voor het aanmaken van een nieuw venster als de gebruiker
     * op add friend drukt.
     */
    
    public AddPersonToTeamChatAction(TeamChatModel model) {
        super("Invite friend");
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        AddPlayerToTeamChatFrame frame = new AddPlayerToTeamChatFrame(model, null);
    }
}
