/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.optionmenu;

import hawkge.chat.model.ChatModel;
import javax.swing.JFrame;
import javax.swing.JMenu;

/**
 *
 * @author Sels
 */
public class OptionMenu extends JMenu {
    
    public OptionMenu(ChatModel chatModel,JFrame frame){
        super("Option");
        add(new AddItem(chatModel));
        add(new LeaveItem(chatModel,frame)); 
 
    }
}
