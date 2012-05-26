/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.listener.ChatWindowListener;
import hawkge.chat.chatsession.NormalChatSession;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.TextColorModel;
import hawkge.event.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;

/**
 *
 * @author Sels
 */
public class ChatFrame extends JFrame {
    
    /*
     * Maakt de frame aan van een chatsessie 
     */
    
   public ChatFrame(ChatModel chatModel){
       
     this.setTitle("ChatFrame");
     
     TextColorModel colorModel = new TextColorModel();
     FontSelectorModel selectorModel = new FontSelectorModel();
     
     JTextPane textPane = new JTextPane();
     setContentPane(textPane);
     
     this.setJMenuBar(new ChatMenuBar(colorModel,chatModel,selectorModel,this));
     this.setContentPane(new NormalChatSession(chatModel,colorModel,selectorModel));
     this.setVisible(true);
     this.pack();
     this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
     this.addWindowListener(new ChatWindowListener(chatModel,this));
     
   }
}
