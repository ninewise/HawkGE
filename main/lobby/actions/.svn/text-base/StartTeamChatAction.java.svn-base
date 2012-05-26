/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.main.lobby.actions;

import hawkge.chat.gui.TeamChatFrame;
import hawkge.chat.model.TeamChatModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class StartTeamChatAction  extends AbstractAction {

    public StartTeamChatAction() {
        super("Start teamchat...");
    }

    /**
     * Start a new chatsession.
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        TeamChatModel teamChatModel = new TeamChatModel();
        TeamChatFrame frame = new TeamChatFrame(teamChatModel);
        teamChatModel.addOwnUserToChat(true);
    }
}