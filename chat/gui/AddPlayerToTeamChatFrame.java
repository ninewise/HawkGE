/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.model.TeamChatModel;
import hawkge.game.models.GameModel;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class AddPlayerToTeamChatFrame extends JFrame{
    
    /*
     * Maakt de GUI van de affFriendFrame aan
     */
    
    public AddPlayerToTeamChatFrame(TeamChatModel model, GameModel gmodel){
        super("Invite player");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(new AddPlayerToTeamChatPanel(model, this, gmodel));
        setVisible(true);
        pack();
    }
}