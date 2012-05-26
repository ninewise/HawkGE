/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.optionmenu;

import hawkge.chat.actions.AddFriendAction;
import hawkge.chat.model.ChatModel;
import javax.swing.JMenuItem;

/**
 *
 * @author Sels
 */
public class AddItem extends JMenuItem{
    
    public AddItem(ChatModel model){
        super(new AddFriendAction(model));
    }
}
