/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.actions.SelectColorAction;
import hawkge.chat.actions.SelectFontAction;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.TextColorModel;
import hawkge.chat.optionmenu.OptionMenu;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Sels
 */
public class ChatMenuBar extends JMenuBar {
    
    /*
     * Maakt een menubar aan in het chatframe 
     */
    private TextColorModel colorModel;
    private FontSelectorModel selectorModel;
    private ChatModel chatModel;
    private JFrame frame;
    
    public ChatMenuBar(TextColorModel colorModel, ChatModel chatModel,FontSelectorModel selectorModel,JFrame frame){
       
        this.colorModel = colorModel;
        this.chatModel = chatModel;
        this.selectorModel = selectorModel;
        this.frame = frame;
        initializeMenu();
        
    }
    
    private void initializeMenu(){
  
        OptionMenu optionMenu = new OptionMenu(chatModel,frame);
        
        JMenu style = new JMenu("Style");
        style.add(new JMenuItem(new SelectColorAction(colorModel,chatModel)));
        style.add(new JMenuItem(new SelectFontAction(selectorModel,chatModel)));
        
        this.add(optionMenu);        
        this.add(style);
    
    }
}
