/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat;

import hawkge.chat.chatsession.GameChatSession;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TextColorModel;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class test {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        ChatModel model = new ChatModel();
        FontSelectorModel font = new FontSelectorModel();
        TextColorModel color = new TextColorModel();
        frame.setContentPane(new GameChatSession(model, color, font));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
