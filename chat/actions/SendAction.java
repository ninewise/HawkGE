/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.area.ChatFieldArea;
import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TextColorModel;
import hawkge.storage.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class SendAction extends AbstractAction {

    private ChatFieldArea field;
    private ChatModel model;
    private TextColorModel colorModel;
    private FontSelectorModel selectorModel;
    
    /*
     * Deze actie zorgt voor het afhandelen als de gebruiker een nieuwe bericht
     * wil verzenden
     */
    
    public SendAction(ChatFieldArea field, ChatModel model, TextColorModel colorModel,FontSelectorModel selectorModel) {
        super("Send");
        this.field = field;
        this.model = model;
        this.colorModel = colorModel;
        this.selectorModel = selectorModel;
    }

    /*
     * Nog aanpassen voor kleur en font
     */
    public void actionPerformed(ActionEvent e) {
        String text = field.getText();        
        User user = model.getOwnUser();
        ArrayList<User> receivers = model.getUsersInChat();
        Color color = colorModel.getResultColor();
        Font font = selectorModel.getResultFont();
        ChatMessage message = new ChatMessage(text, user, receivers, font, color,false);
        model.sendMessage(message);
        field.setText("");
    }
}
