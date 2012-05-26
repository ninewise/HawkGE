/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.event.UserLeavingChatEvent;
import hawkge.chat.dialog.LeaveChatQuestion;
import hawkge.chat.model.ChatModel;
import hawkge.event.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class ChatWindowListener extends WindowAdapter {
    
    private ChatModel model;
    private JFrame frame;
    
    public ChatWindowListener(ChatModel model,JFrame frame){
        this.model = model;
        this.frame = frame;
    }
    
    @Override
     public void windowClosing(WindowEvent winEvt) {
        LeaveChatQuestion question =new LeaveChatQuestion(model.getOwnUser());
        if ( question.getAnswer()){
            EventQueue.getQueue().queueEvent(new UserLeavingChatEvent(model.getOwnUser(), model.getUsersInChat(),model.getId()));
            frame.dispose();
        }  
    }
}
