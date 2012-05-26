/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.model.ChatModel;
import hawkge.game.models.GameModel;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class AddFriendFrame extends JFrame{
    
    /*
     * Maakt de GUI van de affFriendFrame aan
     */
    
    public AddFriendFrame(ChatModel model, GameModel gmodel){
        super("Invite friend");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(new AddFriendPanel(model,this,gmodel));
        setVisible(true);
        pack();
    }
}
