/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.event.UserLeavingChatEvent;
import hawkge.chat.dialog.LeaveChatQuestion;
import hawkge.chat.model.ChatModel;
import hawkge.event.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class LeaveAction extends AbstractAction{

    private ChatModel chatModel;
    private JFrame frame;
    
    public LeaveAction(ChatModel chatModel,JFrame frame){
        super("Leave chat");
        this.chatModel = chatModel;
        this.frame = frame;
    }
    
    public void actionPerformed(ActionEvent e) {
       LeaveChatQuestion question = new LeaveChatQuestion(chatModel.getOwnUser());
       if ( question.getAnswer()){
           EventQueue.getQueue().queueEvent(new UserLeavingChatEvent(chatModel.getOwnUser(), chatModel.getUsersInChat(),chatModel.getId()));
           frame.dispose();
       }
    }
    
}
